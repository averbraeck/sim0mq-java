package org.sim0mq.message.federationmanager;

import java.util.HashMap;
import java.util.Map;

import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djutils.exceptions.Throw;
import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.MessageStatus;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.SimulationMessage;
import org.sim0mq.message.types.NumberDuration;
import org.sim0mq.message.types.NumberTime;

/**
 * SimRunControlMessage, FM.2. Message sent by the Federation Manager to the Model to initialize a simulation.
 * <p>
 * Copyright (c) 2016-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Apr 22, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class SimRunControlMessage extends Sim0MQMessage
{
    /**
     * Duration of the run of a single replication, including the warmup time, if present. The type is any numeric type (1-5) or
     * Float or Double with Unit (25, 26) of type Duration (25).
     */
    private final NumberDuration runTime;

    /**
     * Warmup time of the model in time units that the model uses. The type is any numeric type (1-5) or Float or Double with
     * Unit (25, 26) of type Duration (25).
     */
    private final NumberDuration warmupTime;

    /**
     * Offset of the time (e.g., a model time of 0 is the year 2016, or 1-1-2015). The type is any numeric type (1-5) or Float
     * or Double with Unit (25, 26) of type Time (26).
     */
    private final NumberTime offsetTime;

    /** Speed as the number of times real-time the model should run; Double.INFINITY means as fast as possible. */
    private final double speed;

    /** Number of replications for stochastic uncertainties in the model. */
    private final int numberReplications;

    /** Number of random streams that follow. */
    private final int numberRandomStreams;

    /** Random streams and seeds. */
    private final Map<Object, Long> streamMap = new HashMap<>();

    /** the unique message id. */
    private static final String MESSAGETYPE = "FM.2";

    /** */
    private static final long serialVersionUID = 20170424L;

    /**
     * @param simulationRunId the Simulation run ids can be provided in different types. Examples are two 64-bit longs
     *            indicating a UUID, or a String with a UUID number, a String with meaningful identification, or a short or an
     *            int with a simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param runTime Duration of the run of a single replication, including the warmup time, if present. The type is any
     *            numeric type (1-5) or Float or Double with Unit (25, 26) of type Duration (25).
     * @param warmupTime Warmup time of the model in time units that the model uses. The type is any numeric type (1-5) or Float
     *            or Double with Unit (25, 26) of type Duration (25).
     * @param offsetTime Offset of the time (e.g., a model time of 0 is the year 2016, or 1-1-2015). The type is any numeric
     *            type (1-5) or Float or Double with Unit (25, 26) of type Time (26).
     * @param speed Speed as the number of times real-time the model should run; Double.INFINITY means as fast as possible.
     * @param numberReplications Number of replications for stochastic uncertainties in the model.
     * @param numberRandomStreams Number of random streams that follow.
     * @param streamMap Random streams and seeds.
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public SimRunControlMessage(final Object simulationRunId, final Object senderId, final Object receiverId,
            final long messageId, final Object runTime, final Object warmupTime, final Object offsetTime, final double speed,
            final int numberReplications, final int numberRandomStreams, final Map<Object, Long> streamMap)
            throws Sim0MQException, NullPointerException
    {
        super(simulationRunId, senderId, receiverId, MESSAGETYPE, messageId, MessageStatus.NEW);
        Throw.whenNull(runTime, "runTime cannot be null");
        Throw.whenNull(warmupTime, "warmupTime cannot be null");
        Throw.whenNull(offsetTime, "offsetTime cannot be null");
        Throw.whenNull(streamMap, "streamMap cannot be null");

        if (runTime instanceof Number)
        {
            this.runTime = new NumberDuration((Number) runTime);
        }
        else if (runTime instanceof Duration)
        {
            this.runTime = new NumberDuration((Duration) runTime);
        }
        else if (runTime instanceof FloatDuration)
        {
            this.runTime = new NumberDuration((FloatDuration) runTime);
        }
        else
        {
            throw new Sim0MQException("runTime should be Number, Duration or FloatDuration");
        }

        if (warmupTime instanceof Number)
        {
            this.warmupTime = new NumberDuration((Number) warmupTime);
        }
        else if (warmupTime instanceof Duration)
        {
            this.warmupTime = new NumberDuration((Duration) warmupTime);
        }
        else if (warmupTime instanceof FloatDuration)
        {
            this.warmupTime = new NumberDuration((FloatDuration) warmupTime);
        }
        else
        {
            throw new Sim0MQException("warmupTime should be Number, Duration or FloatDuration");
        }

        if (offsetTime instanceof Number)
        {
            this.offsetTime = new NumberTime((Number) offsetTime);
        }
        else if (offsetTime instanceof Time)
        {
            this.offsetTime = new NumberTime((Time) offsetTime);
        }
        else if (offsetTime instanceof FloatTime)
        {
            this.offsetTime = new NumberTime((FloatTime) offsetTime);
        }
        else
        {
            throw new Sim0MQException("offsetTime should be Number, Time or FloatTime");
        }

        this.speed = speed;

        Throw.when(numberReplications <= 0, Sim0MQException.class, "numberReplications should be > 0");
        this.numberReplications = numberReplications;

        Throw.when(numberRandomStreams < 0, Sim0MQException.class, "numberRandomStreams should be >= 0");
        Throw.when(numberRandomStreams != streamMap.size(), Sim0MQException.class,
                "numberRandomStreams as given and in map are different");
        this.numberRandomStreams = numberRandomStreams;
        this.streamMap.putAll(streamMap);
    }

    /**
     * @return runTime
     */
    public final NumberDuration getRunTime()
    {
        return this.runTime;
    }

    /**
     * @return warmupTime
     */
    public final NumberDuration getWarmupTime()
    {
        return this.warmupTime;
    }

    /**
     * @return offsetTime
     */
    public final NumberTime getOffsetTime()
    {
        return this.offsetTime;
    }

    /**
     * @return speed
     */
    public final double getSpeed()
    {
        return this.speed;
    }

    /**
     * @return numberReplications
     */
    public final int getNumberReplications()
    {
        return this.numberReplications;
    }

    /**
     * @return numberRandomStreams
     */
    public final int getNumberRandomStreams()
    {
        return this.numberRandomStreams;
    }

    /**
     * @return streamMap
     */
    public final Map<Object, Long> getStreamMap()
    {
        return this.streamMap;
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
                getMessageStatus(), this.runTime, this.warmupTime, this.offsetTime, this.speed, this.numberReplications,
                this.numberRandomStreams, this.streamMap };
    }

    /** {@inheritDoc} */
    @Override
    public byte[] createByteArray() throws Sim0MQException, SerializationException
    {
        return SimulationMessage.encodeUTF8(getSimulationRunId(), getSenderId(), getReceiverId(), getMessageTypeId(),
                getMessageId(), getMessageStatus(), this.runTime, this.warmupTime, this.offsetTime, this.speed,
                this.numberReplications, this.numberRandomStreams, this.streamMap);
    }

    /**
     * Build a message from an Object[] that was received.
     * @param fields Object[]; the fields in the message
     * @param intendedReceiverId id of the intended receiver
     * @return a Sim0MQ message
     * @throws Sim0MQException when number of fields is not correct
     */
    public static SimRunControlMessage createMessage(final Object[] fields, final Object intendedReceiverId)
            throws Sim0MQException
    {
        Map<Object, Long> streams = new HashMap<>();
        int numberStreams = ((Integer) fields[13]).intValue();
        check(fields, 6 + 2 * numberStreams, MESSAGETYPE, intendedReceiverId);
        for (int i = 14; i < 14 + 2 * numberStreams; i += 2)
        {
            Object streamId = fields[i];
            Long seed = ((Long) fields[i + 1]).longValue();
            streams.put(streamId, seed);
        }
        return new SimRunControlMessage(fields[1], fields[2], fields[3], ((Long) fields[5]).longValue(), fields[8], fields[9],
                fields[10], ((Double) fields[11]).doubleValue(), ((Integer) fields[12]).intValue(), numberStreams, streams);
    }

    /**
     * Builder for the SimRunControl Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2016-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
     * initial version Apr 22, 2017 <br>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<SimRunControlMessage.Builder>
    {
        /**
         * Duration of the run of a single replication, including the warmup time, if present. The type is any numeric type
         * (1-5) or Float or Double with Unit (25, 26) of type Duration (25).
         */
        private NumberDuration runTime;

        /**
         * Warmup time of the model in time units that the model uses. The type is any numeric type (1-5) or Float or Double
         * with Unit (25, 26) of type Duration (25).
         */
        private NumberDuration warmupTime;

        /**
         * Offset of the time (e.g., a model time of 0 is the year 2016, or 1-1-2015). The type is any numeric type (1-5) or
         * Float or Double with Unit (25, 26) of type Time (26).
         */
        private NumberTime offsetTime;

        /** Speed as the number of times real-time the model should run; Double.INFINITY means as fast as possible. */
        private double speed;

        /** Number of replications for stochastic uncertainties in the model. */
        private int numberReplications;

        /** Random streams and seeds. */
        private Map<Object, Long> streamMap = new HashMap<>();

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newRunTime set runTime
         * @return the original object for chaining
         */
        public final Builder setRunTime(final Number newRunTime)
        {
            this.runTime = new NumberDuration(newRunTime);
            return this;
        }

        /**
         * @param newRunTime set runTime
         * @return the original object for chaining
         */
        public final Builder setRunTime(final Duration newRunTime)
        {
            this.runTime = new NumberDuration(newRunTime);
            return this;
        }

        /**
         * @param newRunTime set runTime
         * @return the original object for chaining
         */
        public final Builder setRunTime(final FloatDuration newRunTime)
        {
            this.runTime = new NumberDuration(newRunTime);
            return this;
        }

        /**
         * @param newWarmupTime set warmupTime
         * @return the original object for chaining
         */
        public final Builder setWarmupTime(final Number newWarmupTime)
        {
            this.warmupTime = new NumberDuration(newWarmupTime);
            return this;
        }

        /**
         * @param newWarmupTime set warmupTime
         * @return the original object for chaining
         */
        public final Builder setWarmupTime(final Duration newWarmupTime)
        {
            this.warmupTime = new NumberDuration(newWarmupTime);
            return this;
        }

        /**
         * @param newWarmupTime set warmupTime
         * @return the original object for chaining
         */
        public final Builder setWarmupTime(final FloatDuration newWarmupTime)
        {
            this.warmupTime = new NumberDuration(newWarmupTime);
            return this;
        }

        /**
         * @param newOffsetTime set offsetTime
         * @return the original object for chaining
         */
        public final Builder setOffsetTime(final Number newOffsetTime)
        {
            this.offsetTime = new NumberTime(newOffsetTime);
            return this;
        }

        /**
         * @param newOffsetTime set offsetTime
         * @return the original object for chaining
         */
        public final Builder setOffsetTime(final Duration newOffsetTime)
        {
            this.offsetTime = new NumberTime(newOffsetTime);
            return this;
        }

        /**
         * @param newOffsetTime set offsetTime
         * @return the original object for chaining
         */
        public final Builder setOffsetTime(final FloatDuration newOffsetTime)
        {
            this.offsetTime = new NumberTime(newOffsetTime);
            return this;
        }

        /**
         * @param newSpeed set speed
         * @return the original object for chaining
         */
        public final Builder setSpeed(final double newSpeed)
        {
            this.speed = newSpeed;
            return this;
        }

        /**
         * @param newNumberReplications set numberReplications
         * @return the original object for chaining
         */
        public final Builder setNumberReplications(final int newNumberReplications)
        {
            this.numberReplications = newNumberReplications;
            return this;
        }

        /**
         * @param newStreamMap set streamMap
         * @return the original object for chaining
         */
        public final Builder setStreamMap(final Map<Object, Long> newStreamMap)
        {
            this.streamMap = newStreamMap;
            return this;
        }

        /** {@inheritDoc} */
        @Override
        public Sim0MQMessage build() throws Sim0MQException, NullPointerException
        {
            return new SimRunControlMessage(this.simulationRunId, this.senderId, this.receiverId, this.messageId, this.runTime,
                    this.warmupTime, this.offsetTime, this.speed, this.numberReplications, this.streamMap.size(),
                    this.streamMap);
        }

    }
}
