package org.sim0mq.message.federatestarter;

import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * FederateKilled, FS.4. Message sent by the Federate Starter to the Federation Manager in response to message FM.8.
 * <p>
 * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FS4FederateKilledMessage extends Sim0MQMessage
{
    /** */
    private static final long serialVersionUID = 20170422L;

    /** The sender id of the model that was started and now has to be terminated. */
    private final Object instanceId;

    /** Did the termination of the model succeed? */
    private final boolean status;

    /** If there was an error, the error message is sent as well. Otherwise this field is an empty string. */
    private final String error;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FS.4";

    /**
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param instanceId The sender id of the model that was killed or had an error while killing. This is exactly the same as
     *            the instanceId sent by the Federation Manager in the KillFederate message.
     * @param status Did the termination of the model succeed?
     * @param error If there was an error with the model termination, the error message is sent as well. Otherwise this field is
     *            an empty string.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FS4FederateKilledMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final Object instanceId, final boolean status, final String error)
            throws Sim0MQException, NullPointerException
    {
        this(new Object[] {Sim0MQMessage.VERSION, true, federationId, senderId, receiverId, MESSAGETYPE, messageId, 3,
                instanceId, status, error});
    }

    /**
     * @param objectArray Object[]; the fields that constitute the message
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FS4FederateKilledMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 3, MESSAGETYPE);
        this.instanceId = objectArray[8];
        Throw.when(!(objectArray[9] instanceof Boolean), Sim0MQException.class, "status (field 9) should be a Boolean");
        this.status = ((Boolean) objectArray[9]).booleanValue();
        Throw.when(!(objectArray[10] instanceof String), Sim0MQException.class, "error (field 10) should be a String");
        this.error = objectArray[10].toString();
    }

    /**
     * @return instanceId
     */
    public Object getInstanceId()
    {
        return this.instanceId;
    }

    /**
     * @return status
     */
    public boolean isStatus()
    {
        return this.status;
    }

    /**
     * @return error
     */
    public String getError()
    {
        return this.error;
    }

    /**
     * @return messagetype
     */
    public static final String getMessageType()
    {
        return MESSAGETYPE;
    }

    /**
     * Builder for the FederateStarted Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FS4FederateKilledMessage.Builder>
    {
        /**
         * The sender id of the model that was started or had an error while starting. This is exactly the same as the
         * instanceId sent by the Federation Manager in the StartFederate message.
         */
        private String instanceId;

        /** Did the termination of the model succeed? */
        private boolean status;

        /** If there was an error, the error message is sent as well. Otherwise this field is an empty string. */
        private String error;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newInstanceId set instanceId
         * @return the original object for chaining
         */
        public final Builder setInstanceId(final String newInstanceId)
        {
            this.instanceId = newInstanceId;
            return this;
        }

        /**
         * @param newStatus set status
         * @return the original object for chaining
         */
        public final Builder setStatus(final boolean newStatus)
        {
            this.status = newStatus;
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
        public FS4FederateKilledMessage build() throws Sim0MQException, NullPointerException
        {
            return new FS4FederateKilledMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.instanceId, this.status, this.error);
        }

    }
}
