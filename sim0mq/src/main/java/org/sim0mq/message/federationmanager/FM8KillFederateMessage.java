package org.sim0mq.message.federationmanager;

import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;

/**
 * KillFederate, FM.8. Kill the given federate (including termination of the process on the computer / node / processor where
 * the federate is running). This message is sent to the FederateStarter.
 * <p>
 * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FM8KillFederateMessage extends Sim0MQMessage
{
    /** Id to identify the federate instance that has to be killed. */
    private final Object instanceId;

    /** the unique message id. */
    private static final String MESSAGETYPE = "FM.8";

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
     * @param instanceId String; Id to identify the federate instance that has to be killed
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FM8KillFederateMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final Object instanceId) throws Sim0MQException, NullPointerException
    {
        super(true, federationId, senderId, receiverId, MESSAGETYPE, messageId, new Object[] {instanceId});
        this.instanceId = instanceId;
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FM8KillFederateMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, 1);
        this.instanceId = objectArray[8];
    }

    /**
     * @return instanceId
     */
    public Object getInstanceId()
    {
        return this.instanceId;
    }

    /**
     * Builder for the KillFederate Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2019-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FM8KillFederateMessage.Builder>
    {
        /** Id to identify the federate instance that has to be killed. */
        private String instanceId;

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newInstanceId set id to identify the federate instance that has to be killed
         * @return the original object for chaining
         */
        public final Builder setInstanceId(final String newInstanceId)
        {
            this.instanceId = newInstanceId;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public FM8KillFederateMessage build() throws Sim0MQException, NullPointerException
        {
            return new FM8KillFederateMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.instanceId);
        }

    }
}
