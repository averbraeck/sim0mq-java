package org.sim0mq.message.federatestarter;

import org.djutils.exceptions.Throw;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.MessageStatus;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.SimulationMessage;

/**
 * FederateStarted, FS.2. Message sent by the Federate Starter to the Federation Manager in response to message FM.1.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Apr 22, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FederateStartedMessage extends Sim0MQMessage
{
    /** */
    private static final long serialVersionUID = 20170422L;

    /**
     * The sender id of the model that was started or had an error while starting. This is exactly the same as the instanceId
     * sent by the Federation Manager in the StartFederate message.
     */
    private final String instanceId;

    /** A string that refers to the model status. Four options: "started", "running", "ended", "error". */
    private final String status;

    /** The model port number. We use an int in Java due to the fact there is no unsigned short. */
    private final int modelPort;

    /** Optional. If there is an error, the error message is sent as well. Otherwise this field is an empty string. */
    private final String error;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FS.2";

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
     * @param status A string that refers to the model status. Four options: "started", "running", "ended", "error".
     * @param modelPort The model port number. We use an int in Java due to the fact there is no unsigned short.
     * @param error Optional. If there is an error, the error message is sent as well. Otherwise this field is an empty string.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public FederateStartedMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final long messageId, final String instanceId, final String status, final int modelPort, final String error)
            throws Sim0MQException, NullPointerException
    {
        super(simulationRunId, senderId, receiverId, MESSAGETYPE, messageId, MessageStatus.NEW);

        Throw.whenNull(instanceId, "instanceId cannot be null");
        Throw.whenNull(status, "status cannot be null");
        Throw.whenNull(error, "error cannot be null");

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
     * @return messagetype
     */
    public static final String getMessageType()
    {
        return MESSAGETYPE;
    }

    /** {@inheritDoc} */
    @Override
    public Object[] createObjectArray()
    {
        return new Object[] { getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(), getMessageId(),
                getMessageStatus(), this.instanceId, this.status, this.modelPort, this.error };
    }

    /** {@inheritDoc} */
    @Override
    public byte[] createByteArray() throws Sim0MQException, SerializationException
    {
        return SimulationMessage.encodeUTF8(getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), getMessageStatus(), this.instanceId, this.status, this.modelPort, this.error);
    }

    /**
     * Build a message from an Object[] that was received.
     * @param fields Object[]; the fields in the message
     * @param intendedReceiverId id of the intended receiver
     * @return a Sim0MQ message
     * @throws Sim0MQException when number of fields is not correct
     */
    public static FederateStartedMessage createMessage(final Object[] fields, final Object intendedReceiverId)
            throws Sim0MQException
    {
        check(fields, 4, MESSAGETYPE, intendedReceiverId);
        return new FederateStartedMessage(fields[1], fields[2], fields[3], ((Long) fields[5]).longValue(), fields[8].toString(),
                fields[9].toString(), ((Short) fields[10]).intValue(), fields[11].toString());
    }

    /**
     * Builder for the FederateStarted Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
     * initial version Apr 22, 2017 <br>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FederateStartedMessage.Builder>
    {
        /**
         * The sender id of the model that was started or had an error while starting. This is exactly the same as the
         * instanceId sent by the Federation Manager in the StartFederate message.
         */
        private String instanceId;

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
        public final Builder setInstanceId(final String newInstanceId)
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
        public Sim0MQMessage build() throws Sim0MQException, NullPointerException
        {
            return new FederateStartedMessage(this.simulationRunId, this.senderId, this.receiverId, this.messageId,
                    this.instanceId, this.status, this.modelPort, this.error);
        }

    }
}
