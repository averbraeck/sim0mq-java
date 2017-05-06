package org.sim0mq.test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.zeromq.ZMQ;

/**
 * Example from http://stackoverflow.com/questions/20944140/zeromq-route-req-java-example-does-not-work.
 * <p>
 * BSD-style license. See <a href="http://opentrafficsim.org/docs/current/license.html">OpenTrafficSim License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Apr 20, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class RouterToReqExample
{
    /** random stream. */
    static Random rand = new Random();
    
    /** static counter for worker. */
    static AtomicInteger staticWorkerRecv = new AtomicInteger();

    /** static counter for broker identity. */
    static AtomicInteger staticBrokerIdRecv = new AtomicInteger();

    /** static counter for broker Message. */
    static AtomicInteger staticBrokerMsgRecv = new AtomicInteger();

    /** how many worker threads? */
    private static final int NBR_WORKERS = 20;

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

        @Override
        public void run()
        {
            ZMQ.Context context = ZMQ.context(1);
            ZMQ.Socket worker = context.socket(ZMQ.REQ);
            worker.setIdentity(this.workerId.getBytes());

            worker.connect("tcp://localhost:5671");

            int total = 0;
            while (true)
            {
                // Tell the broker we're ready for work
                staticWorkerRecv.incrementAndGet();
                if (!worker.send("Hi Boss"))
                {
                    System.err.println("worker " + this.workerId + " failed to send...");
                }

                // Get workload from broker, until finished
                
                String workload = worker.recvStr();
                staticWorkerRecv.decrementAndGet();
                boolean finished = workload.equals("Fired!");
                if (finished)
                {
                    System.out.printf(this.workerId + " completed: %d tasks\n", total);
                    break;
                }
                total++;

                // Do some random work
                try
                {
                    Thread.sleep(rand.nextInt(50) + 1);
                }
                catch (InterruptedException e)
                {
                    // ignore
                }
            }

            worker.close();
            context.term();
        }
    }

    /**
     * While this example runs in a single process, that is just to make it easier to start and stop the example. Each thread
     * has its own context and conceptually acts as a separate process.
     * @param args args, can be empty
     * @throws Exception on error
     */
    public static void main(String[] args) throws Exception
    {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket broker = context.socket(ZMQ.ROUTER);
        broker.bind("tcp://*:5671");

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
            String identity = broker.recvStr();
            staticBrokerIdRecv.decrementAndGet();
            if (!broker.sendMore(identity))
            {
                System.err.println("broker failed to send identity...");
            }
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

        broker.close();
        context.term();
        System.exit(0);
    }

}
