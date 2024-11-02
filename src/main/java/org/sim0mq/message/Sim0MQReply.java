package org.sim0mq.message;

import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;

/**
 * The abstract body of a reply message with the first fields of every Sim0MQ reply message.
 * <p>
 * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Apr 22, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public abstract class Sim0MQReply extends Sim0MQMessage
{
    /** */
    private static final long serialVersionUID = 20170422L;

    /** The unique message id (Frame 5) of the sender for which this is the reply. */
    private final Object replyToId;

    /**
     * Encode the object array into a message.
     * @param bigEndian boolean; Indicates whether this message using little endian or big endian encoding. Big endian is
     *            encoded as true, and little endian as false.
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageTypeId Message type ids can be defined per type of simulation, and can be provided in different types.
     *            Examples are a String with a meaningful identification, or a short or an int with a message type number.
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param payload Object[]; Payload as an Object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public Sim0MQReply(final boolean bigEndian, final Object federationId, final Object senderId, final Object receiverId,
            final Object messageTypeId, final Object messageId, final Object[] payload)
            throws Sim0MQException, NullPointerException
    {
        super(bigEndian, federationId, senderId, receiverId, messageTypeId, messageId, payload);
        Throw.when(payload.length < 1, Sim0MQException.class, "payload for a reply should start with the replyToId");
        this.replyToId = payload[0];
    }

    /**
     * Encode the object array into a message. <br>
     * 0 = magic number, equal to the String "SIM##" where ## stands for the version number of the protocol.<br>
     * 1 = endianness, boolean indicating the endianness for the message. True is Big Endian, false is Little Endian.<br>
     * 2 = federation id, could be String, int, Object, ...<br>
     * 3 = sender id, could be String, int, Object, ...<br>
     * 4 = receiver id, could be String, int, Object, ...<br>
     * 5 = message type id, could be String, int, Object, ...<br>
     * 6 = message id, could be long, Object, String, ....<br>
     * 7 = number of fields that follow, as a Number (byte, short, int, long).<br>
     * 8-n = payload, where the number of fields was defined by message[7]. The first payload field should be the replyToId.
     * @param objectArray Object[]; Full message object array
     * @param expectedNumberOfPayloadFields int; the expected number of fields in the message (field 8 and further). The payload
     *            fields should include the replyToId, so the number should be 1 or higher.
     * @param expectedMessageTypeId the expected message type id
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public Sim0MQReply(final Object[] objectArray, final int expectedNumberOfPayloadFields, final Object expectedMessageTypeId)
            throws Sim0MQException, NullPointerException
    {
        super(objectArray, expectedNumberOfPayloadFields, expectedMessageTypeId);
        Throw.when(objectArray.length < 9, Sim0MQException.class, "payload for a reply should start with the replyToId");
        this.replyToId = objectArray[8];
    }

    /**
     * @return replyToId
     */
    public final Object getReplyToId()
    {
        return this.replyToId;
    }

    /**
     * Builder for the Sim0MQReply. Can string setters together, and call build() at the end to build the actual message.
     * <p>
     * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
     * initial version Apr 22, 2017 <br>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     * @param <B> the actual inherited builder for the return types.
     */
    public abstract static class Builder<B extends Sim0MQMessage.Builder<B>> extends Sim0MQMessage.Builder<B>
    {
        /** The unique message id (Frame 5) of the sender for which this is the reply. */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        protected Object replyToId;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newReplyToId set replyToId
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        public final B setReplyToId(final Object newReplyToId)
        {
            this.replyToId = newReplyToId;
            return (B) this;
        }

        /**
         * @param message set replyToId and receiver based on the message to which this is a reply
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        public final B setReplyTo(final Sim0MQMessage message)
        {
            this.replyToId = message.getMessageId();
            this.receiverId = message.getSenderId();
            return (B) this;
        }

        @Override
        public abstract Sim0MQReply build() throws Sim0MQException, NullPointerException;

    }
}
