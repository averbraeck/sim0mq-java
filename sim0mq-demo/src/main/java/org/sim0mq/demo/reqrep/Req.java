package org.sim0mq.demo.reqrep;

import java.util.concurrent.atomic.AtomicLong;

import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * <p>
 * Copyright (c) 2013-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Aug 4, 2018 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class Req
{
    /** counter to see if we are ready. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    protected AtomicLong counter = new AtomicLong(0);

    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     */
    protected Req(final String[] args) throws Sim0MQException
    {
        long time = System.currentTimeMillis();

        if (args.length < 3)
        {
            System.err.println("Use as Req startport #threads #calls/thread [#contexts]");
            System.exit(-1);
        }

        int startport = Integer.parseInt(args[0]);
        long numthreads = Integer.parseInt(args[1]);
        long numcalls = Integer.parseInt(args[2]);
        int numcontexts = args.length > 3 ? Integer.parseInt(args[3]) : 1;

        ZContext context = new ZContext(numcontexts);

        for (int i = 0; i < numthreads; i++)
        {
            new ReqThread(context, startport + i, numcalls).start();
        }

        // wait for all threads ready
        while (this.counter.get() < numcalls * numthreads)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException exception)
            {
                //
            }
            System.out.println("REQ=" + this.counter.get() + " < " + numcalls * numthreads);
        }

        context.destroy();

        long delta = System.currentTimeMillis() - time;
        System.out.println("RUNTIME = " + delta + " ms");
        System.out.println("Transactions/second = " + 1000.0 * numcalls * numthreads / delta + " tps");
        System.out.println("Messages/second (req + rep) = " + 2000.0 * numcalls * numthreads / delta + " mps");
    }

    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     */
    public static void main(final String[] args) throws Sim0MQException
    {
        new Req(args);
    }

    /** The worker thread for the REQ requests. */
    protected class ReqThread extends Thread
    {
        /** the (shared) context. */
        private final ZContext context;

        /** the port to use. */
        private final int port;

        /** the number of calls to use. */
        private final long numcalls;

        /**
         * @param context the (shared) context
         * @param port the port to use
         * @param numcalls the number of calls to use
         */
        public ReqThread(final ZContext context, final int port, final long numcalls)
        {
            this.context = context;
            this.port = port;
            this.numcalls = numcalls;
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            // Socket to talk to server
            System.out.println("REQ: Connecting to server with thread on port " + this.port);

            ZMQ.Socket socket = this.context.createSocket(SocketType.REQ);
            socket.connect("tcp://127.0.0.1:" + this.port);
            String runId = "RUN01";
            String senderId = "REQ." + this.port;
            String receiverId = "REP." + this.port;

            for (int i = 0; i < this.numcalls; i++)
            {
                // send a request
                Object[] request = new Object[] {this.port, i};
                try
                {
                    byte[] message =
                            Sim0MQMessage.encodeUTF8(true, runId, senderId, receiverId, "TEST", i, request);
                    boolean ok = socket.send(message, 0);
                    if (!ok)
                    {
                        System.err.println("send message " + i + " for port " + this.port + " returned FALSE");
                    }

                    byte[] reply = socket.recv(0);
                    if (reply == null)
                    {
                        System.err.println("receive message " + i + " for port " + this.port + " returned NULL");
                    }

                    Object[] replyMessage = Sim0MQMessage.decode(reply).createObjectArray();
                    if (!replyMessage[3].toString().equals(senderId))
                    {
                        System.err.println(Sim0MQMessage.print(replyMessage));
                        System.err.println("receive message " + i + " for port " + this.port + ", receiver = "
                                + replyMessage[3].toString() + ", expected " + senderId);
                    }
                    if (((Number) replyMessage[7]).intValue() == 0)
                    {
                        System.err.println(Sim0MQMessage.print(replyMessage));
                        System.err.println("receive message " + i + " for port " + this.port + ", #fields = 0");
                    }
                    else if (((Number) replyMessage[5]).intValue() != i)
                    {
                        System.err.println(Sim0MQMessage.print(replyMessage));
                        System.err
                                .println("receive message " + i + " for port " + this.port + ", payload# = " + replyMessage[5]);
                    }
                    Req.this.counter.incrementAndGet();
                }
                catch (Sim0MQException | SerializationException exception)
                {
                    exception.printStackTrace();
                }
            }

            // send stop message to REP client
            try
            {
                byte[] message = Sim0MQMessage.encodeUTF8(true, runId, senderId, receiverId, "STOP", -1,
                        new Object[] {});
                boolean ok = socket.send(message, 0);
                if (!ok)
                {
                    System.err.println("send message STOP for port " + this.port + " returned FALSE");
                }

                // wait until stopped
                byte[] reply = socket.recv(0);
                if (reply == null)
                {
                    System.err.println("receive message STOP for port " + this.port + " returned NULL");
                }
            }
            catch (Sim0MQException | SerializationException exception)
            {
                exception.printStackTrace();
            }

            socket.close();
        }

    }

}
