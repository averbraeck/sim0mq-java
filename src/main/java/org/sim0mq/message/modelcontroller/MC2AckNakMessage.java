package org.sim0mq.message.modelcontroller;

import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.Sim0MQReply;

/**
 * AckNak, MC.2. Message sent by the Model to acknowledge the reception and implementation of a message sent by the Federation
 * Manager.
 * <p>
 * Copyright (c) 2016-2024 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class MC2AckNakMessage extends Sim0MQReply
{
    /** A string that refers to the model status. Four options: "started", "running", "ended", "error". */
    private final boolean status;

    /** Optional. If there is an error, the error message is sent as well. Otherwise this field is an empty string. */
    private final String error;

    /** the unique message id. */
    private static final String MESSAGETYPE = "MC.2";

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
     * @param uniqueId Id to identify the callback to the message.
     * @param status boolean; indicates whether the command sent by the FM has been successfully implemented, e.g. whether the
     *            run control parameters are set successfully.
     * @param error If ‘status’ is False, an error message that indicates what went wrong. Otherwise, an empty string.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public MC2AckNakMessage(final Object federationId, final Object senderId, final Object receiverId, final Object messageId,
            final Object uniqueId, final boolean status, final String error) throws Sim0MQException, NullPointerException
    {
        this(new Object[] {Sim0MQMessage.VERSION, true, federationId, senderId, receiverId, MESSAGETYPE, messageId, 3, uniqueId,
                status, error});
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public MC2AckNakMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 3, MESSAGETYPE);
        Throw.when(!(objectArray[9] instanceof Boolean), Sim0MQException.class, "status (field 9) should be Boolean");
        this.status = ((Boolean) objectArray[9]).booleanValue();
        Throw.when(!(objectArray[10] instanceof String), Sim0MQException.class, "error (field 10) should be String");
        this.error = objectArray[10].toString();
    }

    /**
     * @return status
     */
    public final boolean getStatus()
    {
        return this.status;
    }

    /**
     * @return error
     */
    public final String getError()
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
    public static class Builder extends Sim0MQReply.Builder<MC2AckNakMessage.Builder>
    {
        /** A string that refers to the model status. Four options: "started", "running", "ended", "error". */
        private boolean status;

        /** Optional. If there is an error, the error message is sent as well. Otherwise this field is an empty string. */
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

        @Override
        public MC2AckNakMessage build() throws Sim0MQException, NullPointerException
        {
            return new MC2AckNakMessage(this.federationId, this.senderId, this.receiverId, this.messageId, this.replyToId,
                    this.status, this.error);
        }

    }
}
