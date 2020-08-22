package org.sim0mq.test;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;
import org.zeromq.ZThread;
import zmq.ZError;

import java.util.Scanner;

/**
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version 6 Mar 2020 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class ZmqChat
{
    /**
     * Decentralized chat example
     * @param args first arg is chat server, 2nd is us, 3rd is id
     */
    public static void main(String[] args)
    {
        if (args.length < 3)
        {
            System.out.println("Usage: ZmqChat ipaddress interface username");
            System.out.println("Example: ZmqChat 192.168.55.123 localhost joe");
            // System.exit(0);
            args = new String[] {"localhost", "localhost", "tud"}; 
        }
        ZContext ctx = new ZContext();

        // cut string after dot
        String addressWithoutLastPart = args[0].substring(0, args[0].lastIndexOf('.'));
        ZThread.fork(ctx, new ListenerTask(), addressWithoutLastPart);
        ZMQ.Socket broadcaster = ctx.createSocket(ZMQ.PUB);
        broadcaster.bind(String.format("tcp://%s:9000", args[1]));
        Scanner scanner = new Scanner(System.in);
        while (!Thread.currentThread().isInterrupted())
        {
            String line = scanner.nextLine();
            if (line.isEmpty())
                break;
            broadcaster.send(String.format("%s: %s", args[2], line));
        }
        ctx.destroy();
    }

    static class ListenerTask implements ZThread.IAttachedRunnable
    {

        @Override
        public void run(Object[] args, ZContext ctx, ZMQ.Socket pipe)
        {
            ZMQ.Socket listener = ctx.createSocket(ZMQ.SUB);
            int address;
            for (address = 1; address < 255; address++)
                listener.connect(String.format("tcp://%s.%d:9000", args[0], address));

            listener.subscribe(ZMQ.SUBSCRIPTION_ALL);
            while (!Thread.currentThread().isInterrupted())
            {
                String message;
                try
                {
                    message = listener.recvStr();
                }
                catch (ZMQException e)
                {
                    if (e.getErrorCode() == ZError.ETERM)
                        break;
                    e.printStackTrace();
                    break;
                }
                if (!message.isEmpty())
                    System.out.println(message);
            }
        }
    }

}
