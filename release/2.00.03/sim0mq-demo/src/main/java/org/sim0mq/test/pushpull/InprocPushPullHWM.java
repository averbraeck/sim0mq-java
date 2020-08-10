package org.sim0mq.test.pushpull;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Send PUSH - PULL messages over the inproc protocol. There is a HWM set at 500 messages, and 1000 messages are flooding the
 * network, because the PUSH thread is created before the PULL thread.
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version 30 Apr 2020 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class InprocPushPullHWM
{

    /** */
    private InprocPushPullHWM()
    {
        // utility class
    }

    /**
     * @param args empty
     * @throws InterruptedException on error
     */
    public static void main(final String[] args) throws InterruptedException
    {
        // start a push thread and a pull thread that send messages to each other using the inproc protocol
        ZContext ctx = new ZContext();
        new PushThread(ctx);
        Thread.sleep(5); // make sure the output buffer floods
        new PullThread(ctx);
    }

    /** */
    private static class PushThread extends Thread
    {
        /** the PUSH socket. */
        private ZMQ.Socket pushSocket;

        /**
         * @param ctx the context.
         */
        PushThread(final ZContext ctx)
        {
            this.pushSocket = ctx.createSocket(SocketType.PUSH);
            this.pushSocket.setHWM(500);
            this.pushSocket.connect("inproc://bus");
            start();
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            for (int i = 0; i < 1000; i++)
            {
                this.pushSocket.send("Hello #" + i, ZMQ.DONTWAIT); // non-blocking (flood the buffer)
            }
            System.out.println("1000 messages sent");
            this.pushSocket.send("STOP", 0);
        }
    }

    /** */
    private static class PullThread extends Thread
    {
        /** the PULL socket. */
        private ZMQ.Socket pullSocket;

        /**
         * @param ctx the context.
         */
        PullThread(final ZContext ctx)
        {
            this.pullSocket = ctx.createSocket(SocketType.PULL);
            this.pullSocket.bind("inproc://bus");
            start();
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            int count = 0;
            while (true)
            {
                String msg = this.pullSocket.recvStr(0); // blocking
                if (msg.equals("STOP"))
                {
                    break;
                }
                count++;
            }
            System.out.println(String.format("%d messages received", count));
        }
    }

}
