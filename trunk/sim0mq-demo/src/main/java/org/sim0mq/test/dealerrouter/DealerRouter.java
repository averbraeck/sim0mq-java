package org.sim0mq.test.dealerrouter;

import java.util.Random;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZFrame;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMsg;

/**
 * 0MQ DEALER-ROUTER example. Based on <a href="http://zguide.zeromq.org/java:asyncsrv">
 * http://zguide.zeromq.org/java:asyncsrv</a> or in the JeroMQ example at
 * <a href= "https://github.com/zeromq/jeromq/blob/master/src/test/java/guide/asyncsrv.java">
 * https://github.com/zeromq/jeromq/blob/master/src/test/java/guide/asyncsrv.java</a>.<br>
 * In the 0MQ manual this example can be found at
 * <a href="http://zguide.zeromq.org/page:all#The-Asynchronous-Client-Server-Pattern">
 * http://zguide.zeromq.org/page:all#The-Asynchronous-Client-Server-Pattern</a>.
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class DealerRouter
{

    /** */
    private DealerRouter()
    {
        // utility class
    }

    /** random stream. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    static Random rand = new Random(System.nanoTime());

    /**
     * This is our client task. It connects to the server, and then sends a request once per second. It collects responses as
     * they arrive, and it prints them out. We will run several client tasks in parallel, each with a different random ID.
     */
    static class ClientTask implements Runnable
    {
        /** {@inheritDoc} */
        @Override
        public void run()
        {
            try (ZContext ctx = new ZContext())
            {
                Socket client = ctx.createSocket(SocketType.DEALER);

                // Set random identity to make tracing easier
                String identity = String.format("%04X-%04X", rand.nextInt(), rand.nextInt());
                client.setIdentity(identity.getBytes(ZMQ.CHARSET));
                client.connect("tcp://localhost:5570");

                Poller poller = ctx.createPoller(1);
                poller.register(client, Poller.POLLIN);

                int requestNr = 0;
                while (!Thread.currentThread().isInterrupted())
                {
                    // Tick once per second, pulling in arriving messages
                    for (int centitick = 0; centitick < 100; centitick++)
                    {
                        poller.poll(10);
                        if (poller.pollin(0))
                        {
                            ZMsg msg = ZMsg.recvMsg(client);
                            msg.getLast().print(identity);
                            msg.destroy();
                        }
                    }
                    client.send(String.format("request #%d", ++requestNr), 0);
                }
            }
        }
    }

    /**
     * This is our server task. It uses the multithreaded server model to deal requests out to a pool of workers and route
     * replies back to clients. One worker can handle one request at a time but one client can talk to multiple workers at once.
     */
    static class ServerTask implements Runnable
    {
        /** {@inheritDoc} */
        @Override
        public void run()
        {
            try (ZContext ctx = new ZContext())
            {
                // Frontend socket talks to clients over TCP
                Socket frontend = ctx.createSocket(SocketType.ROUTER);
                frontend.bind("tcp://*:5570");

                // Backend socket talks to workers over inproc
                Socket backend = ctx.createSocket(SocketType.DEALER);
                backend.bind("inproc://backend");

                // Launch pool of worker threads, precise number is not critical
                for (int threadNbr = 0; threadNbr < 5; threadNbr++)
                {
                    new Thread(new ServerWorker(ctx)).start();
                }

                // Connect backend to frontend via a proxy
                ZMQ.proxy(frontend, backend, null);
            }
        }
    }

    /**
     * Each worker task works on one request at a time and sends a random number of replies back, with random delays between
     * replies.
     */
    private static class ServerWorker implements Runnable
    {
        /** context. */
        private ZContext ctx;

        /**
         * Construct a worker.
         * @param ctx the 0MQ context to use
         */
        ServerWorker(final ZContext ctx)
        {
            this.ctx = ctx;
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            Socket worker = this.ctx.createSocket(SocketType.DEALER);
            worker.connect("inproc://backend");

            while (!Thread.currentThread().isInterrupted())
            {
                // The DEALER socket gives us the address envelope and message
                ZMsg msg = ZMsg.recvMsg(worker);
                ZFrame address = msg.pop();
                ZFrame content = msg.pop();
                assert (content != null);
                msg.destroy();

                // Send 0..4 replies back
                int replies = rand.nextInt(5);
                for (int reply = 0; reply < replies; reply++)
                {
                    // Sleep for some fraction of a second
                    try
                    {
                        Thread.sleep(rand.nextInt(1000) + 1);
                    }
                    catch (InterruptedException e)
                    {
                        // ignore
                    }
                    address.send(worker, ZFrame.REUSE + ZFrame.MORE);
                    content.send(worker, ZFrame.REUSE);
                }
                address.destroy();
                content.destroy();
            }
            this.ctx.destroy();
        }
    }

    /**
     * The main thread simply starts several clients, and a server, and then waits for the server to finish.
     * @param args unused
     * @throws Exception on error
     */
    public static void main(final String[] args) throws Exception
    {
        new Thread(new ClientTask()).start();
        new Thread(new ClientTask()).start();
        new Thread(new ClientTask()).start();
        new Thread(new ServerTask()).start();

        // Run for 20 seconds then quit
        Thread.sleep(20 * 1000);

        System.exit(0);
    }
}
