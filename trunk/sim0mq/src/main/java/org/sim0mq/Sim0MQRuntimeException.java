package org.sim0mq;

/**
 * Runtime Exception for the DSOL ZeroMQ bridge.
 * <p>
 * Copyright (c) 2016-2019 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Mar 1, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class Sim0MQRuntimeException extends RuntimeException
{
    /** */
    private static final long serialVersionUID = 30100L;

    /**
     * Create a ZeroMQ Exception.
     */
    public Sim0MQRuntimeException()
    {
        super();
    }

    /**
     * Create a ZeroMQ Exception.
     * @param message the message
     */
    public Sim0MQRuntimeException(final String message)
    {
        super(message);
    }

    /**
     * Create a ZeroMQ Exception.
     * @param cause the exception that caused the ZeroMQ exception
     */
    public Sim0MQRuntimeException(final Throwable cause)
    {
        super(cause);
    }

    /**
     * Create a ZeroMQ Exception.
     * @param message the message
     * @param cause the exception that caused the ZeroMQ exception
     */
    public Sim0MQRuntimeException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Create a ZeroMQ Exception.
     * @param message the message
     * @param cause the exception that caused the ZeroMQ exception
     * @param enableSuppression to enable suppressions or not
     * @param writableStackTrace to have a writable stack trace or not
     */
    public Sim0MQRuntimeException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
