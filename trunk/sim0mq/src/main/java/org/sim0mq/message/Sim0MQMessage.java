package org.sim0mq.message;

import java.io.Serializable;

import org.djutils.exceptions.Throw;
import org.djutils.serialization.EndianUtil;
import org.djutils.serialization.SerializationException;
import org.djutils.serialization.TypedMessage;
import org.sim0mq.Sim0MQException;

/**
 * Sim0MQMessage contains the abstract body of the message with the first fields of every Sim0MQ message. The message structure
 * of a typical typed Sim0MQ simulation message looks as follows:
 * <ul>
 * <li>Frame 0. Magic number = |9|0|0|0|5|S|I|M|#|#| where ## stands for the version number, e.g., 01.</li>
 * <li>Frame 1. Federation id. Federation ids can be provided in different types. Examples are a 64-bit long, or a String with a
 * UUID number, a String with meaningful identification, or a short or an int with a simulation run number. In order to check
 * whether the right information has been received, the id can be translated to a String and compared with an internal string
 * representation of the required simulation run id. Typically we use a String to provide maximum freedom. In that case, the run
 * id can be coded as UTF-8 or UTF-16.</li>
 * <li>Frame 2. Sender id. Sender ids can be provided in different types. Examples are a 64-bit long, or a String with a UUID
 * number, a String with meaningful identification, or a short or an int with a sender id number. The sender id can be used to
 * send back a message to the sender at some later time. Typically we use a String to provide maximum freedom. In that case, the
 * sender id can be coded as UTF-8 or UTF-16.</li>
 * <li>Frame 3. Receiver id. Receiver ids can be provided in different types. Examples are a 64-bit long, or a String with a
 * UUID number, a String with meaningful identification, or a short or an int with a receiver id number. The receiver id can be
 * used to check whether the message is meant for us, or should be discarded (or an error can be sent if we receive a message
 * not meant for us). Typically we use a String to provide maximum freedom. In that case, the receiver id can be coded as UTF-8
 * or UTF-16.</li>
 * <li>Frame 4. Message type id. Message type ids can be defined per type of simulation, and can be provided in different types.
 * Examples are a String with a meaningful identification, or a short or an int with a message type number. For interoperability
 * between different types of simulation, a String id with dot-notation (e.g., DSOL.1 for a simulator start message from DSOL or
 * OTS.14 for a statistics message from OpenTrafficSim) would be preferred. In that case, the run id can be coded as UTF-8 or
 * UTF-16.</li>
 * <li>Frame 5. Unique message number. The unique message number will be sent as a long (64 bits), and is meant to confirm with
 * a callback that the message has been received correctly. The number is unique for the sender, so not globally within the
 * federation.</li>
 * <li>Frame 6. Number of fields. The number of fields in the payload is indicated to be able to check the payload and to avoid
 * reading past the end. The number of fields can be encoded using byte, short, or int. A 16-bit positive short (including zero)
 * is the standard encoding. It can also be an int or long, allowing for messages with a vast number of fields.</li>
 * <li>Frame 7-n. Payload, where each field has a 1-byte prefix denoting the type of field.</li>
 * </ul>
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * initial version Mar 3, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public abstract class Sim0MQMessage implements Serializable
{
    /** */
    private static final long serialVersionUID = 20191017L;

    /** version of the protocol, magic number. */
    protected static final String VERSION = "SIM02";

    /**
     * the federation id can be provided in different types. Examples are two 64-bit longs indicating a UUID, or a String with a
     * UUID number, a String with meaningful identification, or a byte, short or int with a simulation run number.
     */
    private final Object federationId;

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
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public Sim0MQMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final Object messageTypeId, final long messageId) throws Sim0MQException, NullPointerException
    {
        Throw.whenNull(simulationRunId, "simulationRunId cannot be null");
        Throw.whenNull(senderId, "senderId cannot be null");
        Throw.whenNull(receiverId, "receiverId cannot be null");
        Throw.whenNull(messageTypeId, "messageTypeId cannot be null");

        this.federationId = simulationRunId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageTypeId = messageTypeId;
        this.messageId = messageId;
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
        return this.federationId;
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
        Throw.when(fields.length != expectedPayloadFields + 7, Sim0MQException.class,
                "Message " + expectedMessageType + " does not contain the right number of fields. " + "Expected: "
                        + (expectedPayloadFields + 7) + ", Actual: " + fields.length);

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

        Throw.when(!(fields[6] instanceof Number), Sim0MQException.class,
                "Message " + expectedMessageType + " does not have a Number field[6] for the number of fields");
        Throw.when(((Number) fields[6]).longValue() != expectedPayloadFields, Sim0MQException.class,
                "Message " + expectedMessageType + " does not contain the right number of payload fields in field[6]");
    }

    /* ******************************************************************************************************* */
    /* ************************************ STATIC METHODS TO BUILD A MESSAGE ******************************** */
    /* ******************************************************************************************************* */

    /**
     * Encode the object array into a message. Use UTF8 or UTF16 to code Strings.
     * @param stringEncoding choice to use Use UTF8 or UTF16 to code Strings
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    @SuppressWarnings("checkstyle:parameternumber")
    private static byte[] encode(final StringEncoding stringEncoding, final Object simulationRunId, final Object senderId,
            final Object receiverId, final Object messageTypeId, final long messageId, final Object... content)
            throws Sim0MQException, SerializationException
    {
        Object[] simulationContent = new Object[content.length + 7];
        simulationContent[0] = Sim0MQMessage.VERSION;
        simulationContent[1] = simulationRunId;
        simulationContent[2] = senderId;
        simulationContent[3] = receiverId;
        simulationContent[4] = messageTypeId;
        simulationContent[5] = messageId;
        if (content.length < Short.MAX_VALUE)
        {
            simulationContent[6] = new Short((short) content.length);
        }
        else
        {
            simulationContent[6] = new Integer(content.length);
        }
        for (int i = 0; i < content.length; i++)
        {
            simulationContent[i + 7] = content[i];
        }
        return stringEncoding.isUTF8() ? TypedMessage.encodeUTF8(EndianUtil.BIG_ENDIAN, simulationContent)
                : TypedMessage.encodeUTF16(EndianUtil.BIG_ENDIAN, simulationContent);
    }

    /**
     * Encode the object array into a message. Use UTF8 or UTF16 to code Strings.
     * @param stringEncoding choice to use Use UTF8 or UTF16 to code Strings
     * @param identity the identity of the federate to which this is the reply
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    @SuppressWarnings("checkstyle:parameternumber")
    private static byte[] encodeReply(final StringEncoding stringEncoding, final String identity, final Object simulationRunId,
            final Object senderId, final Object receiverId, final Object messageTypeId, final long messageId,
            final Object... content) throws Sim0MQException, SerializationException
    {
        Object[] simulationContent = new Object[content.length + 9];
        simulationContent[0] = identity;
        simulationContent[1] = new byte[] {0};
        simulationContent[2] = Sim0MQMessage.VERSION;
        simulationContent[3] = simulationRunId;
        simulationContent[4] = senderId;
        simulationContent[5] = receiverId;
        simulationContent[6] = messageTypeId;
        simulationContent[7] = messageId;
        if (content.length < Short.MAX_VALUE)
        {
            simulationContent[8] = new Short((short) content.length);
        }
        else
        {
            simulationContent[8] = new Integer(content.length);
        }
        for (int i = 0; i < content.length; i++)
        {
            simulationContent[i + 9] = content[i];
        }
        return stringEncoding.isUTF8() ? TypedMessage.encodeUTF8(EndianUtil.BIG_ENDIAN, simulationContent)
                : TypedMessage.encodeUTF16(EndianUtil.BIG_ENDIAN, simulationContent);
    }

    /**
     * Encode the object array into a message. Use UTF8 to code Strings.
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    public static byte[] encodeUTF8(final Object simulationRunId, final Object senderId, final Object receiverId,
            final Object messageTypeId, final long messageId, final Object... content)
            throws Sim0MQException, SerializationException
    {
        return encode(StringEncoding.UTF8, simulationRunId, senderId, receiverId, messageTypeId, messageId, content);
    }

    /**
     * Encode the object array into a message. Use UTF16 to code Strings.
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    public static byte[] encodeUTF16(final Object simulationRunId, final Object senderId, final Object receiverId,
            final Object messageTypeId, final long messageId, final Object... content)
            throws Sim0MQException, SerializationException
    {
        return encode(StringEncoding.UTF16, simulationRunId, senderId, receiverId, messageTypeId, messageId, content);
    }

    /**
     * Encode the object array into a reply message. Use UTF8 to code Strings.
     * @param identity the identity of the federate to which this is the reply
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public static byte[] encodeReplyUTF8(final String identity, final Object simulationRunId, final Object senderId,
            final Object receiverId, final Object messageTypeId, final long messageId, final Object... content)
            throws Sim0MQException, SerializationException
    {
        return encodeReply(StringEncoding.UTF8, identity, simulationRunId, senderId, receiverId, messageTypeId, messageId,
                content);
    }

    /**
     * Encode the object array into a reply message. Use UTF16 to code Strings.
     * @param identity the identity of the federate to which this is the reply
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public static byte[] encodeReplyUTF16(final String identity, final Object simulationRunId, final Object senderId,
            final Object receiverId, final Object messageTypeId, final long messageId, final Object... content)
            throws Sim0MQException, SerializationException
    {
        return encodeReply(StringEncoding.UTF16, identity, simulationRunId, senderId, receiverId, messageTypeId, messageId,
                content);
    }

    /**
     * Decode the message into an object array. Note that the message fields are coded as follows:<br>
     * 0 = magic number, equal to the String "SIM##" where ## stands for the version number of the protocol.<br>
     * 1 = simulation run id, could be String, int, Object, ...<br>
     * 2 = sender id, could be String, int, Object, ...<br>
     * 3 = receiver id, could be String, int, Object, ...<br>
     * 4 = message type id, could be String, int, Object, ...<br>
     * 5 = message id, as a long.<br>
     * 6 = number of fields that follow.<br>
     * 7-n = payload, where the number of fields was defined by message[6].
     * @param bytes the ZeroMQ byte array to decode
     * @return an array of objects of the right type
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException when deserialization fails
     */
    public static Object[] decode(final byte[] bytes) throws Sim0MQException, SerializationException
    {
        Object[] message = TypedMessage.decodeToObjectDataTypes(bytes, EndianUtil.BIG_ENDIAN);
        Throw.when(!(message[6] instanceof Number), Sim0MQException.class, "message[6] is not a number");
        Throw.when(message.length != ((Number) message[6]).intValue() + 7, Sim0MQException.class,
                "message[6] number of fields not matched by message structure");
        return message;
    }

    /**
     * Return a printable version of the message, e.g. for debugging purposes.
     * @param message the message to parse
     * @return a string representation of the message
     */
    public static String print(final Object[] message)
    {
        StringBuffer s = new StringBuffer();
        s.append("0. magic number     : " + message[0] + "\n");
        s.append("1. simulation run id: " + message[1] + "\n");
        s.append("2. sender id        : " + message[2] + "\n");
        s.append("3. receiver id      : " + message[3] + "\n");
        s.append("4. message type id  : " + message[4] + "\n");
        s.append("5. message id       : " + message[5] + "\n");
        s.append("6. number of fields : " + message[6] + "\n");
        int nr = ((Number) message[6]).intValue();
        if (message.length != nr + 7)
        {
            s.append("Error - number of fields not matched by message structure");
        }
        else
        {
            for (int i = 0; i < nr; i++)
            {
                s.append((7 + i) + ". message field    : " + message[7 + i] + "  (" + message[7 + i].getClass().getSimpleName()
                        + ")\n");
            }
        }
        return s.toString();
    }

    /**
     * Return a printable line with the payload of the message, e.g. for debugging purposes.
     * @param message the message to parse
     * @return a string representation of the message
     */
    public static String listPayload(final Object[] message)
    {
        StringBuffer s = new StringBuffer();
        s.append('|');
        int nr = ((Number) message[6]).intValue();
        if (message.length != nr + 7)
        {
            s.append("Error - number of fields not matched by message structure");
        }
        else
        {
            for (int i = 0; i < nr; i++)
            {
                s.append(message[7 + i] + " (" + message[7 + i].getClass().getSimpleName() + ") | ");
            }
        }
        return s.toString();
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
         * Build the object.
         * @return the message object from the builder.
         * @throws Sim0MQException on unknown data type
         * @throws NullPointerException when one of the parameters is null
         */
        public abstract Sim0MQMessage build() throws Sim0MQException, NullPointerException;

    }
}
