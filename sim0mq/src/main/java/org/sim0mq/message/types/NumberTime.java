package org.sim0mq.message.types;

import java.io.Serializable;

import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.sim0mq.Sim0MQException;

/**
 * Wrapper for a Number or float/double with Unit of type Time. Store it internally as a Number <b>or</b> as a DoubleScalar,
 * <b>or</b> as a FloatScalar and have methods to retrieve it in different ways.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Apr 24, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class NumberTime extends Number implements Serializable
{
    /** */
    private static final long serialVersionUID = 20170424L;

    /** Number - any of the number types. */
    private final Number time;

    /** DoubleScalar of type Time. */
    private final Time doubleScalar;

    /** FloatScalar of type FloatTime. */
    private final FloatTime floatScalar;

    /**
     * Create a time from a Number.
     * @param time the time as a Number.
     */
    public NumberTime(final Number time)
    {
        this.time = time;
        this.doubleScalar = null;
        this.floatScalar = null;
    }

    /**
     * Create a time from a DoubleScalar Time type.
     * @param time the time as a DoubleScalar Time.
     */
    public NumberTime(final Time time)
    {
        this.time = null;
        this.doubleScalar = time;
        this.floatScalar = null;
    }

    /**
     * Create a time from a FloatScalar FloatTime type.
     * @param time the time as a FloatScalar FloatTime.
     */
    public NumberTime(final FloatTime time)
    {
        this.time = null;
        this.doubleScalar = null;
        this.floatScalar = time;
    }

    /**
     * Instantiate a NumberTime based on a value.
     * @param value the value that can be Time, FloatTime, or Number
     * @return an instantiation of NumberTime
     * @throws Sim0MQException if the value is neither Time, FloatTime, nor Number
     */
    public static NumberTime instantiate(final Object value) throws Sim0MQException
    {
        if (value instanceof Time)
        {
            return new NumberTime((Time) value);
        }
        else if (value instanceof FloatTime)
        {
            return new NumberTime((FloatTime) value);
        }
        else if (value instanceof Number)
        {
            return new NumberTime((Number) value);
        }
        else
        {
            throw new Sim0MQException("value should be Number, Time or FloatTime");
        }
    }
  
    /** {@inheritDoc} */
    @Override
    public int intValue()
    {
        return this.time.intValue();
    }

    /** {@inheritDoc} */
    @Override
    public long longValue()
    {
        return this.time.longValue();
    }

    /** {@inheritDoc} */
    @Override
    public float floatValue()
    {
        return this.time.floatValue();
    }

    /** {@inheritDoc} */
    @Override
    public double doubleValue()
    {
        return this.time.doubleValue();
    }

    /**
     * Return the NumberTime as an object, e.g., for serializing.
     * @return NumberTime as an object
     */
    public Object getObject()
    {
        if (this.time != null)
        {
            return this.time;
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
            throw new RuntimeException("NumberTime is neither Number, nor Time, nor FloatTime");
        }
    }

    /**
     * @return the time as a Number
     */
    public Number getNumber()
    {
        if (this.time != null)
        {
            return this.time;
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
            throw new RuntimeException("NumberTime is neither Number, nor Time, nor FloatTime");
        }
    }

    /**
     * @return the time as a djunits Time type
     */
    public Time getTime()
    {
        if (this.time != null)
        {
            return Time.instantiateSI(this.time.doubleValue());
        }
        else if (this.doubleScalar != null)
        {
            return this.doubleScalar;
        }
        else if (this.floatScalar != null)
        {
            return new Time(this.floatScalar.getInUnit(), this.floatScalar.getDisplayUnit());
        }
        else
        {
            // should never happen
            throw new RuntimeException("NumberTime is neither Number, nor Time, nor FloatTime");
        }
    }

    /**
     * @return the time as a djunits FloatTime type
     */
    public FloatTime getFloatTime()
    {
        if (this.time != null)
        {
            return FloatTime.instantiateSI(this.time.floatValue());
        }
        else if (this.doubleScalar != null)
        {
            return new FloatTime((float) this.doubleScalar.getInUnit(), this.doubleScalar.getDisplayUnit());
        }
        else if (this.floatScalar != null)
        {
            return this.floatScalar;
        }
        else
        {
            // should never happen
            throw new RuntimeException("NumberTime is neither Number, nor Time, nor FloatTime");
        }
    }
}
