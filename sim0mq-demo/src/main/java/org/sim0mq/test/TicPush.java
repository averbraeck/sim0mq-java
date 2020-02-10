package org.sim0mq.test;

import java.util.Arrays;

import org.sim0mq.Sim0MQException;
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
public final class TicPush
{
    /** */
    private TicPush()
    {
        // Utility Class.
    }
    
    /**
     * @param args command line arguments
     * @throws Sim0MQException on error
     */
    public static void main(final String[] args) throws Sim0MQException
    {
        long time = System.currentTimeMillis();
        try (ZContext context = new ZContext(1))
        {
            // Socket to talk to server
            System.out.println("Connecting to server on port 5556...");

            ZMQ.Socket requester = context.createSocket(SocketType.REQ);
            requester.connect("tcp://localhost:5556");

            for (int i = 0; i < 100000; i++)
            {
                // send a request
                byte[] message = string2byte("TIC");
                requester.send(message, 0);

                // wait for reply
                byte[] reply = requester.recv(0);
                String rs = byte2string(reply);
                if (!rs.equals("TOC"))
                {
                    System.err.println("Answer was not TOC");
                }
            }

            // send stop
            byte[] message = string2byte("STOP");
            requester.send(message, 0);

            requester.close();
            context.destroy();
        }
        System.out.println("RUNTIME = " + (System.currentTimeMillis() - time) + " ms");
    }

    /**
     * Turn String into byte array with closing zero.
     * @param s the input string
     * @return byte array with closing zero byte
     */
    public static byte[] string2byte(final String s)
    {
        byte[] b = s.getBytes();
        return Arrays.copyOf(b, b.length + 1);
    }

    /**
     * Turn byte array with closing zero into String.
     * @param b the byte array with closing zero byte
     * @return String without closing zero
     */
    public static String byte2string(final byte[] b)
    {
        return new String(Arrays.copyOf(b, b.length - 1));
    }

}