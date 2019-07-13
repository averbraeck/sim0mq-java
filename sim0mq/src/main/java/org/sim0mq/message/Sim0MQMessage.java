package org.sim0mq.message;

import java.io.Serializable;

import org.djutils.exceptions.Throw;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;

/**
 * The abstract body of the message with the first fields of every Sim0MQ message.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Apr 22, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public abstract class Sim0MQMessage implements Serializable
{
    /** */
    private static final long serialVersionUID = 20170422L;

    /** version of the protocol, magic number. */
    protected static final String VERSION = "SIM01";

    /**
     * the Simulation run ids can be provided in different types. Examples are two 64-bit longs indicating a UUID, or a String
     * with a UUID number, a String with meaningful identification, or a short or an int with a simulation run number.
     */
    private final Object simulationRunId;

    /** The sender id can be used to send back a message to the sender at some later time. */
    private final Object senderId;

    /**
     * The receiver id can be used to check whether the message is meant for us, or should be discarded (or an error can be sent
     * if we receive a message not meant for us).
     */
    private final Object receiverId;

    /**
     * Message type ids can be defined per type of simulation, and can be provided in different types. Examples are a String
     * with a meaningful identification, or a short or an int with a message type number.
     */
    private final Object messageTypeId;

    /**
     * The unique message number is meant to confirm with a callback that the message has been received correctly. The number is
     * unique for the sender, so not globally within the federation.
     */
    private final long messageId;

    /**
     * Three different status messages are defined: 1 for new, 2 for change, and 3 for delete. This field is coded as a byte.
     */
    private final MessageStatus messageStatus;

    /**
     * Encode the object array into a message.
     * @param simulationRunId the Simulation run ids can be provided in different types. Examples are two 64-bit longs
     *            indicating a UUID, or a String with a UUID number, a String with meaningful identification, or a short or an
     *            int with a simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageTypeId Message type ids can be defined per type of simulation, and can be provided in different types.
     *            Examples are a String with a meaningful identification, or a short or an int with a message type number.
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param messageStatus Three different status messages are defined: 1 for new, 2 for change, and 3 for delete. This field
     *            is coded as a byte.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public Sim0MQMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final Object messageTypeId, final long messageId, final MessageStatus messageStatus)
            throws Sim0MQException, NullPointerException
    {
        Throw.whenNull(simulationRunId, "simulationRunId cannot be null");
        Throw.whenNull(senderId, "senderId cannot be null");
        Throw.whenNull(receiverId, "receiverId cannot be null");
        Throw.whenNull(messageTypeId, "messageTypeId cannot be null");
        Throw.whenNull(messageId, "messageId cannot be null");
        Throw.whenNull(messageStatus, "messageStatus cannot be null");

        this.simulationRunId = simulationRunId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageTypeId = messageTypeId;
        this.messageId = messageId;
        this.messageStatus = messageStatus;
    }

    /**
     * @return Magic number = |9|0|0|0|5|S|I|M|#|#| where ## stands for the version number, e.g., 01. Internally, the magic
     *         number is always coded as a UTF-8 String, so it always starts with a byte equal to 9.
     */
    public final Object getMagicNumber()
    {
        return VERSION;
    }

    /**
     * @return simulationRunId
     */
    public final Object getSimulationRunId()
    {
        return this.simulationRunId;
    }

    /**
     * @return senderId
     */
    public final Object getSenderId()
    {
        return this.senderId;
    }

    /**
     * @return receiverId
     */
    public final Object getReceiverId()
    {
        return this.receiverId;
    }

    /**
     * @return messageTypeId
     */
    public final Object getMessageTypeId()
    {
        return this.messageTypeId;
    }

    /**
     * @return messageId
     */
    public final long getMessageId()
    {
        return this.messageId;
    }

    /**
     * @return messageStatus
     */
    public final MessageStatus getMessageStatus()
    {
        return this.messageStatus;
    }

    /**
     * Create a Sim0MQ object array of the fields.
     * @return Object[] a Sim0MQ object array of the fields
     */
    public abstract Object[] createObjectArray();

    /**
     * Create a byte array of the fields.
     * @return byte[] a Sim0MQ byte array of the content
     * @throws Sim0MQException on unknown data type as part of the content
     * @throws SerializationException when the byte array cannot be created, e.g. because the number of bytes does not match
     */
    public abstract byte[] createByteArray() throws Sim0MQException, SerializationException;

    /**
     * Get the number of payload fields in the message.
     * @return short; the number of payload fields in the message.
     */
    public abstract short getNumberOfPayloadFields();

    /**
     * Check the consistency of a message from an Object[] that was received.
     * @param fields Object[]; the fields in the message
     * @param expectedPayloadFields the expected number of payload fields
     * @param expectedMessageType the expected message type
     * @param intendedReceiverId id of the intended receiver
     * @throws Sim0MQException when errors in the message have been detected
     */
    public static void check(final Object[] fields, final int expectedPayloadFields, final String expectedMessageType,
            final Object intendedReceiverId) throws Sim0MQException
    {
        Throw.when(fields.length != expectedPayloadFields + 8, Sim0MQException.class,
                "Message " + expectedMessageType + " does not contain the right number of fields. " + "Expected: "
                        + (expectedPayloadFields + 8) + ", Actual: " + fields.length);

        for (int i = 0; i < fields.length; i++)
        {
            Object field = fields[i];
            if (field == null)
            {
                throw new Sim0MQException("Message " + expectedMessageType + " field " + i + " equals null");
            }
        }

        Throw.when(!expectedMessageType.equals(fields[4].toString()), Sim0MQException.class,
                "Message type not right -- should have been " + expectedMessageType);

        Throw.when(!fields[3].equals(intendedReceiverId), Sim0MQException.class,
                "Receiver in message of type " + expectedMessageType + " not right. Should have been: " + intendedReceiverId);

        Throw.when(!(fields[7] instanceof Short), Sim0MQException.class,
                "Message " + expectedMessageType + " does not have a short field[7] for the number of fields");
        Throw.when(((Short) fields[7]).intValue() != expectedPayloadFields, Sim0MQException.class,
                "Message " + expectedMessageType + " does not contain the right number of payload fields in field[7]");
    }

    /**
     * Builder for the Sim0MQMessage. Can string setters together, and call build() at the end to build the actual message.
     * <p>
     * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
     * initial version Apr 22, 2017 <br>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     * @param <B> the actual inherited builder for the return types.
     */
    public abstract static class Builder<B extends Sim0MQMessage.Builder<B>>
    {
        /**
         * the Simulation run ids can be provided in different types. Examples are two 64-bit longs indicating a UUID, or a
         * String with a UUID number, a String with meaningful identification, or a short or an int with a simulation run
         * number.
         */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        protected Object simulationRunId;

        /** The sender id can be used to send back a message to the sender at some later time. */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        protected Object senderId;

        /**
         * The receiver id can be used to check whether the message is meant for us, or should be discarded (or an error can be
         * sent if we receive a message not meant for us).
         */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        protected Object receiverId;

        /**
         * Message type ids can be defined per type of simulation, and can be provided in different types. Examples are a String
         * with a meaningful identification, or a short or an int with a message type number.
         */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        protected Object messageTypeId;

        /**
         * The unique message number is meant to confirm with a callback that the message has been received correctly. The
         * number is unique for the sender, so not globally within the federation.
         */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        protected long messageId;

        /**
         * Three different status messages are defined: 1 for new, 2 for change, and 3 for delete. This field is coded as a
         * byte.
         */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        protected MessageStatus messageStatus;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newSimulationRunId set simulationRunId
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        public final B setSimulationRunId(final Object newSimulationRunId)
        {
            this.simulationRunId = newSimulationRunId;
            return (B) this;
        }

        /**
         * @param newSenderId set senderId
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        public final B setSenderId(final Object newSenderId)
        {
            this.senderId = newSenderId;
            return (B) this;
        }

        /**
         * @param newReceiverId set receiverId
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        public final B setReceiverId(final Object newReceiverId)
        {
            this.receiverId = newReceiverId;
            return (B) this;
        }

        /**
         * @param newMessageTypeId set messageTypeId
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        protected final B setMessageTypeId(final Object newMessageTypeId)
        {
            this.messageTypeId = newMessageTypeId;
            return (B) this;
        }

        /**
         * @param newMessageId set messageId
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        public final B setMessageId(final long newMessageId)
        {
            this.messageId = newMessageId;
            return (B) this;
        }

        /**
         * @param newMessageStatus set messageStatus
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        protected final B setMessageStatus(final MessageStatus newMessageStatus)
        {
            this.messageStatus = newMessageStatus;
            return (B) this;
        }

        /**
         * Build the object.
         * @return the message object from the builder.
         * @throws Sim0MQException on unknown data type
         * @throws NullPointerException when one of the parameters is null
         */
        public abstract Sim0MQMessage build() throws Sim0MQException, NullPointerException;

    }
}
