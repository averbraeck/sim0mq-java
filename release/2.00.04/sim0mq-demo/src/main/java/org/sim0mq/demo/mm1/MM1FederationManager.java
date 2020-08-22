package org.sim0mq.demo.mm1;

import java.util.concurrent.atomic.AtomicLong;

import org.djutils.serialization.SerializationException;
import org.sim0mq.Sim0MQException;

/**
 * Example implementation of a FederationManager to start the MM1Queue41Application DSOL model.
 * <p>
 * Copyright (c) 2016-2020 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version April 10, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class MM1FederationManager
{
    /** */
    private MM1FederationManager()
    {
        // Utility class
    }

    /**
     * @param args parameters for main
     * @throws Sim0MQException on error
     * @throws SerializationException on serialization problem
     */
    public static void main(final String[] args) throws Sim0MQException, SerializationException
    {
        if (args.length < 5)
        {
            System.err.println("Use as FederationManager federationName federationManagerPortNumber "
                    + "federateStarterIPorName federateStarterPortNumber modelFolder");
            System.exit(-1);
        }
        String federationName = args[0];

        String fmsPort = args[1];
        int fmPort = 0;
        try
        {
            fmPort = Integer.parseInt(fmsPort);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Use as FederationManager fedName fmPort fsIP fsPort modelFolder, where fmPort is a number");
            System.exit(-1);
        }
        if (fmPort == 0 || fmPort > 65535)
        {
            System.err.println("fmPort should be between 1 and 65535");
            System.exit(-1);
        }

        String fsServerNameOrIP = args[2];

        String fsPortString = args[3];
        int fsPort = 0;
        try
        {
            fsPort = Integer.parseInt(fsPortString);
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Use as FederationManager fedName fmPort fsIP fsPort modelFolder, where fmPort is a number");
            System.exit(-1);
        }
        if (fsPort == 0 || fsPort > 65535)
        {
            System.err.println("fsPort should be between 1 and 65535");
            System.exit(-1);
        }

        String mm1ModelFolder = args[4];

        AtomicLong messageCount = new AtomicLong(0L);
        new MM1FederationManager20.StateMachine(messageCount, federationName, fsServerNameOrIP, fsPort, mm1ModelFolder, 1);
    }

}
