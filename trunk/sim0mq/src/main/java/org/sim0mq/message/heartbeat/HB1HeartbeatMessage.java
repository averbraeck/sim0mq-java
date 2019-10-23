package org.sim0mq.message.heartbeat;

import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * Heartbeat, HB.1. Request to test if other party is still alive. From FM to FS, LB and MC, or from FS to MC.
 * <p>
 * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class HB1HeartbeatMessage extends Sim0MQMessage
{
    /** the unique message id. */
    private static final String MESSAGETYPE = "HB.1";

    /** */
    private static final long serialVersionUID = 20190714L;

    /**
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public HB1HeartbeatMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId) throws Sim0MQException, NullPointerException
    {
        super(true, federationId, senderId, receiverId, MESSAGETYPE, messageId, null);
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public HB1HeartbeatMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 0);
    }

    /**
     * Builder for the Heartbeat Message. Can string setters together, and call build() at the end to build the actual message.
     * <p>
     * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<HB1HeartbeatMessage.Builder>
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
        public HB1HeartbeatMessage build() throws Sim0MQException, NullPointerException
        {
            return new HB1HeartbeatMessage(this.federationId, this.senderId, this.receiverId, this.messageId);
        }

    }
}
