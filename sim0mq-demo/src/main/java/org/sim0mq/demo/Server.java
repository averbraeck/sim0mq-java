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
 * (c) copyright 2015-2019 <a href="http://www.simulation.tudelft.nl">Delft University of Technology</a>. <br>
 * BSD-style license. See <a href="http://www.simulation.tudelft.nl/dsol/3.0/license.html">DSOL License</a>. <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @version Oct 21, 2016
 */
public final class Server
{
    /** */
    private Server()
    {
        // Utility class
    }
    
    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    public static void main(final String[] args) throws Sim0MQException, SerializationException
    {
        ZContext context = new ZContext(1);

        // Socket to talk to clients
        ZMQ.Socket responder = context.createSocket(SocketType.REP);
        responder.bind("tcp://*:5556");

        while (!Thread.currentThread().isInterrupted())
        {
            // Wait for next request from the client
            byte[] request = responder.recv(0);
            Object[] message = Sim0MQMessage.decode(request);
            System.out.println("Received " + Sim0MQMessage.print(message));

            // send a reply
            Object[] reply = new Object[] { true, -28.2, 77000, "Bangladesh" };
            responder.send(Sim0MQMessage.encodeUTF8("IDVV14.2", "MC.1", "MM1.4", "TEST.2", 1201L, reply),
                    0);
        }
        responder.close();
        context.destroy();
        context.close();
    }
}
