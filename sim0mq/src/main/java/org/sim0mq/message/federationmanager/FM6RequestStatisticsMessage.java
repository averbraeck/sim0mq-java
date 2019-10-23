package org.sim0mq.message.federationmanager;

import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * RequestStatistics, FM.6. Message sent by the Federation Manager to collect the output.
 * <p>
 * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FM6RequestStatisticsMessage extends Sim0MQMessage
{
    /**
     * The name of the output variable whose value is requested. That should match with the name in the model. For a tallied
     * variable, several statistics are possible, e.g., average, variance, minimum, maximum, time series, etc. The name should
     * clearly indicate what the Model Controller expects and what the model should produce.
     */
    private final String variableName;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FM.6";

    /** */
    private static final long serialVersionUID = 20190712L;

    /**
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param variableName String; The name of the output variable whose value is requested. That should match with the name in
     *            the model. For a tallied variable, several statistics are possible, e.g., average, variance, minimum, maximum,
     *            time series, etc. The name should clearly indicate what the Model Controller expects and what the model should
     *            produce.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public FM6RequestStatisticsMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final String variableName) throws Sim0MQException, NullPointerException
    {
        super(true, federationId, senderId, receiverId, MESSAGETYPE, messageId, new Object[] {variableName});
        this.variableName = variableName;
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FM6RequestStatisticsMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 1);
        Throw.when(!(objectArray[8] instanceof String), Sim0MQException.class, "variableName (field 8) should be String");
        this.variableName = objectArray[8].toString();
    }

    /**
     * @return variableName
     */
    public String getVariableName()
    {
        return this.variableName;
    }

    /**
     * Builder for the RequestStatistics Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FM6RequestStatisticsMessage.Builder>
    {
        /**
         * The name of the output variable whose value is requested. That should match with the name in the model. For a tallied
         * variable, several statistics are possible, e.g., average, variance, minimum, maximum, time series, etc. The name
         * should clearly indicate what the Model Controller expects and what the model should produce.
         */
        private String variableName;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newVariableName set statistics variable name
         * @return the original object for chaining
         */
        public final Builder setVariableName(final String newVariableName)
        {
            this.variableName = newVariableName;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public FM6RequestStatisticsMessage build() throws Sim0MQException, NullPointerException
        {
            return new FM6RequestStatisticsMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.variableName);
        }

    }
}
