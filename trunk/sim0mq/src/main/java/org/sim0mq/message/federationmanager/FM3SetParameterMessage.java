package org.sim0mq.message.federationmanager;

import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

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
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
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
    public FM3SetParameterMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final String parameterName, final Object parameterValue)
            throws Sim0MQException, NullPointerException
    {
        this(new Object[] {Sim0MQMessage.VERSION, true, federationId, senderId, receiverId, MESSAGETYPE, messageId, 2,
                parameterName, parameterValue});
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FM3SetParameterMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 2, MESSAGETYPE);
        Throw.when(!(objectArray[8] instanceof String), Sim0MQException.class, "parameterName (field 8) should be String");
        this.parameterName = objectArray[8].toString();
        this.parameterValue = objectArray[9];
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
            return new FM3SetParameterMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.parameterName, this.parameterValue);
        }

    }
}
