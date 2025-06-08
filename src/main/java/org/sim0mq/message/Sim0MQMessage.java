package org.sim0mq.message;

import java.io.Serializable;

import org.djutils.exceptions.Throw;
import org.djutils.serialization.Endianness;
import org.djutils.serialization.SerializationException;
import org.djutils.serialization.TypedMessage;
import org.sim0mq.Sim0MQException;

/**
 * Sim0MQMessage contains the abstract body of the message with the first fields of every Sim0MQ message. The message structure
 * of a typical typed Sim0MQ simulation message looks as follows:
 * <ul>
 * <li>Frame 0. Magic number = |9|0|0|0|5|S|I|M|#|#| where ## stands for the version number, e.g., 02.</li>
 * <li>Frame 1. Endianness. A boolean that is True for Big-Endian encoding and false for Little-Endian encosing of the
 * message.</li>
 * <li>Frame 2. Federation id. Federation ids can be provided in different types. Examples are a 64-bit long, or a String with a
 * UUID number, a String with meaningful identification, or a short or an int with a simulation run number. In order to check
 * whether the right information has been received, the id can be translated to a String and compared with an internal string
 * representation of the required simulation run id. Typically we use a String to provide maximum freedom. In that case, the run
 * id can be coded as UTF-8 or UTF-16.</li>
 * <li>Frame 3. Sender id. Sender ids can be provided in different types. Examples are a 64-bit long, or a String with a UUID
 * number, a String with meaningful identification, or a short or an int with a sender id number. The sender id can be used to
 * send back a message to the sender at some later time. Typically we use a String to provide maximum freedom. In that case, the
 * sender id can be coded as UTF-8 or UTF-16.</li>
 * <li>Frame 4. Receiver id. Receiver ids can be provided in different types. Examples are a 64-bit long, or a String with a
 * UUID number, a String with meaningful identification, or a short or an int with a receiver id number. The receiver id can be
 * used to check whether the message is meant for us, or should be discarded (or an error can be sent if we receive a message
 * not meant for us). Typically we use a String to provide maximum freedom. In that case, the receiver id can be coded as UTF-8
 * or UTF-16.</li>
 * <li>Frame 5. Message type id. Message type ids can be defined per type of simulation, and can be provided in different types.
 * Examples are a String with a meaningful identification, or a short or an int with a message type number. For interoperability
 * between different types of simulation, a String id with dot-notation (e.g., DSOL.1 for a simulator start message from DSOL or
 * OTS.14 for a statistics message from OpenTrafficSim) would be preferred. In that case, the message type id can be coded as
 * UTF-8 or UTF-16.</li>
 * <li>Frame 6. Unique message number. The unique message number can have any type, but is typically sent as a long (64 bits),
 * and is meant to confirm with a callback that the message has been received correctly. The number is unique for the sender, so
 * not globally within the federation.</li>
 * <li>Frame 7. Number of fields. The number of fields in the payload is indicated to be able to check the payload and to avoid
 * reading past the end. The number of fields can be encoded using byte, short, int, or long. A 16-bit positive short (including
 * zero) is the standard encoding.</li>
 * <li>Frame 8-n. Payload, where each field has a 1-byte prefix denoting the type of field.</li>
 * </ul>
 * <p>
 * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * initial version Mar 3, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class Sim0MQMessage implements Serializable
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

    /**
     * Indicates whether this message using little endian or big endian encoding. Big endian is encoded as true, and little
     * endian as false.
     */
    private final boolean bigEndian;

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
     * The unique message id is meant to be used in, e.g., a callback that the message has been received correctly. Within this
     * implementation, the messageId is unique for the sender, so not globally within the federation.
     */
    private final Object messageId;

    /** Payload as an Object array. */
    private final Object[] payload;

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
    public Sim0MQMessage(final boolean bigEndian, final Object federationId, final Object senderId, final Object receiverId,
            final Object messageTypeId, final Object messageId, final Object[] payload)
            throws Sim0MQException, NullPointerException
    {
        Throw.whenNull(federationId, "federationId cannot be null");
        Throw.whenNull(senderId, "senderId cannot be null");
        Throw.whenNull(receiverId, "receiverId cannot be null");
        Throw.whenNull(messageTypeId, "messageTypeId cannot be null");
        Throw.whenNull(messageId, "messageId cannot be null");
        if (payload != null)
        {
            for (int i = 0; i < payload.length; i++)
            {
                Throw.whenNull(payload[i], "payload[" + i + "] cannot be null");
            }
        }

        this.bigEndian = bigEndian;
        this.federationId = federationId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageTypeId = messageTypeId;
        this.messageId = messageId;
        this.payload = payload == null ? new Object[0] : payload;
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
     * 8-n = payload, where the number of fields was defined by message[7].
     * @param objectArray Object[]; Full message object array
     * @param expectedNumberOfPayloadFields int; the expected number of fields in the message (field 8 and further)
     * @param expectedMessageTypeId the expected message type id
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public Sim0MQMessage(final Object[] objectArray, final int expectedNumberOfPayloadFields,
            final Object expectedMessageTypeId) throws Sim0MQException, NullPointerException
    {
        Throw.whenNull(objectArray, "objectArray cannot be null");
        Throw.when(objectArray.length != 8 + expectedNumberOfPayloadFields, Sim0MQException.class,
                "FS1RequestStatusMessage should have " + expectedNumberOfPayloadFields + " fields but has "
                        + (objectArray.length - 8) + " fields");
        for (int i = 0; i < 8; i++)
        {
            Throw.whenNull(objectArray[i], "objectArray[" + i + "] cannot be null");
        }
        Throw.when(!objectArray[0].equals(VERSION), Sim0MQException.class, "objectArray.version != " + VERSION);
        Throw.when(!objectArray[5].equals(expectedMessageTypeId), Sim0MQException.class,
                "objectArray.messageTypeId != " + expectedMessageTypeId);
        Throw.when(!(objectArray[1] instanceof Boolean), Sim0MQException.class, "objectArray.bigEndian not boolean");
        this.bigEndian = ((Boolean) objectArray[1]).booleanValue();
        this.federationId = objectArray[2];
        this.senderId = objectArray[3];
        this.receiverId = objectArray[4];
        this.messageTypeId = objectArray[5];
        this.messageId = objectArray[6];
        Throw.when(!(objectArray[7] instanceof Number), Sim0MQException.class, "objectArray.numberOfFields not a number");
        this.payload = new Object[((Number) objectArray[7]).intValue()];
        for (int i = 0; i < this.payload.length; i++)
        {
            this.payload[i] = objectArray[i + 8];
        }
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
     * @return bigEndian
     */
    public final boolean isBigEndian()
    {
        return this.bigEndian;
    }

    /**
     * @return federationId
     */
    public final Object getFederationId()
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
    public final Object getMessageId()
    {
        return this.messageId;
    }

    /**
     * Create a Sim0MQ object array of the fields.
     * @return Object[] a Sim0MQ object array of the fields
     */
    public final Object[] createObjectArray()
    {
        Object[] result = new Object[8 + getNumberOfPayloadFields()];
        result[0] = Sim0MQMessage.VERSION;
        result[1] = isBigEndian();
        result[2] = getFederationId();
        result[3] = getSenderId();
        result[4] = getReceiverId();
        result[5] = getMessageTypeId();
        result[6] = getMessageId();
        result[7] = getNumberOfPayloadFields();
        for (int i = 0; i < getNumberOfPayloadFields(); i++)
        {
            result[8 + i] = this.payload[i];
        }
        return result;
    }

    /**
     * Create a byte array of the fields.
     * @return byte[] a Sim0MQ byte array of the content
     * @throws Sim0MQException on unknown data type as part of the content
     * @throws SerializationException when the byte array cannot be created, e.g. because the number of bytes does not match
     */
    public final byte[] createByteArray() throws Sim0MQException, SerializationException
    {
        return Sim0MQMessage.encodeUTF8(this.bigEndian, getFederationId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), this.payload);
    }

    /**
     * Get the number of payload fields in the message.
     * @return short; the number of payload fields in the message.
     */
    public final short getNumberOfPayloadFields()
    {
        return (short) this.payload.length;
    }

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

        Throw.when(!expectedMessageType.equals(fields[5].toString()), Sim0MQException.class,
                "Message type not right -- should have been " + expectedMessageType);

        Throw.when(!fields[4].equals(intendedReceiverId), Sim0MQException.class,
                "Receiver in message of type " + expectedMessageType + " not right. Should have been: " + intendedReceiverId);

        Throw.when(!(fields[7] instanceof Number), Sim0MQException.class,
                "Message " + expectedMessageType + " does not have a Number field[7] for the number of fields");
        Throw.when(((Number) fields[7]).longValue() != expectedPayloadFields, Sim0MQException.class,
                "Message " + expectedMessageType + " does not contain the right number of payload fields in field[7]");
    }

    /* ******************************************************************************************************* */
    /* ************************************ STATIC METHODS TO BUILD A MESSAGE ******************************** */
    /* ******************************************************************************************************* */

    /**
     * Encode the object array into a message. Use UTF8 or UTF16 to code Strings.
     * @param stringEncoding choice to use Use UTF8 or UTF16 to code Strings
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    @SuppressWarnings("checkstyle:parameternumber")
    private static byte[] encode(final StringEncoding stringEncoding, final boolean bigEndian, final Object federationId,
            final Object senderId, final Object receiverId, final Object messageTypeId, final Object messageId,
            final Object... content) throws Sim0MQException, SerializationException
    {
        Object[] simulationContent = new Object[content.length + 8];
        simulationContent[0] = Sim0MQMessage.VERSION;
        simulationContent[1] = bigEndian;
        simulationContent[2] = federationId;
        simulationContent[3] = senderId;
        simulationContent[4] = receiverId;
        simulationContent[5] = messageTypeId;
        simulationContent[6] = messageId;
        if (content.length < Short.MAX_VALUE)
        {
            simulationContent[7] = Short.valueOf((short) content.length);
        }
        else
        {
            simulationContent[7] = Integer.valueOf(content.length);
        }
        for (int i = 0; i < content.length; i++)
        {
            simulationContent[i + 8] = content[i];
        }
        return stringEncoding.isUTF8()
                ? TypedMessage.encodeUTF8(bigEndian ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN, simulationContent)
                : TypedMessage.encodeUTF16(bigEndian ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN, simulationContent);
    }

    /**
     * Encode the object array into a message. Use UTF8 or UTF16 to code Strings.
     * @param identity the identity of the federate to which this is the reply
     * @param stringEncoding choice to use Use UTF8 or UTF16 to code Strings
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    @SuppressWarnings("checkstyle:parameternumber")
    private static byte[] encodeReply(final String identity, final StringEncoding stringEncoding, final boolean bigEndian,
            final Object federationId, final Object senderId, final Object receiverId, final Object messageTypeId,
            final Object messageId, final Object... content) throws Sim0MQException, SerializationException
    {
        Object[] simulationContent = new Object[content.length + 10];
        simulationContent[0] = identity;
        simulationContent[1] = new byte[] {0};
        simulationContent[2] = Sim0MQMessage.VERSION;
        simulationContent[3] = bigEndian;
        simulationContent[4] = federationId;
        simulationContent[5] = senderId;
        simulationContent[6] = receiverId;
        simulationContent[7] = messageTypeId;
        simulationContent[8] = messageId;
        if (content.length < Short.MAX_VALUE)
        {
            simulationContent[9] = Short.valueOf((short) content.length);
        }
        else
        {
            simulationContent[9] = Integer.valueOf(content.length);
        }
        for (int i = 0; i < content.length; i++)
        {
            simulationContent[i + 10] = content[i];
        }
        return stringEncoding.isUTF8()
                ? TypedMessage.encodeUTF8(bigEndian ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN, simulationContent)
                : TypedMessage.encodeUTF16(bigEndian ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN, simulationContent);
    }

    /**
     * Encode the object array into a message. Use UTF8 to code Strings.
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    public static byte[] encodeUTF8(final boolean bigEndian, final Object federationId, final Object senderId,
            final Object receiverId, final Object messageTypeId, final Object messageId, final Object... content)
            throws Sim0MQException, SerializationException
    {
        return encode(StringEncoding.UTF8, bigEndian, federationId, senderId, receiverId, messageTypeId, messageId, content);
    }

    /**
     * Encode the object array into a message. Use UTF16 to code Strings.
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    public static byte[] encodeUTF16(final boolean bigEndian, final Object federationId, final Object senderId,
            final Object receiverId, final Object messageTypeId, final Object messageId, final Object... content)
            throws Sim0MQException, SerializationException
    {
        return encode(StringEncoding.UTF16, bigEndian, federationId, senderId, receiverId, messageTypeId, messageId, content);
    }

    /**
     * Encode the object array into a reply message. Use UTF8 to code Strings.
     * @param identity the identity of the federate to which this is the reply
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public static byte[] encodeReplyUTF8(final String identity, final boolean bigEndian, final Object federationId,
            final Object senderId, final Object receiverId, final Object messageTypeId, final Object messageId,
            final Object... content) throws Sim0MQException, SerializationException
    {
        return encodeReply(identity, StringEncoding.UTF8, bigEndian, federationId, senderId, receiverId, messageTypeId,
                messageId, content);
    }

    /**
     * Encode the object array into a reply message. Use UTF16 to code Strings.
     * @param identity the identity of the federate to which this is the reply
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
     * @param content the objects to encode
     * @return the zeroMQ message to send as a byte array
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException on serialization problem
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public static byte[] encodeReplyUTF16(final String identity, final boolean bigEndian, final Object federationId,
            final Object senderId, final Object receiverId, final Object messageTypeId, final Object messageId,
            final Object... content) throws Sim0MQException, SerializationException
    {
        return encodeReply(identity, StringEncoding.UTF16, bigEndian, federationId, senderId, receiverId, messageTypeId,
                messageId, content);
    }

    /**
     * Decode the message into an object array. Note that the message fields are coded as follows:<br>
     * 0 = magic number, equal to the String "SIM##" where ## stands for the version number of the protocol.<br>
     * 1 = endianness, boolean indicating the endianness for the message. True is Big Endian, false is Little Endian.<br>
     * 2 = federation id, could be String, int, Object, ...<br>
     * 3 = sender id, could be String, int, Object, ...<br>
     * 4 = receiver id, could be String, int, Object, ...<br>
     * 5 = message type id, could be String, int, Object, ...<br>
     * 6 = message id, could be long, Object, String, ....<br>
     * 7 = number of fields that follow, as a Number (byte, short, int, long).<br>
     * 8-n = payload, where the number of fields was defined by message[7].
     * @param bytes the ZeroMQ byte array to decode
     * @return Sim0MQMessage; a newly created Sim0MQMessage based on the decoded bytes
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException when deserialization fails
     */
    public static Sim0MQMessage decode(final byte[] bytes) throws Sim0MQException, SerializationException
    {
        Object[] array = decodeToArray(bytes);
        return new Sim0MQMessage(array, array.length - 8, array[5]);
    }

    /**
     * Decode the message into an object array. Note that the message fields are coded as follows:<br>
     * 0 = magic number, equal to the String "SIM##" where ## stands for the version number of the protocol.<br>
     * 1 = endianness, boolean indicatingthe enfianness for the message. True is Big Endian, false is Little Endian.<br>
     * 2 = simulation run id, could be String, int, Object, ...<br>
     * 3 = sender id, could be String, int, Object, ...<br>
     * 4 = receiver id, could be String, int, Object, ...<br>
     * 5 = message type id, could be String, int, Object, ...<br>
     * 6 = message id, as a long.<br>
     * 7 = number of fields that follow.<br>
     * 8-n = payload, where the number of fields was defined by message[6].
     * @param bytes the ZeroMQ byte array to decode
     * @return an array of objects of the right type
     * @throws Sim0MQException on unknown data type
     * @throws SerializationException when deserialization fails
     */
    public static Object[] decodeToArray(final byte[] bytes) throws Sim0MQException, SerializationException
    {
        Throw.whenNull(bytes, "bytes should not be null");
        Throw.when(bytes.length < 12, Sim0MQException.class, "number of bytes in message < 12: " + bytes.length);
        Throw.when(bytes[10] != 6 || bytes[11] < 0 || bytes[11] > 1, Sim0MQException.class,
                "Bytes 10+11 in the byte array do not contain a boolean");
        Throw.when(bytes[0] != 9, Sim0MQException.class, "Byte 0 of message is not equal to 9");
        int magicLength = Endianness.BIG_ENDIAN.decodeInt(bytes, 1);
        Throw.when(bytes[5 + magicLength] != 6, Sim0MQException.class, "Byte 1 frame 1 of message is not equal to 6");
        var endianness = bytes[6 + magicLength] == 1 ? Endianness.BIG_ENDIAN : Endianness.LITTLE_ENDIAN;
        Object[] objectArray = TypedMessage.decodeToObjectDataTypes(endianness, bytes);
        Throw.when(objectArray.length < 8, Sim0MQException.class, "number of message fields < 8: " + objectArray.length);
        Throw.when(!(objectArray[0] instanceof String) || !(objectArray[0].equals(Sim0MQMessage.VERSION)),
                Sim0MQException.class, "message[0] does not contain the right version number: " + objectArray[0]);
        Throw.when(!(objectArray[1] instanceof Boolean), Sim0MQException.class, "message[1] is not a boolean");
        Throw.when(!(objectArray[7] instanceof Number), Sim0MQException.class, "message[7] is not a number");
        Throw.when(objectArray.length != ((Number) objectArray[7]).intValue() + 8, Sim0MQException.class,
                "message[7] number of fields not matched by message structure");
        return objectArray;
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
        s.append("1. endianness       : " + message[1] + "\n");
        s.append("2. simulation run id: " + message[2] + "\n");
        s.append("3. sender id        : " + message[3] + "\n");
        s.append("4. receiver id      : " + message[4] + "\n");
        s.append("5. message type id  : " + message[5] + "\n");
        s.append("6. message id       : " + message[6] + "\n");
        s.append("7. number of fields : " + message[7] + "\n");
        int nr = ((Number) message[7]).intValue();
        if (message.length != nr + 8)
        {
            s.append("Error - number of fields not matched by message structure");
        }
        else
        {
            for (int i = 0; i < nr; i++)
            {
                s.append((8 + i) + ". message field    : " + message[8 + i] + "  (" + message[8 + i].getClass().getSimpleName()
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
        int nr = ((Number) message[7]).intValue();
        if (message.length != nr + 8)
        {
            s.append("Error - number of fields not matched by message structure");
        }
        else
        {
            for (int i = 0; i < nr; i++)
            {
                s.append(message[8 + i] + " (" + message[8 + i].getClass().getSimpleName() + ") | ");
            }
        }
        return s.toString();
    }

    /**
     * Builder for the Sim0MQMessage. Can string setters together, and call build() at the end to build the actual message.
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
    public abstract static class Builder<B extends Sim0MQMessage.Builder<B>>
    {
        /**
         * the Simulation run ids can be provided in different types. Examples are two 64-bit longs indicating a UUID, or a
         * String with a UUID number, a String with meaningful identification, or a short or an int with a simulation run
         * number.
         */
        @SuppressWarnings("checkstyle:visibilitymodifier")
        protected Object federationId;

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
        protected Object messageId;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newFederationId set federationId
         * @return the original object for chaining
         */
        @SuppressWarnings("unchecked")
        public final B setSimulationRunId(final Object newFederationId)
        {
            this.federationId = newFederationId;
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
        public final B setMessageId(final Object newMessageId)
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
