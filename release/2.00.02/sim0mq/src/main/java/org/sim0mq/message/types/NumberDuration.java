package org.sim0mq.message.types;

import java.io.Serializable;

import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.sim0mq.Sim0MQException;

/**
 * Wrapper for a Number or float/double with Unit of type Duration. Store it internally as a Number <b>or</b> as a DoubleScalar,
 * <b>or</b> as a FloatScalar and have methods to retrieve it in different ways.
 * <p>
 * Copyright (c) 2016-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Apr 24, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class NumberDuration extends Number implements Serializable
{
    /** */
    private static final long serialVersionUID = 20170424L;

    /** Number - any of the number types. */
    private final Number duration;

    /** DoubleScalar of type Duration. */
    private final Duration doubleScalar;

    /** FloatScalar of type FloatDuration. */
    private final FloatDuration floatScalar;

    /**
     * Create a duration from a Number.
     * @param duration the duration as a Number.
     */
    public NumberDuration(final Number duration)
    {
        this.duration = duration;
        this.doubleScalar = null;
        this.floatScalar = null;
    }

    /**
     * Create a duration from a DoubleScalar Duration type.
     * @param duration the duration as a DoubleScalar Duration.
     */
    public NumberDuration(final Duration duration)
    {
        this.duration = null;
        this.doubleScalar = duration;
        this.floatScalar = null;
    }

    /**
     * Create a duration from a FloatScalar FloatDuration type.
     * @param duration the duration as a FloatScalar FloatDuration.
     */
    public NumberDuration(final FloatDuration duration)
    {
        this.duration = null;
        this.doubleScalar = null;
        this.floatScalar = duration;
    }

    /**
     * Instantiate a NumberDuration based on a value.
     * @param value the value that can be Duration, FloatDuration, or Number
     * @return an instantiation of NumberDuration
     * @throws Sim0MQException if the value is neither Duration, FloatDuration, nor Number
     */
    public static NumberDuration instantiate(final Object value) throws Sim0MQException
    {
        if (value instanceof Duration)
        {
            return new NumberDuration((Duration) value);
        }
        else if (value instanceof FloatDuration)
        {
            return new NumberDuration((FloatDuration) value);
        }
        else if (value instanceof Number)
        {
            return new NumberDuration((Number) value);
        }
        else
        {
            throw new Sim0MQException("value should be Number, Duration or FloatDuration");
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public int intValue()
    {
        return this.duration.intValue();
    }

    /** {@inheritDoc} */
    @Override
    public long longValue()
    {
        return this.duration.longValue();
    }

    /** {@inheritDoc} */
    @Override
    public float floatValue()
    {
        return this.duration.floatValue();
    }

    /** {@inheritDoc} */
    @Override
    public double doubleValue()
    {
        return this.duration.doubleValue();
    }

    /**
     * Return the NumberDuration as an object, e.g., for serializing.
     * @return NumberDuration as an object
     */
    public Object getObject()
    {
        if (this.duration != null)
        {
            return this.duration;
        }
        else if (this.doubleScalar != null)
        {
            return this.doubleScalar;
        }
        else if (this.floatScalar != null)
        {
            return this.floatScalar;
        }
        else
        {
            // should never happen
            throw new RuntimeException("NumberDuration is neither Number, nor Duration, nor FloatDuration");
        }
    }

    /**
     * @return the duration as a Number
     */
    public Number getNumber()
    {
        if (this.duration != null)
        {
            return this.duration;
        }
        else if (this.doubleScalar != null)
        {
            return this.doubleScalar;
        }
        else if (this.floatScalar != null)
        {
            return this.floatScalar;
        }
        else
        {
            // should never happen
            throw new RuntimeException("NumberDuration is neither Number, nor Duration, nor FloatDuration");
        }
    }

    /**
     * @return the duration as a djunits Duration type
     */
    public Duration getDuration()
    {
        if (this.duration != null)
        {
            return Duration.instantiateSI(this.duration.doubleValue());
        }
        else if (this.doubleScalar != null)
        {
            return this.doubleScalar;
        }
        else if (this.floatScalar != null)
        {
            return new Duration(this.floatScalar.getInUnit(), this.floatScalar.getDisplayUnit());
        }
        else
        {
            // should never happen
            throw new RuntimeException("NumberDuration is neither Number, nor Duration, nor FloatDuration");
        }
    }

    /**
     * @return the duration as a djunits FloatDuration type
     */
    public FloatDuration getFloatDuration()
    {
        if (this.duration != null)
        {
            return FloatDuration.instantiateSI(this.duration.floatValue());
        }
        else if (this.doubleScalar != null)
        {
            return new FloatDuration((float) this.doubleScalar.getInUnit(), this.doubleScalar.getDisplayUnit());
        }
        else if (this.floatScalar != null)
        {
            return this.floatScalar;
        }
        else
        {
            // should never happen
            throw new RuntimeException("NumberDuration is neither Number, nor Duration, nor FloatDuration");
        }
    }
}
