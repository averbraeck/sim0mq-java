package org.sim0mq.demo;

import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Client example for JeroMQ / ZeroMQ.
 * <p>
 * (c) copyright 2015-2020 <a href="http://www.simulation.tudelft.nl">Delft University of Technology</a>. <br>
 * BSD-style license. See <a href="http://www.simulation.tudelft.nl/dsol/3.0/license.html">DSOL License</a>. <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @version Oct 21, 2016
 */
public final class Client
{
    /** */
    private Client()
    {
        // Utility clss
    }
    
    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    public static void main(final String[] args) throws Sim0MQException, SerializationException
    {
        ZContext context = new ZContext(1);

        // Socket to talk to server
        System.out.println("Connecting to server...");

        ZMQ.Socket requester = context.createSocket(SocketType.REQ);
        requester.connect("tcp://localhost:5556");
        // requester.connect("tcp://131.180.98.169:5556");
        // requester.connect("tcp://130.161.3.179:5556");

        // send a reply
        Object[] request = new Object[] { "test message", new Double(14.2), new Float(-28.4), new Short((short) 10) };
        requester.send(Sim0MQMessage.encodeUTF8(true, "IDVV14.2", "MC.1", "MM1.4", "TEST.2", 1201L, request),
                0);

        byte[] reply = requester.recv(0);
        Object[] replyMessage = Sim0MQMessage.decode(reply).createObjectArray();
        System.out.println("Received\n" + Sim0MQMessage.print(replyMessage));

        requester.close();
        context.destroy();
        context.close();
    }

}
