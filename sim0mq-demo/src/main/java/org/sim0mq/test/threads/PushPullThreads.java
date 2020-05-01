package org.sim0mq.test.threads;

import java.util.LinkedHashMap;
import java.util.Map;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Play with three event producing threads that need to send their message via a shared method to a receiving thread that is
 * listening. The PUSH-PULL over the inproc protocol with a synchronized send method is used to implement this. Messages are
 * sent without waiting to not block the sending threads. Therefore, the HWM is set considerably higher to not lose any
 * messages. A map of thread id to socket is used to create one socket per sending thread.
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
    /** the context for this program. */
    private ZContext ctx;
    
    /** map of thread ids to inproc sockets. */
    private Map<Long, ZMQ.Socket> socketMap = new LinkedHashMap<>(); 
    
    /** Total number of push threads. */
    private final int totalPushThreads;

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
        this.totalPushThreads = 1000;
        this.ctx = new ZContext(1);
        for (int i = 0; i < this.totalPushThreads; i++)
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
        long threadId = Thread.currentThread().getId();
        ZMQ.Socket pushSocket = this.socketMap.get(threadId);
        if (pushSocket == null)
        {
            pushSocket = this.ctx.createSocket(SocketType.PUSH);
            pushSocket.setHWM(100000);
            pushSocket.connect("inproc://bus");
            this.socketMap.put(threadId, pushSocket);
            System.out.println("Socket added for thread " + threadId);
        }
        pushSocket.send(message, ZMQ.DONTWAIT); // don't block the sending thread
    }

    /** */
    class ProducerThread extends Thread
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
    class ConsumerThread extends Thread
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
                    if (stopCount == totalPushThreads)
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
