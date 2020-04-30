package org.sim0mq.test.threads;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Play with three event producing threads that need to send their message over a shared socket to a receiving thread that is
 * listening. The PUSH-PULL over the inproc protocol with a synchronized send method is used to implement this. Messages are
 * sent without waiting to not block the sending threads. Therefore, the HWM is set considerably higher to not lose any
 * messages.
 * <p>
 * Copyright (c) 2013-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version 30 Apr 2020 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class PushPullThreads
{
    /** the socket for sending. */
    private ZMQ.Socket pushSocket;

    /** the context for this program. */
    private ZContext ctx;

    /**
     * @param args empty
     */
    public static void main(final String[] args)
    {
        new PushPullThreads();
    }

    /**
     * Constructor.
     */
    public PushPullThreads()
    {
        this.ctx = new ZContext(1);
        this.pushSocket = this.ctx.createSocket(SocketType.PUSH);
        this.pushSocket.setHWM(100000);
        this.pushSocket.connect("inproc://bus");
        for (int i = 0; i < 3; i++)
        {
            new ProducerThread(this, i);
        }
        new ConsumerThread(this.ctx).start();
    }

    /**
     * Process message and push to central thread.
     * @param message the message to send
     */
    public synchronized void call(final String message)
    {
        this.pushSocket.send(message, ZMQ.DONTWAIT); // don't block the sending thread
    }

    /** */
    static class ProducerThread extends Thread
    {
        /** the thread number. */
        private int threadNr;

        /** the calling program. */
        private PushPullThreads program;

        /**
         * @param program the calling program with the notify() method
         * @param threadNr the thread number
         */
        ProducerThread(final PushPullThreads program, final int threadNr)
        {
            this.program = program;
            this.threadNr = threadNr;
            start();
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            for (int i = 0; i < 1000; i++)
            {
                this.program.call("Message from thread " + this.threadNr + " #" + i);
            }
            this.program.call("STOP");
        }
    }

    /** */
    static class ConsumerThread extends Thread
    {
        /** the context. Should be the same for inproc messages. */
        private ZContext ctx;

        /**
         * Constructor.
         * @param ctx the context
         */
        ConsumerThread(final ZContext ctx)
        {
            this.ctx = ctx;
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            ZMQ.Socket pullSocket = this.ctx.createSocket(SocketType.PULL);
            pullSocket.bind("inproc://bus");
            int stopCount = 0;
            int msgCount = 0;
            while (true)
            {
                String msg = pullSocket.recvStr(0);
                if ("STOP".equals(msg))
                {
                    stopCount++;
                    if (stopCount == 3)
                    {
                        break;
                    }
                }
                else
                {
                    msgCount++;
                    System.out.println(msg);
                }
            }
            System.out.println("# messages received = " + msgCount);
        }

    }
}
