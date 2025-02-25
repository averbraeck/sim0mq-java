package org.sim0mq.message.modelcontroller;

import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * StatisticsError, MC.4. The Model sends this message as a response to RequestStatistics messages sent by the Federation
 * Manager, when e.g., the requested variable could not be found.
 * <p>
 * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class MC4StatisticsErrorMessage extends Sim0MQMessage
{
    /** The name of the output variable whose value is requested. That should match with the name in the model. */
    private final String variableName;

    /** Error retrieving the model output value. */
    private final String error;

    /** the unique message id. */
    private static final String MESSAGETYPE = "MC.4";

    /** */
    private static final long serialVersionUID = 20190712;

    /**
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param variableName The name of the output variable whose value was requested. That should match with the name in the
     *            model.
     * @param error String; Error retrieving the model output value.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public MC4StatisticsErrorMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final String variableName, final String error) throws Sim0MQException, NullPointerException
    {
        this(new Object[] {Sim0MQMessage.VERSION, true, federationId, senderId, receiverId, MESSAGETYPE, messageId, 2,
                variableName, error});
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public MC4StatisticsErrorMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 2, MESSAGETYPE);
        Throw.when(!(objectArray[8] instanceof String), Sim0MQException.class, "variableName (field 8) should be String");
        this.variableName = objectArray[8].toString();
        Throw.when(!(objectArray[9] instanceof String), Sim0MQException.class, "error (field 9) should be String");
        this.error = objectArray[9].toString();
    }

    /**
     * @return variableName
     */
    public String getVariableName()
    {
        return this.variableName;
    }

    /**
     * @return error
     */
    public String getError()
    {
        return this.error;
    }

    /**
     * Builder for the StartFederate Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<MC4StatisticsErrorMessage.Builder>
    {
        /** The name of the output variable whose value is requested. That should match with the name in the model. */
        private String variableName;

        /** Error message retrieving the output value from the model. */
        private String error;

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
         * @param newError set error
         * @return the original object for chaining
         */
        public final Builder setError(final String newError)
        {
            this.error = newError;
            return this;
        }

        @Override
        public MC4StatisticsErrorMessage build() throws Sim0MQException, NullPointerException
        {
            return new MC4StatisticsErrorMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.variableName, this.error);
        }

    }
}
