package org.sim0mq.message.modelcontroller;

import org.djutils.exceptions.Throw;
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
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
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
    public MC3StatisticsMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final String variableName, final Object variableValue)
            throws Sim0MQException, NullPointerException
    {
        super(true, federationId, senderId, receiverId, MESSAGETYPE, messageId, new Object[] {variableName, variableValue});
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public MC3StatisticsMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 2);
        Throw.when(!(objectArray[8] instanceof String), Sim0MQException.class, "variableName (field 8) should be String");
        this.variableName = objectArray[8].toString();
        this.variableValue = objectArray[9];
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
            return new MC3StatisticsMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.variableName, this.variableValue);
        }

    }
}
