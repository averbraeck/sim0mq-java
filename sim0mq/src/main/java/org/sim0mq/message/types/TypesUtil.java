package org.sim0mq.message.types;

import java.util.HashMap;
import java.util.Map;

import org.djunits.unit.AreaUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MoneyPerAreaUnit;
import org.djunits.unit.MoneyPerDurationUnit;
import org.djunits.unit.MoneyPerEnergyUnit;
import org.djunits.unit.MoneyPerLengthUnit;
import org.djunits.unit.MoneyPerMassUnit;
import org.djunits.unit.MoneyPerVolumeUnit;
import org.djunits.unit.MoneyUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.VolumeUnit;
import org.sim0mq.Sim0MQException;

/**
 * Utility class to help qirth encoding / decoding data types.
 * <p>
 * Copyright (c) 2016-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Mar 4, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public final class TypesUtil
{
    /** The MoneyPerUnit cache stores the instantiated types so they are not created again and again. */
    private static Map<MoneyUnit, Map<Unit<?>, Unit<?>>> moneyPerUnitCache = new HashMap<>();

    /**
     * Utility class.
     */
    private TypesUtil()
    {
        // Utility class.
    }

    /**
     * Return the cached or created moneyPerUnitType.
     * @param moneyDisplayType the money type to use, e.g. USD
     * @param perDisplayType the per-unit to use, e.g. SQUARE_METER
     * @return the cached or created moneyPerUnitType
     */
    public static Unit<?> moneyPerUnitType(final Sim0MQDisplayType moneyDisplayType, final Sim0MQDisplayType perDisplayType)
    {
        Map<Unit<?>, Unit<?>> moneyMap = moneyPerUnitCache.get(moneyDisplayType.getDjunitsType());
        if (moneyMap == null)
        {
            moneyMap = new HashMap<>();
            moneyPerUnitCache.put((MoneyUnit) moneyDisplayType.getDjunitsType(), moneyMap);
        }
        Unit<?> moneyPerUnitType = moneyMap.get(perDisplayType.getDjunitsType());
        if (moneyPerUnitType != null)
        {
            return moneyPerUnitType;
        }
        String name = moneyDisplayType.getName() + "/" + perDisplayType.getName();
        String abbreviation = moneyDisplayType.getAbbreviation() + "/" + perDisplayType.getAbbreviation();
        if (perDisplayType.getUnitType().equals(Sim0MQUnitType.AREA))
        {
            moneyPerUnitType = new MoneyPerAreaUnit((MoneyUnit) moneyDisplayType.getDjunitsType(),
                    (AreaUnit) perDisplayType.getDjunitsType(), name, abbreviation);
        }
        else if (perDisplayType.getUnitType().equals(Sim0MQUnitType.ENERGY))
        {
            moneyPerUnitType = new MoneyPerEnergyUnit((MoneyUnit) moneyDisplayType.getDjunitsType(),
                    (EnergyUnit) perDisplayType.getDjunitsType(), name, abbreviation);
        }
        else if (perDisplayType.getUnitType().equals(Sim0MQUnitType.LENGTH))
        {
            moneyPerUnitType = new MoneyPerLengthUnit((MoneyUnit) moneyDisplayType.getDjunitsType(),
                    (LengthUnit) perDisplayType.getDjunitsType(), name, abbreviation);
        }
        else if (perDisplayType.getUnitType().equals(Sim0MQUnitType.MASS))
        {
            moneyPerUnitType = new MoneyPerMassUnit((MoneyUnit) moneyDisplayType.getDjunitsType(),
                    (MassUnit) perDisplayType.getDjunitsType(), name, abbreviation);
        }
        else if (perDisplayType.getUnitType().equals(Sim0MQUnitType.DURATION))
        {
            moneyPerUnitType = new MoneyPerDurationUnit((MoneyUnit) moneyDisplayType.getDjunitsType(),
                    (DurationUnit) perDisplayType.getDjunitsType(), name, abbreviation);
        }
        else if (perDisplayType.getUnitType().equals(Sim0MQUnitType.VOLUME))
        {
            moneyPerUnitType = new MoneyPerVolumeUnit((MoneyUnit) moneyDisplayType.getDjunitsType(),
                    (VolumeUnit) perDisplayType.getDjunitsType(), name, abbreviation);
        }
        else
        {
            throw new RuntimeException(new Sim0MQException("Unknown moneyPerUnit type: " + name));
        }
        moneyMap.put(perDisplayType.getDjunitsType(), moneyPerUnitType);
        return moneyPerUnitType;
    }

}
