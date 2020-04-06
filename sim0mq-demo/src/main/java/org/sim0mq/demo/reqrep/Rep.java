package org.sim0mq.demo.reqrep;

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
public class Rep
{
    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     */
    protected Rep(final String[] args) throws Sim0MQException
    {
        if (args.length < 2)
        {
            System.err.println("Use as Rep startport #threads [#contexts]");
            System.exit(-1);
        }

        int startport = Integer.parseInt(args[0]);
        long numthreads = Integer.parseInt(args[1]);
        int numcontexts = args.length > 2 ? Integer.parseInt(args[2]) : 1;

        ZContext context = new ZContext(numcontexts);

        for (int i = 0; i < numthreads; i++)
        {
            new RepThread(context, startport + i).start();
        }

        context.destroy();
    }

    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     */
    public static void main(final String[] args) throws Sim0MQException
    {
        new Rep(args);
    }

    /** The worker thread for the REP requests. */
    protected class RepThread extends Thread
    {
        /** the (shared) context. */
        private final ZContext context;

        /** the port to use. */
        private final int port;

        /**
         * @param context the (shared) context
         * @param port the port to use
         */
        public RepThread(final ZContext context, final int port)
        {
            this.context = context;
            this.port = port;
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            // Socket to talk to server
            System.out.println("REP: Connecting to server with thread on port " + this.port);

            // Socket to talk to clients
            ZMQ.Socket socket = this.context.createSocket(SocketType.REP);
            socket.bind("tcp://*:" + this.port);

            String senderId = "REP." + this.port;
            String receiverId = "REQ." + this.port;

            int messagenr = 0;

            while (true)
            {
                try
                {
                    // Wait for next request from the client
                    byte[] request = socket.recv(0);
                    Object[] message = Sim0MQMessage.decode(request).createObjectArray();

                    if (message[4].toString().equals("STOP"))
                    {
                        // send a reply
                        Object[] reply = new Object[] { "STOPPED" };
                        socket.send(Sim0MQMessage.encodeUTF8(true, message[1], senderId, receiverId, "STOPPED", messagenr,
                                reply), 0);
                        break;
                    }

                    // check the message
                    if (!message[3].toString().equals(senderId))
                    {
                        System.err.println(Sim0MQMessage.print(message));
                        System.err.println("receive message " + messagenr + " for port " + this.port + ", receiver = "
                                + message[3].toString() + ", expected " + senderId);
                    }
                    if (((Number) message[7]).intValue() == 0)
                    {
                        System.err.println(Sim0MQMessage.print(message));
                        System.err.println("receive message " + messagenr + " for port " + this.port + ", #fields = 0");
                    }
                    else if (((Number) message[5]).intValue() != messagenr)
                    {
                        System.err.println(Sim0MQMessage.print(message));
                        System.err.println(
                                "receive message " + messagenr + " for port " + this.port + ", payload# = " + message[5]);
                    }

                    // send a reply
                    Object[] reply = new Object[] { message[8], message[9] };
                    socket.send(Sim0MQMessage.encodeUTF8(true, message[1], senderId, receiverId, "REPLY", messagenr,
                            reply), 0);
                }
                catch (Sim0MQException | SerializationException exception)
                {
                    exception.printStackTrace();
                }
                
                // increase the messagenr
                messagenr++;
            }

            socket.close();

        }

    }

}
