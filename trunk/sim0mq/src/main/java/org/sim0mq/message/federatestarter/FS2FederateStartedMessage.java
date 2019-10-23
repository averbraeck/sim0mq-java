package org.sim0mq.message.federatestarter;

import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * FederateStarted, FS.2. Message sent by the Federate Starter to the Federation Manager in response to message FM.1.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FS2FederateStartedMessage extends Sim0MQMessage
{
    /** */
    private static final long serialVersionUID = 20170422L;

    /**
     * The sender id of the model that was started or had an error while starting. This is exactly the same as the instanceId
     * sent by the Federation Manager in the StartFederate message.
     */
    private final Object instanceId;

    /** A string that refers to the model status. Four options: "started", "running", "ended", "error". */
    private final String status;

    /** The model port number. We use an int in Java due to the fact there is no unsigned short. */
    private final int modelPort;

    /** Optional. If there is an error, the error message is sent as well. Otherwise this field is an empty string. */
    private final String error;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FS.2";

    /**
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param instanceId The sender id of the model that was started or had an error while starting. This is exactly the same as
     *            the instanceId sent by the Federation Manager in the StartFederate message.
     * @param status A string that refers to the model status. Four options: "started", "running", "ended", "error".
     * @param modelPort The model port number. We use an int in Java due to the fact there is no unsigned short.
     * @param error Optional. If there is an error, the error message is sent as well. Otherwise this field is an empty string.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public FS2FederateStartedMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final Object instanceId, final String status, final int modelPort, final String error)
            throws Sim0MQException, NullPointerException
    {
        super(true, federationId, senderId, receiverId, MESSAGETYPE, messageId,
                new Object[] {instanceId, status, modelPort, error});

        Throw.when(status.isEmpty(), Sim0MQException.class, "status cannot be empty");
        Throw.when(!status.equals("started") && !status.equals("running") && !status.equals("ended") && !status.equals("error"),
                Sim0MQException.class, "status should be one of 'started', 'running', 'ended', 'error'");
        Throw.when(modelPort < 0 || modelPort > 65535, Sim0MQException.class, "modelPort should be between 0 and 65535");

        this.instanceId = instanceId;
        this.status = status;
        this.modelPort = modelPort;
        this.error = error;
    }

    /**
     * @param objectArray Object[]; the fields that constitute the message
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FS2FederateStartedMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 4);

        this.instanceId = objectArray[8];
        Throw.when(!(objectArray[9] instanceof String), Sim0MQException.class, "status (field 9) should be a String");
        this.status = objectArray[9].toString();
        Throw.when(this.status.isEmpty(), Sim0MQException.class, "status cannot be empty");
        Throw.when(
                !this.status.equals("started") && !this.status.equals("running") && !this.status.equals("ended")
                        && !this.status.equals("error"),
                Sim0MQException.class, "status should be one of 'started', 'running', 'ended', 'error'");
        Throw.when(!(objectArray[10] instanceof Integer), Sim0MQException.class, "modelPort (field 10) should be an Integer");
        this.modelPort = (Integer) objectArray[10];
        Throw.when(this.modelPort < 0 || this.modelPort > 65535, Sim0MQException.class,
                "modelPort should be between 0 and 65535");
        Throw.when(!(objectArray[11] instanceof String), Sim0MQException.class, "error (field 11) should be a String");
        this.error = objectArray[11].toString();
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
    public String getStatus()
    {
        return this.status;
    }

    /**
     * @return modelPort
     */
    public int getModelPort()
    {
        return this.modelPort;
    }

    /**
     * @return error
     */
    public String getError()
    {
        return this.error;
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
    public static class Builder extends Sim0MQMessage.Builder<FS2FederateStartedMessage.Builder>
    {
        /**
         * The sender id of the model that was started or had an error while starting. This is exactly the same as the
         * instanceId sent by the Federation Manager in the StartFederate message.
         */
        private Object instanceId;

        /** A string that refers to the model status. Four options: "started", "running", "ended", "error". */
        private String status;

        /** The model port number. We use an int in Java due to the fact there is no unsigned short. */
        private int modelPort;

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
         * @param newInstanceId set instanceId
         * @return the original object for chaining
         */
        public final Builder setInstanceId(final Object newInstanceId)
        {
            this.instanceId = newInstanceId;
            return this;
        }

        /**
         * @param newStatus set status
         * @return the original object for chaining
         */
        public final Builder setStatus(final String newStatus)
        {
            this.status = newStatus;
            return this;
        }

        /**
         * @param newModelPort set modelPort (int instead of short because of signed short in Java)
         * @return the original object for chaining
         */
        public final Builder setModelPort(final int newModelPort)
        {
            this.modelPort = newModelPort;
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
        public FS2FederateStartedMessage build() throws Sim0MQException, NullPointerException
        {
            return new FS2FederateStartedMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.instanceId, this.status, this.modelPort, this.error);
        }

    }
}
