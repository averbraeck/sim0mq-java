package org.sim0mq.message.federatestarter;

import org.djutils.exceptions.Throw;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * FederatesKilled, FS.5. Message sent by the Federate Starter to the Federation Manager in response to message FM.9.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FS5FederatesKilledMessage extends Sim0MQMessage
{
    /** */
    private static final long serialVersionUID = 20170422L;

    /** succeeded? */
    private final boolean status;

    /** If there is an error, the error message is sent as well. Otherwise this field is an empty string. */
    private final String error;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FS.5";

    /**
     * @param simulationRunId the Simulation run ids can be provided in different types. Examples are two 64-bit longs
     *            indicating a UUID, or a String with a UUID number, a String with meaningful identification, or a short or an
     *            int with a simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param status boolean; success or failure of killing all models.
     * @param error If there is an error, the error message is sent as well. Otherwise this field is an empty string.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public FS5FederatesKilledMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final long messageId, final boolean status, final String error) throws Sim0MQException, NullPointerException
    {
        super(simulationRunId, senderId, receiverId, MESSAGETYPE, messageId);
        Throw.whenNull(error, "error cannot be null");
        this.status = status;
        this.error = error;
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
        return 2;
    }

    /** {@inheritDoc} */
    @Override
    public Object[] createObjectArray()
    {
        return new Object[] {getMagicNumber(), getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), getNumberOfPayloadFields(), this.status, this.error};
    }

    /** {@inheritDoc} */
    @Override
    public byte[] createByteArray() throws Sim0MQException, SerializationException
    {
        return Sim0MQMessage.encodeUTF8(getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), this.status, this.error);
    }

    /**
     * Build a message from an Object[] that was received.
     * @param fields Object[]; the fields in the message
     * @param intendedReceiverId id of the intended receiver
     * @return a Sim0MQ message
     * @throws Sim0MQException when number of fields is not correct
     */
    public static FS5FederatesKilledMessage createMessage(final Object[] fields, final Object intendedReceiverId)
            throws Sim0MQException
    {
        check(fields, 2, MESSAGETYPE, intendedReceiverId);
        return new FS5FederatesKilledMessage(fields[1], fields[2], fields[3], ((Long) fields[5]).longValue(),
                (boolean) fields[7], fields[8].toString());
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
    public static class Builder extends Sim0MQMessage.Builder<FS5FederatesKilledMessage.Builder>
    {
        /** did the kill operation succeed? */
        private boolean status;

        /** If there is an error, the error message is sent as well. Otherwise this field is an empty string. */
        private String error;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
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
        public FS5FederatesKilledMessage build() throws Sim0MQException, NullPointerException
        {
            return new FS5FederatesKilledMessage(this.simulationRunId, this.senderId, this.receiverId, this.messageId,
                    this.status, this.error);
        }

    }
}
