package org.sim0mq.message.federatestarter;

import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.MessageStatus;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.SimulationMessage;

/**
 * RequestStatus, FS.1. This message is sent by the Federate Starter to the Model until a "started" response is received from
 * the Model. Since the message type id clarifies the function of this message and no information exchange is necessary, the
 * payload field can be empty (number of fields = 0).
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FS1RequestStatusMessage extends Sim0MQMessage
{
    /** */
    private static final long serialVersionUID = 20170422L;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FS.1";

    /**
     * @param simulationRunId the Simulation run ids can be provided in different types. Examples are two 64-bit longs
     *            indicating a UUID, or a String with a UUID number, a String with meaningful identification, or a short or an
     *            int with a simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FS1RequestStatusMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final long messageId) throws Sim0MQException, NullPointerException
    {
        super(simulationRunId, senderId, receiverId, MESSAGETYPE, messageId, MessageStatus.NEW);
    }

    /**
     * @return messagetype
     */
    public static final String getMessageType()
    {
        return MESSAGETYPE;
    }

    /** {@inheritDoc} */
    @Override
    public short getNumberOfPayloadFields()
    {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public Object[] createObjectArray()
    {
        return new Object[] {getMagicNumber(), getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), getMessageStatus(), getNumberOfPayloadFields()};
    }

    /** {@inheritDoc} */
    @Override
    public byte[] createByteArray() throws Sim0MQException, SerializationException
    {
        return SimulationMessage.encodeUTF8(getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), getMessageStatus());
    }

    /**
     * Build a message from an Object[] that was received.
     * @param fields Object[]; the fields in the message
     * @param intendedReceiverId id of the intended receiver
     * @return a Sim0MQ message
     * @throws Sim0MQException when number of fields is not correct
     */
    public static FS1RequestStatusMessage createMessage(final Object[] fields, final Object intendedReceiverId)
            throws Sim0MQException
    {
        check(fields, 0, MESSAGETYPE, intendedReceiverId);
        return new FS1RequestStatusMessage(fields[1], fields[2], fields[3], ((Long) fields[5]).longValue());
    }

    /**
     * Builder for the StartFederate Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FS1RequestStatusMessage.Builder>
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
        public FS1RequestStatusMessage build() throws Sim0MQException, NullPointerException
        {
            return new FS1RequestStatusMessage(this.simulationRunId, this.senderId, this.receiverId, this.messageId);
        }

    }
}