package org.sim0mq.message.federatestarter;

import org.djutils.exceptions.Throw;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * FederateKilled, FS.4. Message sent by the Federate Starter to the Federation Manager in response to message FM.8.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FS4FederateKilledMessage extends Sim0MQMessage
{
    /** */
    private static final long serialVersionUID = 20170422L;

    /** The sender id of the model that was started and now has to be terminated. */
    private final String instanceId;

    /** Did the termination of the model succeed? */
    private final boolean status;

    /** If there was an error, the error message is sent as well. Otherwise this field is an empty string. */
    private final String error;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FS.4";

    /**
     * @param simulationRunId the Simulation run ids can be provided in different types. Examples are two 64-bit longs
     *            indicating a UUID, or a String with a UUID number, a String with meaningful identification, or a short or an
     *            int with a simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param instanceId The sender id of the model that was started or had an error while starting. This is exactly the same as
     *            the instanceId sent by the Federation Manager in the StartFederate message.
     * @param status Did the termination of the model succeed?
     * @param error If there was an error with the model termination, the error message is sent as well. Otherwise this field is
     *            an empty string.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public FS4FederateKilledMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final long messageId, final String instanceId, final boolean status, final String error)
            throws Sim0MQException, NullPointerException
    {
        super(simulationRunId, senderId, receiverId, MESSAGETYPE, messageId);

        Throw.whenNull(instanceId, "instanceId cannot be null");
        Throw.whenNull(status, "status cannot be null");
        Throw.whenNull(error, "error cannot be null");

        this.instanceId = instanceId;
        this.status = status;
        this.error = error;
    }

    /**
     * @return instanceId
     */
    public String getInstanceId()
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

    /** {@inheritDoc} */
    @Override
    public short getNumberOfPayloadFields()
    {
        return 3;
    }

    /** {@inheritDoc} */
    @Override
    public Object[] createObjectArray()
    {
        return new Object[] {getMagicNumber(), getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), getNumberOfPayloadFields(), this.instanceId, this.status, this.error};
    }

    /** {@inheritDoc} */
    @Override
    public byte[] createByteArray() throws Sim0MQException, SerializationException
    {
        return Sim0MQMessage.encodeUTF8(getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), this.instanceId, this.status, this.error);
    }

    /**
     * Build a message from an Object[] that was received.
     * @param fields Object[]; the fields in the message
     * @param intendedReceiverId id of the intended receiver
     * @return a Sim0MQ message
     * @throws Sim0MQException when number of fields is not correct
     */
    public static FS4FederateKilledMessage createMessage(final Object[] fields, final Object intendedReceiverId)
            throws Sim0MQException
    {
        check(fields, 3, MESSAGETYPE, intendedReceiverId);
        return new FS4FederateKilledMessage(fields[1], fields[2], fields[3], ((Long) fields[5]).longValue(),
                fields[7].toString(), (boolean) fields[8], fields[9].toString());
    }

    /**
     * Builder for the FederateStarted Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
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

        /** {@inheritDoc} */
        @Override
        public FS4FederateKilledMessage build() throws Sim0MQException, NullPointerException
        {
            return new FS4FederateKilledMessage(this.simulationRunId, this.senderId, this.receiverId, this.messageId,
                    this.instanceId, this.status, this.error);
        }

    }
}
