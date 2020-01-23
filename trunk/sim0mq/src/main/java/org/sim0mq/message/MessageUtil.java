package org.sim0mq.message;

/**
 * <p>
 * Copyright (c) 2013-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version 23 Jun 2019 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class MessageUtil
{
    /** Constructor cannot be called. */
    private MessageUtil()
    {
        // Utility class
    }
    
    /**
     * Return a readable string with the bytes in a byte[] message.
     * @param bytes byte[]; the byte array to display
     * @return String; a readable string with the bytes in a byte[] message
     */
    public static String printBytes(final byte[] bytes)
    {
        StringBuffer s = new StringBuffer();
        s.append("|");
        for (int b : bytes)
        {
            if (b < 0)
            {
                b += 128;
            }
            if (b >= 32 && b <= 127)
            {
                s.append("#" + Integer.toString(b, 16).toUpperCase() + "(" + (char) (byte) b + ")|");
            }
            else
            {
                s.append("#" + Integer.toString(b, 16).toUpperCase() + "|");
            }
        }
        return s.toString();
    }
}

