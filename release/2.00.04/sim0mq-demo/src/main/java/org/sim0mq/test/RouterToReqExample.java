package org.sim0mq.test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Example from http://stackoverflow.com/questions/20944140/zeromq-route-req-java-example-does-not-work. Added testing of send
 * messages for correctly sent messages, plus a monitor that checks whether one of the conversations hangs. The lazy pirate
 * pattern from the 0mq guide has been added to deal with possible timeouts.
 * <p>
 * BSD-style license. See <a href="http://opentrafficsim.org/docs/current/license.html">OpenTrafficSim License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Apr 20, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class RouterToReqExample
{
    /** random stream. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    static Random rand = new Random();

    /** static counter for worker. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    static AtomicInteger staticWorkerRecv = new AtomicInteger();

    /** static counter for broker identity. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    static AtomicInteger staticBrokerIdRecv = new AtomicInteger();

    /** static counter for broker Message. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    static AtomicInteger staticBrokerMsgRecv = new AtomicInteger();

    /** completed. */
    @SuppressWarnings("checkstyle:visibilitymodifier")
    static AtomicInteger completed = new AtomicInteger();

    /** how many worker threads? */
    private static final int NBR_WORKERS = 100;

    /** */
    private RouterToReqExample()
    {
        // Utility clss
    }

    /** a worker thread... */
    private static class Worker extends Thread
    {
        /** the worker id. */
        private String workerId;

        /**
         * Construct a worker.
         * @param workerId worker id
         */
        Worker(final String workerId)
        {
            this.workerId = workerId;
        }

        /** */
        private static final long TIMEOUT = 100; // ms
        
        /** */
        private static final int RETRIES = 3;

        @Override
        public void run()
        {
            String endpoint = "tcp://localhost:5671";

            ZContext context = new ZContext(1);
            ZMQ.Socket worker = context.createSocket(SocketType.REQ);
            worker.setIdentity(this.workerId.getBytes());

            worker.connect(endpoint);

            int total = 0;
            while (true)
            {
                staticWorkerRecv.incrementAndGet();
                String message = "Hi Boss";
                String workloadResponse = "";
                int retriesLeft = RETRIES;
                boolean ok = false;
                while (retriesLeft > 0 && !ok)
                {
                    // Tell the broker we're ready for work
                    if (!worker.send(message))
                    {
                        System.err.println("worker " + this.workerId + " failed to send...");
                    }
                    while (workloadResponse == null || workloadResponse.isEmpty())
                    {
                        ZMQ.Poller poller = context.createPoller(1);
                        poller.register(worker, ZMQ.Poller.POLLIN);
                        int signalled = poller.poll(TIMEOUT);
                        poller.unregister(worker);
                        if (signalled == 1)
                        {
                            workloadResponse = worker.recvStr();
                            // }
                            //
                            // // Poll socket for a reply, with timeout
                            // ZMQ.PollItem items[] = { new ZMQ.PollItem(worker, ZMQ.Poller.POLLIN) };
                            // int rc = ZMQ.poll(items, TIMEOUT);
                            // if (rc == -1)
                            // {
                            // break; // Interrupted
                            // }
                            //
                            // // Here we process a server reply and exit our loop if the reply is valid. If we didn't a reply
                            // we close
                            // // the client socket and resend the request. We try a number of times before finally abandoning:
                            // if (items[0].isReadable())
                            // {
                            // workloadResponse = worker.recvStr();
                            if (workloadResponse == null)
                            {
                                break; // Interrupted
                            }
                            if (workloadResponse.equals("Work harder") || workloadResponse.equals("Fired!"))
                            {
                                retriesLeft = RETRIES;
                                ok = true;
                            }
                            else
                            {
                                System.err.printf("E: malformed reply from server: %s\n", workloadResponse);
                            }

                        }
                        else if (--retriesLeft == 0)
                        {
                            System.err.println("E: server seems to be offline, abandoning\n");
                            break;
                        }
                        else
                        {
                            System.err.println("W: no response from server, retrying\n");
                            // Old socket is confused; close it and open a new one
                            worker.close();
                            System.err.println("I: reconnecting to server\n");
                            worker = context.createSocket(SocketType.REQ);
                            worker.setIdentity(this.workerId.getBytes());
                            worker.connect(endpoint);
                            // Send message again, on new socket
                            worker.send(message);
                        }
                    }
                }
                staticWorkerRecv.decrementAndGet();
                boolean finished = workloadResponse.equals("Fired!");
                if (finished)
                {
                    completed.incrementAndGet();
                    System.out.printf(this.workerId + " completed: %d tasks\n", total);
                    break;
                }
                total++;

                // Do some random work
                try
                {
                    Thread.sleep(rand.nextInt(10) + 1);
                }
                catch (InterruptedException e)
                {
                    // ignore
                }
            }

            worker.close();
            context.destroy();
            context.close();
        }
    }

    /**
     * While this example runs in a single process, that is just to make it easier to start and stop the example. Each thread
     * has its own context and conceptually acts as a separate process.
     * @param args args, can be empty
     * @throws Exception on error
     */
    public static void main(final String[] args) throws Exception
    {
        ZContext context = new ZContext(1);
        ZMQ.Socket broker = context.createSocket(SocketType.ROUTER);
        broker.bind("tcp://*:5671");

        System.out.println("Recv buf size = " + broker.getReceiveBufferSize());
        System.out.println("Send buf size = " + broker.getSendBufferSize());
        System.out.println("Recv HWM      = " + broker.getRcvHWM());
        System.out.println("Send HWM      = " + broker.getSndHWM());

        // starting all workers
        for (int workerNbr = 0; workerNbr < NBR_WORKERS; workerNbr++)
        {
            Thread worker = new Worker("worker-" + workerNbr);
            worker.start();
        }

        // start a monitoring thread of 6 seconds to check hanging program...
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(6000);
                    System.err.println("staticWorkerRecv    = " + staticWorkerRecv);
                    System.err.println("staticBrokerIdRecv  = " + staticBrokerIdRecv);
                    System.err.println("staticBrokerMsgRecv = " + staticBrokerMsgRecv);
                    System.exit(-1);
                }
                catch (InterruptedException exception)
                {
                    // ignore
                }
            }
        }.start();

        // Run for five seconds and then tell workers to end
        long endTime = System.currentTimeMillis() + 5000;
        int workersFired = 0;
        while (true)
        {
            // Next message gives us least recently used worker
            staticBrokerIdRecv.incrementAndGet();
            String identity = broker.recvStr(); // recvStringWithTimeout(broker, 100, "Work harder");
            staticBrokerIdRecv.decrementAndGet();
            if (!broker.sendMore(identity))
            {
                System.err.println("broker failed to send identity...");
            }
            if (!identity.equals("FAILED"))
            {
                staticBrokerMsgRecv.incrementAndGet();
                broker.recvStr(); // Envelope delimiter
                broker.recvStr(); // Response from worker
                staticBrokerMsgRecv.decrementAndGet();
                if (!broker.sendMore(""))
                {
                    System.err.println("broker failed to send delimiter...");
                }

                // Encourage workers until it's time to fire them
                if (System.currentTimeMillis() < endTime)
                {
                    if (!broker.send("Work harder"))
                    {
                        System.err.println("broker failed to send work...");
                    }
                }
                else
                {
                    if (!broker.send("Fired!"))
                    {
                        System.err.println("broker failed to send fired...");
                    }
                    if (++workersFired == NBR_WORKERS)
                    {
                        break;
                    }
                }
            }
        }

        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException exception)
        {
            // ignore
        }

        System.out.println("completed = " + completed);
        System.err.println("staticWorkerRecv    = " + staticWorkerRecv);
        System.err.println("staticBrokerIdRecv  = " + staticBrokerIdRecv);
        System.err.println("staticBrokerMsgRecv = " + staticBrokerMsgRecv);

        broker.close();
        context.destroy();
        context.close();
        System.exit(0);
    }

    /**
     * @param context the context
     * @param socket the socket
     * @param timeoutMs timeout in milliseconds
     * @param resend string to resend if it fails
     * @return the read string after potential resending of the request or even reconnecting
     */
    static String recvStringWithTimeout(final ZContext context, final ZMQ.Socket socket, final long timeoutMs,
            final String resend)
    {
        for (int i = 0; i < 5; i++)
        {
            ZMQ.Poller poller = context.createPoller(1);
            poller.register(socket, ZMQ.Poller.POLLIN);
            int signalled = poller.poll(timeoutMs);
            poller.unregister(socket);
            if (signalled == 1)
            {
                return socket.recvStr();
            }
            System.err.println("RETRY... " + resend);
            if (!socket.send(resend))
            {
                System.err.println("broker failed to resend string");
            }
        }
        System.err.println("FAILED");
        return "FAILED";
    }
}
