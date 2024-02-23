package org.sim0mq.message.heartbeat;

import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.Sim0MQReply;

/**
 * Alive Message, HB.2. A federate sends this message as a response to Heartbeat messages sent by the Federate Starter or the
 * Federation Manager.
 * <p>
 * Copyright (c) 2019-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class HB2AliveMessage extends Sim0MQReply
{
    /** the unique message id. */
    private static final String MESSAGETYPE = "HB.2";

    /** */
    private static final long serialVersionUID = 20190713L;

    /**
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param uniqueId Id to identify the callback to the message.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public HB2AliveMessage(final Object federationId, final Object senderId, final Object receiverId, final Object messageId,
            final Object uniqueId) throws Sim0MQException, NullPointerException
    {
        this(new Object[] {Sim0MQMessage.VERSION, true, federationId, senderId, receiverId, MESSAGETYPE, messageId, 1,
                uniqueId});
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public HB2AliveMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 1, MESSAGETYPE);
    }

    /**
     * @return messagetype
     */
    public static final String getMessageType()
    {
        return MESSAGETYPE;
    }

    /**
     * Builder for the Alive Message. Can string setters together, and call build() at the end to build the actual message.
     * <p>
     * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQReply.Builder<HB2AliveMessage.Builder>
    {
        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /** {@inheritDoc} */
        @Override
        public HB2AliveMessage build() throws Sim0MQException, NullPointerException
        {
            return new HB2AliveMessage(this.federationId, this.senderId, this.receiverId, this.messageId, this.replyToId);
        }

    }
}
