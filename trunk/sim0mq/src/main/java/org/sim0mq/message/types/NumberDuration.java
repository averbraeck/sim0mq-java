package org.sim0mq.message.types;

import java.io.Serializable;

import org.djunits.unit.DurationUnit;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vfloat.scalar.FloatDuration;

/**
 * Wrapper for a Number or float/double with Unit of type Duration. Store it internally as a Number <b>and</b> as a
 * DoubleScalar, and have methods to retrieve it in different ways.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
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

    /**
     * Create a duration from a Number.
     * @param duration the duration as a Number.
     */
    public NumberDuration(final Number duration)
    {
        this.duration = duration;
        this.doubleScalar = new Duration(duration.doubleValue(), DurationUnit.SI);
    }

    /**
     * Create a duration from a DoubleScalar Duration type.
     * @param duration the duration as a DoubleScalar Duration.
     */
    public NumberDuration(final Duration duration)
    {
        this.duration = duration;
        this.doubleScalar = duration;
    }

    /**
     * Create a duration from a FloatScalar FloatDuration type.
     * @param duration the duration as a FloatScalar FloatDuration.
     */
    public NumberDuration(final FloatDuration duration)
    {
        this.duration = duration;
        this.doubleScalar = new Duration(duration.getInUnit(), duration.getUnit());
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
     * @return the duration as a djunits Duration type
     */
    public Duration getDuration()
    {
        return this.doubleScalar;
    }

    /**
     * @return the duration as a djunits FloatDuration type
     */
    public FloatDuration getFloatDuration()
    {
        return new FloatDuration((float) this.doubleScalar.getInUnit(), this.doubleScalar.getUnit());
    }
}
