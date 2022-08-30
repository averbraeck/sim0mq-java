package org.sim0mq.message;

/**
 * String encoding indicating UTF8 or UTF16 strings.
 * <p>
 * Copyright (c) 2013-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Aug 9, 2018 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public enum StringEncoding
{
    /** 8 bits String and char encoding. */
    UTF8,

    /** 16 bits String and char encoding. */
    UTF16;

    /**
     * Return whether the string encoding is UTF8.
     * @return boolean; indicates whether the String encoding is UTF8
     */
    public boolean isUTF8()
    {
        return this.equals(UTF8);
    }

    /**
     * Return whether the string encoding is UTF16.
     * @return boolean; indicates whether the String encoding is UTF16
     */
    public boolean isUTF16()
    {
        return this.equals(UTF16);
    }
}
