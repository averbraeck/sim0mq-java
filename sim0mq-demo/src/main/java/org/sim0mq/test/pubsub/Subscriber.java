package org.sim0mq.test.pubsub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * The PUB-SUB pattern takes place on port 9001. Port 9002 is used in a PAIR-PAIR setting to start the publisher when the
 * subscriber comes on-line and terminate the publisher after the subscriber has received 100 messages. The message can be coded
 * as one string, but in this casse it is coded as two separate frames, where the first frame is the subscription topic.
 * <p>
 * Copyright (c) 2013-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version 26 Apr 2020 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class Subscriber
{
    /** */
    private Subscriber()
    {
        // utility class
    }

    /**
     * @param args the command line arguments. The first argument is the subscription key. If it is empty, 12345 will be used.
     */
    public static void main(final String[] args)
    {
        try (ZContext ctx = new ZContext())
        {
            ZMQ.Socket subscriber = ctx.createSocket(SocketType.SUB);
            ZMQ.Socket flowcontrol = ctx.createSocket(SocketType.PAIR);
            subscriber.connect("tcp://localhost:9001"); // communicate PUB-SUB on port 9001
            flowcontrol.connect("tcp://localhost:9002"); // communicate with PAIR-PAIR for start/end on port 9002
            String subscriptionTopic = args.length < 1 ? "12345" : args[0];
            subscriber.subscribe(subscriptionTopic);
            int count = 0;
            flowcontrol.send("START");
            while (true)
            {
                String topic = subscriber.recvStr().trim();
                String message = subscriber.recvStr().trim();
                count++;
                System.out.println(String.format("%3d %s : %s", count, topic, message));
                if (count >= 100)
                {
                    flowcontrol.send("END");
                    break;
                }
            }
        }
    }
}
