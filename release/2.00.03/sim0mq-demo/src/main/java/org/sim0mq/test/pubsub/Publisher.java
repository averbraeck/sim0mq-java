package org.sim0mq.test.pubsub;

import java.util.Random;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * The PUB-SUB pattern takes place on port 9001. Port 9002 is used in a PAIR-PAIR setting to start the publisher when the
 * subscriber comes on-line and terminate the publisher after the subscriber has received 100 messages. The message can be sent
 * as one string, but it is better to send the message as two separate frames, where the first frame is the subscription topic.
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version 26 Apr 2020 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class Publisher
{
    /** */
    private Publisher()
    {
        // utility class.
    }

    /**
     * @param args the command line arguments. Empty in this case.
     */
    public static void main(final String[] args)
    {
        try (ZContext ctx = new ZContext())
        {
            ZMQ.Socket publisher = ctx.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:9001"); // set-up PUB connection on port 9001

            ZMQ.Socket flowcontrol = ctx.createSocket(SocketType.PAIR);
            flowcontrol.bind("tcp://*:9002"); // communicate with PAIR-PAIR for ending on port 9002

            // wait for the subscriber to get on-line
            flowcontrol.recvStr(0); // blocking

            // the topic is a random number between 0 and 99999
            // the payload is a random number between 1000-9999
            Random rand = new Random();
            int nrMessages = 0;
            while (true)
            {
                String topic = String.valueOf(rand.nextInt(100_000));
                String content = String.valueOf(1000 + rand.nextInt(9000));
                publisher.sendMore(topic); // frame 1 is the topic, which will be used in the subscription
                publisher.send(content); // frame 2 is the content
                if (++nrMessages % 100_000 == 0)
                {
                    System.out.println(String.format("# messages sent: %,d", nrMessages));
                }
                if ("END".equals(flowcontrol.recvStr(ZMQ.DONTWAIT)))
                {
                    break;
                }
            }
        }
    }
}
