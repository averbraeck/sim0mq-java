package org.sim0mq.message.modelcontroller;

import org.djutils.exceptions.Throw;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * StatisticsMessage, MC.3. The Model sends this message as a response to RequestStatistics messages sent by the Federation
 * Manager. It contains one value for a model output statistic.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class MC3StatisticsMessage extends Sim0MQMessage
{
    /** The name of the output variable whose value is requested. That should match with the name in the model. */
    private final String variableName;

    /**
     * If variableType is scalar, the data type is e.g., an integer, float etc. and the value generated in the model. If
     * variableType is time series, the data type is an ‘array’ (type 11-16 or 27/28) or a time series (type 31/32).
     */
    private final Object variableValue;

    /** the unique message id. */
    private static final String MESSAGETYPE = "MC.3";

    /** */
    private static final long serialVersionUID = 20170422L;

    /**
     * @param simulationRunId the Simulation run ids can be provided in different types. Examples are two 64-bit longs
     *            indicating a UUID, or a String with a UUID number, a String with meaningful identification, or a short or an
     *            int with a simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param variableName The name of the output variable whose value is requested. That should match with the name in the
     *            model.
     * @param variableValue If variableType is scalar, the data type is e.g., an integer, float etc. and the value generated in
     *            the model. If variableType is timeseries, the data type is an ‘array’ (type 11-16 or 27/28) or a time series
     *            (type 31/32).
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public MC3StatisticsMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final long messageId, final String variableName, final Object variableValue)
            throws Sim0MQException, NullPointerException
    {
        super(simulationRunId, senderId, receiverId, MESSAGETYPE, messageId);
        Throw.whenNull(variableName, "variableName cannot be null");
        Throw.whenNull(variableValue, "variableValue cannot be null");
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    /**
     * @return variableName
     */
    public String getVariableName()
    {
        return this.variableName;
    }

    /**
     * @return variableValue
     */
    public Object getVariableValue()
    {
        return this.variableValue;
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
                getMessageId(), getNumberOfPayloadFields(), this.variableName, this.variableValue};
    }

    /** {@inheritDoc} */
    @Override
    public byte[] createByteArray() throws Sim0MQException, SerializationException
    {
        return Sim0MQMessage.encodeUTF8(getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), this.variableName, this.variableValue);
    }

    /**
     * Build a message from an Object[] that was received.
     * @param fields Object[]; the fields in the message
     * @param intendedReceiverId id of the intended receiver
     * @return a Sim0MQ message
     * @throws Sim0MQException when number of fields is not correct
     */
    public static MC3StatisticsMessage createMessage(final Object[] fields, final Object intendedReceiverId)
            throws Sim0MQException
    {
        check(fields, 2, MESSAGETYPE, intendedReceiverId);
        return new MC3StatisticsMessage(fields[1], fields[2], fields[3], ((Long) fields[5]).longValue(), fields[7].toString(),
                fields[8]);
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
    public static class Builder extends Sim0MQMessage.Builder<MC3StatisticsMessage.Builder>
    {
        /** The name of the output variable whose value is requested. That should match with the name in the model. */
        private String variableName;

        /**
         * If variableType is scalar, the data type is e.g., an integer, float etc. and the value generated in the model. If
         * variableType is time series, the data type is an ‘array’ (type 11-16 or 27/28) or a time series (type 31/32).
         */
        private Object variableValue;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newVariableName set variable name
         * @return the original object for chaining
         */
        public final Builder setVariableName(final String newVariableName)
        {
            this.variableName = newVariableName;
            return this;
        }

        /**
         * @param newVariableValue set variable value
         * @return the original object for chaining
         */
        public final Builder setVariableValue(final Object newVariableValue)
        {
            this.variableValue = newVariableValue;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public MC3StatisticsMessage build() throws Sim0MQException, NullPointerException
        {
            return new MC3StatisticsMessage(this.simulationRunId, this.senderId, this.receiverId, this.messageId,
                    this.variableName, this.variableValue);
        }

    }
}
