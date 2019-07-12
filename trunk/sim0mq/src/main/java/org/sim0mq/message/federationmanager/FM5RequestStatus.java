package org.sim0mq.message.federationmanager;

import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.MessageStatus;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.SimulationMessage;

/**
 * RequestStatus, FM.5. Message sent by the Federation Manager to enquire the status of the simulation. The answer to this
 * message is MC.1 "Status". Since the message type id clarifies the function of this message and no information exchange is
 * necessary, the payload field can be empty.
 * <p>
 * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FM5RequestStatus extends Sim0MQMessage
{
    /** the unique message id. */
    private static final String MESSAGETYPE = "FM.5";

    /** */
    private static final long serialVersionUID = 20190712L;

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
    @SuppressWarnings("checkstyle:parameternumber")
    public FM5RequestStatus(final Object simulationRunId, final Object senderId, final Object receiverId, final long messageId)
            throws Sim0MQException, NullPointerException
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
    public Object[] createObjectArray()
    {
        return new Object[] {getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(), getMessageId(),
                getMessageStatus()};
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
    public static FM5RequestStatus createMessage(final Object[] fields, final Object intendedReceiverId) throws Sim0MQException
    {
        check(fields, 0, MESSAGETYPE, intendedReceiverId);
        return new FM5RequestStatus(fields[1], fields[2], fields[3], ((Long) fields[5]).longValue());
    }

    /**
     * Builder for the RequestStatus Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FM5RequestStatus.Builder>
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
        public Sim0MQMessage build() throws Sim0MQException, NullPointerException
        {
            return new FM5RequestStatus(this.simulationRunId, this.senderId, this.receiverId, this.messageId);
        }

    }
}
