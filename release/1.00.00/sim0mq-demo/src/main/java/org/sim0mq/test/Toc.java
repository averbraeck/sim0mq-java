package org.sim0mq.test;

import org.sim0mq.Sim0MQException;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Server example for JeroMQ / ZeroMQ.
 * <p>
 * (c) copyright 2002-2016 <a href="http://www.simulation.tudelft.nl">Delft University of Technology</a>. <br>
 * BSD-style license. See <a href="http://www.simulation.tudelft.nl/dsol/3.0/license.html">DSOL License</a>. <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @version Oct 21, 2016
 */
public final class Toc
{
    /** */
    private Toc()
    {
        // Utility Class.
    }
    
    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     */
    public static void main(final String[] args) throws Sim0MQException
    {
        try (ZContext context = new ZContext(1))
        {
            // Socket to talk to clients
            ZMQ.Socket responder = context.createSocket(ZMQ.REP);
            responder.bind("tcp://*:5556");

            while (true)
            {
                // Wait for next request from the client
                byte[] request = responder.recv(0);
                String rs = Tic.byte2string(request);
                if (rs.equals("STOP"))
                {
                    break;
                }
                if (!rs.equals("TIC"))
                {
                    System.err.println("Request was not TIC");
                }

                // send a reply
                byte[] reply = Tic.string2byte("TOC");
                responder.send(reply, 0);
            }
            responder.close();
            context.destroy();
        }
    }
}
