package org.sim0mq.message.federationmanager;

import org.djutils.exceptions.Throw;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.MessageStatus;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.SimulationMessage;

/**
 * SetParameter, FM.3. Message sent by the FederateManager to the Model for setting the parameter values. Parameters are set one
 * by one (but can be a Vector or Matrix).
 * <p>
 * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FM3SetParameterMessage extends Sim0MQMessage
{
    /** Name of the parameter to be set. Links to a parameter name in the model. */
    private final String parameterName;

    /** Value of the parameter to be set; can be any of the legal types in djutils-serialization. */
    private final Object parameterValue;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FM.3";

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
     * @param parameterName String; Name of the parameter to be set. Links to a parameter name in the model
     * @param parameterValue Object; Value of the parameter to be set; can be any of the legal types in djutils-serialization
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public FM3SetParameterMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final long messageId, final String parameterName, final Object parameterValue)
            throws Sim0MQException, NullPointerException
    {
        super(simulationRunId, senderId, receiverId, MESSAGETYPE, messageId, MessageStatus.NEW);
        Throw.whenNull(parameterName, "parameterName cannot be null");
        Throw.whenNull(parameterValue, "parameterValue cannot be null");
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    /**
     * @return parameterName
     */
    public String getParameterName()
    {
        return this.parameterName;
    }

    /**
     * @return parameterValue
     */
    public Object getParameterValue()
    {
        return this.parameterValue;
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
        return 2;
    }

    /** {@inheritDoc} */
    @Override
    public Object[] createObjectArray()
    {
        return new Object[] {getMagicNumber(), getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), getMessageStatus(), getNumberOfPayloadFields(), this.parameterName, this.parameterValue};
    }

    /** {@inheritDoc} */
    @Override
    public byte[] createByteArray() throws Sim0MQException, SerializationException
    {
        return SimulationMessage.encodeUTF8(getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), getMessageStatus(), this.parameterName, this.parameterValue);
    }

    /**
     * Build a message from an Object[] that was received.
     * @param fields Object[]; the fields in the message
     * @param intendedReceiverId id of the intended receiver
     * @return a Sim0MQ message
     * @throws Sim0MQException when number of fields is not correct
     */
    public static FM3SetParameterMessage createMessage(final Object[] fields, final Object intendedReceiverId)
            throws Sim0MQException
    {
        check(fields, 2, MESSAGETYPE, intendedReceiverId);
        return new FM3SetParameterMessage(fields[1], fields[2], fields[3], ((Long) fields[5]).longValue(), fields[8].toString(),
                fields[9]);
    }

    /**
     * Builder for the SetParameter Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FM3SetParameterMessage.Builder>
    {
        /** Name of the parameter to be set. Links to a parameter name in the model. */
        private String parameterName;

        /** Value of the parameter to be set; can be any of the legal types in djutils-serialization. */
        private Object parameterValue;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newParameterName set parameter name
         * @return the original object for chaining
         */
        public final Builder setParameterName(final String newParameterName)
        {
            this.parameterName = newParameterName;
            return this;
        }

        /**
         * @param newParameterValue set parameterValue
         * @return the original object for chaining
         */
        public final Builder setParameterValue(final Object newParameterValue)
        {
            this.parameterValue = newParameterValue;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public FM3SetParameterMessage build() throws Sim0MQException, NullPointerException
        {
            return new FM3SetParameterMessage(this.simulationRunId, this.senderId, this.receiverId, this.messageId,
                    this.parameterName, this.parameterValue);
        }

    }
}
