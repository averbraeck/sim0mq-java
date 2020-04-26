package org.sim0mq.demo;

import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Server example for JeroMQ / ZeroMQ.
 * <p>
 * (c) copyright 2015-2020 <a href="http://www.simulation.tudelft.nl">Delft University of Technology</a>. <br>
 * BSD-style license. See <a href="http://www.simulation.tudelft.nl/dsol/3.0/license.html">DSOL License</a>. <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @version Oct 21, 2016
 */
public final class Server
{
    /** */
    private ZContext context;

    /** the socket. */
    private ZMQ.Socket responder;

    /** */
    public Server()
    {
        this.context = new ZContext(1);

        // Socket to talk to clients
        this.responder = this.context.createSocket(SocketType.REP);
        this.responder.bind("tcp://*:5556");

        new Worker(this).start();
    }

    /**
     * @return context
     */
    public ZContext getContext()
    {
        return this.context;
    }

    /**
     * @return responder
     */
    public ZMQ.Socket getResponder()
    {
        return this.responder;
    }

    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    public static void main(final String[] args) throws Sim0MQException, SerializationException
    {
        new Server();
    }

    /** */
    protected static class Worker extends Thread
    {
        /** */
        private Server server;

        /**
         * Constructor.
         * @param server the server
         */
        public Worker(final Server server)
        {
            this.server = server;
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            while (!Thread.currentThread().isInterrupted())
            {
                try
                {
                    // Wait for next request from the client
                    byte[] request = this.server.getResponder().recv(0);
                    Object[] message = Sim0MQMessage.decode(request).createObjectArray();
                    System.out.println("Received " + Sim0MQMessage.print(message));

                    // send a reply
                    Object[] reply = new Object[] {true, -28.2, 77000, "Bangladesh"};
                    this.server.getResponder()
                            .send(Sim0MQMessage.encodeUTF8(true, "IDVV14.2", "MC.1", "MM1.4", "TEST.2", 1201L, reply), 0);
                }
                catch (Sim0MQException | SerializationException e)
                {
                    e.printStackTrace();
                }
            }
            this.server.getResponder().close();
            this.server.getContext().destroy();
            this.server.getContext().close();
        }

    }
}
