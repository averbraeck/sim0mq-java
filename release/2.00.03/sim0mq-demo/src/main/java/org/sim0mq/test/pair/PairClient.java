package org.sim0mq.test.pair;

import java.util.Scanner;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * Client-server implementation in 0MQ using the PAIR implementation. This involves exactly one client and one server that can
 * chat with each other independent of the order of the messages. Text is read from stdin. This is the client. It can be started
 * before or after the server. Any messages already created will be buffered and will not get lost.
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version 25 Apr 2020 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class PairClient
{
    /**
     * @param port port nr
     * @param clientname name
     */
    private PairClient(final int port, final String clientname)
    {
        ZContext ctx = new ZContext();
        ZMQ.Socket socket = ctx.createSocket(SocketType.PAIR);
        socket.connect("tcp://localhost:" + port);
        new ListenThread(socket).start();
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            String line = scanner.nextLine();
            if (line.isEmpty())
            {
                break;
            }
            socket.send(String.format("%s: %s", clientname, line));
        }
        ctx.close();
        ctx.destroy();
        scanner.close();
        System.exit(0);
    }

    /**
     * @param args pairserver_port name
     */
    public static void main(final String[] args)
    {
        String port = args.length < 1 ? "9001" : args[0];
        String name = args.length < 2 ? "client" : args[1];
        new PairClient(Integer.valueOf(port), name);
    }

    /** */
    static class ListenThread extends Thread
    {
        /** */
        private ZMQ.Socket socket;

        /**
         * @param socket socket
         */
        ListenThread(final ZMQ.Socket socket)
        {
            super();
            this.socket = socket;
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            while (true)
            {
                String msg = this.socket.recvStr();
                if (!msg.isEmpty())
                {
                    System.out.println(msg);
                }
            }
        }

    }
}
