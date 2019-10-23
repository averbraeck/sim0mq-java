package org.sim0mq.message.federationmanager;

import java.util.LinkedHashMap;
import java.util.Map;

import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djutils.exceptions.Throw;
import org.sim0mq.Sim0MQException;
import org.sim0mq.message.Sim0MQMessage;
import org.sim0mq.message.types.NumberDuration;
import org.sim0mq.message.types.NumberTime;

/**
 * SimRunControlMessage, FM.2. Message sent by the Federation Manager to the Model to initialize a simulation.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class FM2SimRunControlMessage extends Sim0MQMessage
{
    /**
     * Duration of the run of a single replication, including the warmup duration, if present. The type is any numeric type
     * (1-5) or Float or Double with Unit (25, 26) of type Duration (25).
     */
    private final NumberDuration runDuration;

    /**
     * Warmup duration of the model in durationunits that the model uses. The type is any numeric type (1-5) or Float or Double
     * with Unit (25, 26) of type Duration (25).
     */
    private final NumberDuration warmupDuration;

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
    private final Map<Object, Long> streamMap = new LinkedHashMap<>();

    /** the unique message id. */
    private static final String MESSAGETYPE = "FM.2";

    /** */
    private static final long serialVersionUID = 20170424L;

    /**
     * @param federationId the federation id can be coded using different types. Examples are two 64-bit longs indicating a
     *            UUID, or a String with a UUID number, a String with meaningful identification, or a short or an int with a
     *            simulation run number.
     * @param senderId The sender id can be used to send back a message to the sender at some later time.
     * @param receiverId The receiver id can be used to check whether the message is meant for us, or should be discarded (or an
     *            error can be sent if we receive a message not meant for us).
     * @param messageId The unique message number is meant to confirm with a callback that the message has been received
     *            correctly. The number is unique for the sender, so not globally within the federation.
     * @param runDuration Duration of the run of a single replication, including the warmup time, if present. The type is any
     *            numeric type (1-5) or Float or Double with Unit (25, 26) of type Duration (25).
     * @param warmupDuration Warmup time of the model in time units that the model uses. The type is any numeric type (1-5) or
     *            Float or Double with Unit (25, 26) of type Duration (25).
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
    public FM2SimRunControlMessage(final Object federationId, final Object senderId, final Object receiverId,
            final Object messageId, final Object runDuration, final Object warmupDuration, final Object offsetTime,
            final double speed, final int numberReplications, final int numberRandomStreams, final Map<Object, Long> streamMap)
            throws Sim0MQException, NullPointerException
    {
        super(true, federationId, senderId, receiverId, MESSAGETYPE, messageId, createPayloadArray(runDuration, warmupDuration,
                offsetTime, speed, numberReplications, numberRandomStreams, streamMap));

        this.runDuration = NumberDuration.instantiate(runDuration);
        this.warmupDuration = NumberDuration.instantiate(warmupDuration);
        this.offsetTime = NumberTime.instantiate(offsetTime);
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
     * @param objectArray Object[]; Full message object array
     * @throws Sim0MQException on unknown data type
     * @throws NullPointerException when one of the parameters is null
     */
    public FM2SimRunControlMessage(final Object[] objectArray) throws Sim0MQException, NullPointerException
    {
        super(objectArray, calcPayloadFields(objectArray));

        this.runDuration = NumberDuration.instantiate(objectArray[8]);
        this.warmupDuration = NumberDuration.instantiate(objectArray[9]);
        this.offsetTime = NumberTime.instantiate(objectArray[10]);
        Throw.when(!(objectArray[11] instanceof Double), Sim0MQException.class, "speed (field 11) should be double");
        this.speed = ((Double) objectArray[11]).doubleValue();
        Throw.when(!(objectArray[12] instanceof Integer), Sim0MQException.class, "numberReplications (field 12) should be int");
        this.numberReplications = ((Integer) objectArray[12]).intValue();
        Throw.when(this.numberReplications <= 0, Sim0MQException.class, "numberReplications should be > 0");
        Throw.when(!(objectArray[13] instanceof Integer), Sim0MQException.class,
                "numberRandomStreams (field 13) should be int");
        this.numberRandomStreams = ((Integer) objectArray[13]).intValue();
        for (int i = 0; i < this.numberRandomStreams; i += 2)
        {
            Object id = objectArray[14 + i];
            Throw.when(!(objectArray[15 + i] instanceof Long), Sim0MQException.class,
                    "Seed (field " + (15 + i) + ") should be int");
            Long seed = (Long) objectArray[15 + i];
            this.streamMap.put(id, seed);
        }
        Throw.when(this.numberRandomStreams != this.streamMap.size(), Sim0MQException.class,
                "numberRandomStreams as given and in map are different");
    }

    /**
     * Check and make the payload for this message.
     * @param runDuration Duration of the run of a single replication, including the warmup time, if present. The type is any
     *            numeric type (1-5) or Float or Double with Unit (25, 26) of type Duration (25).
     * @param warmupDuration Warmup time of the model in time units that the model uses. The type is any numeric type (1-5) or
     *            Float or Double with Unit (25, 26) of type Duration (25).
     * @param offsetTime Offset of the time (e.g., a model time of 0 is the year 2016, or 1-1-2015). The type is any numeric
     *            type (1-5) or Float or Double with Unit (25, 26) of type Time (26).
     * @param speed Speed as the number of times real-time the model should run; Double.INFINITY means as fast as possible.
     * @param numberReplications Number of replications for stochastic uncertainties in the model.
     * @param numberRandomStreams Number of random streams that follow.
     * @param streamMap Random streams and seeds.
     * @return the object array for the payload
     * @throws NullPointerException when one of the parameters is null
     */
    private static Object[] createPayloadArray(final Object runDuration, final Object warmupDuration, final Object offsetTime,
            final double speed, final int numberReplications, final int numberRandomStreams, final Map<Object, Long> streamMap)
            throws NullPointerException
    {
        Throw.whenNull(runDuration, "runDuration cannot be null");
        Throw.whenNull(warmupDuration, "warmupDuration cannot be null");
        Throw.whenNull(offsetTime, "offsetTime cannot be null");
        Throw.whenNull(streamMap, "streamMap cannot be null");

        Object[] array = new Object[6 + 2 * streamMap.size()];
        array[0] = runDuration;
        array[1] = warmupDuration;
        array[2] = offsetTime;
        array[3] = speed;
        array[4] = numberReplications;
        array[5] = numberRandomStreams;
        int i = 6;
        for (Object key : streamMap.keySet())
        {
            array[i++] = key;
            array[i++] = streamMap.get(key);
        }
        return array;
    }

    /**
     * @param objectArray Object[]; Full message object array
     * @return the number of payload fields
     * @throws Sim0MQException when the
     */
    private static int calcPayloadFields(final Object[] objectArray) throws Sim0MQException
    {
        Throw.when(objectArray.length < 14, Sim0MQException.class, "objectArray too short -- length < 14");
        int numberRandomStreams = ((Integer) objectArray[13]).intValue();
        Throw.when(numberRandomStreams < 0, Sim0MQException.class, "numberRandomStreams should be >= 0");
        return 6 + 2 * numberRandomStreams;
    }

    /**
     * @return runDuration
     */
    public final NumberDuration getRunDuration()
    {
        return this.runDuration;
    }

    /**
     * @return warmupDuration
     */
    public final NumberDuration getWarmupDuration()
    {
        return this.warmupDuration;
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
     * Builder for the SimRunControl Message. Can string setters together, and call build() at the end to build the actual
     * message.
     * <p>
     * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved.
     * <br>
     * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
     * </p>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     */
    public static class Builder extends Sim0MQMessage.Builder<FM2SimRunControlMessage.Builder>
    {
        /**
         * Duration of the run of a single replication, including the warmup time, if present. The type is any numeric type
         * (1-5) or Float or Double with Unit (25, 26) of type Duration (25).
         */
        private Object runDuration;

        /**
         * Warmup time of the model in time units that the model uses. The type is any numeric type (1-5) or Float or Double
         * with Unit (25, 26) of type Duration (25).
         */
        private Object warmupDuration;

        /**
         * Offset of the time (e.g., a model time of 0 is the year 2016, or 1-1-2015). The type is any numeric type (1-5) or
         * Float or Double with Unit (25, 26) of type Time (26).
         */
        private Object offsetTime;

        /** Speed as the number of times real-time the model should run; Double.INFINITY means as fast as possible. */
        private double speed;

        /** Number of replications for stochastic uncertainties in the model. */
        private int numberReplications;

        /** Random streams and seeds. */
        private Map<Object, Long> streamMap = new LinkedHashMap<>();

        /**
         * Empty constructor.
         */
        public Builder()
        {
            // nothing to do.
        }

        /**
         * @param newRunDuration set runDuration
         * @return the original object for chaining
         */
        public final Builder setRunDurationNumber(final Number newRunDuration)
        {
            this.runDuration = newRunDuration;
            return this;
        }

        /**
         * @param newRunDuration set runDuration
         * @return the original object for chaining
         */
        public final Builder setRunDuration(final Duration newRunDuration)
        {
            this.runDuration = newRunDuration;
            return this;
        }

        /**
         * @param newRunDuration set runDuration
         * @return the original object for chaining
         */
        public final Builder setRunDurationFloat(final FloatDuration newRunDuration)
        {
            this.runDuration = newRunDuration;
            return this;
        }

        /**
         * @param newWarmupDuration set warmupDuration
         * @return the original object for chaining
         */
        public final Builder setWarmupDurationNumber(final Number newWarmupDuration)
        {
            this.warmupDuration = newWarmupDuration;
            return this;
        }

        /**
         * @param newWarmupDuration set warmupDuration
         * @return the original object for chaining
         */
        public final Builder setWarmupDuration(final Duration newWarmupDuration)
        {
            this.warmupDuration = newWarmupDuration;
            return this;
        }

        /**
         * @param newWarmupDuration set warmupDuration
         * @return the original object for chaining
         */
        public final Builder setWarmupDurationFloat(final FloatDuration newWarmupDuration)
        {
            this.warmupDuration = newWarmupDuration;
            return this;
        }

        /**
         * @param newOffsetTime set offsetTime
         * @return the original object for chaining
         */
        public final Builder setOffsetTimeNumber(final Number newOffsetTime)
        {
            this.offsetTime = newOffsetTime;
            return this;
        }

        /**
         * @param newOffsetTime set offsetTime
         * @return the original object for chaining
         */
        public final Builder setOffsetTime(final Time newOffsetTime)
        {
            this.offsetTime = newOffsetTime;
            return this;
        }

        /**
         * @param newOffsetTime set offsetTime
         * @return the original object for chaining
         */
        public final Builder setOffsetTimeFloat(final FloatTime newOffsetTime)
        {
            this.offsetTime = newOffsetTime;
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
        public FM2SimRunControlMessage build() throws Sim0MQException, NullPointerException
        {
            return new FM2SimRunControlMessage(this.federationId, this.senderId, this.receiverId, this.messageId,
                    this.runDuration, this.warmupDuration, this.offsetTime, this.speed, this.numberReplications,
                    this.streamMap.size(), this.streamMap);
        }

    }
}
