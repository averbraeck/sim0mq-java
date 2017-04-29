package org.sim0mq.message.types;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.djunits.unit.AbsoluteTemperatureUnit;
import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.AngleSolidUnit;
import org.djunits.unit.AngleUnit;
import org.djunits.unit.AreaUnit;
import org.djunits.unit.DensityUnit;
import org.djunits.unit.DimensionlessUnit;
import org.djunits.unit.DirectionUnit;
import org.djunits.unit.DurationUnit;
import org.djunits.unit.ElectricalChargeUnit;
import org.djunits.unit.ElectricalCurrentUnit;
import org.djunits.unit.ElectricalPotentialUnit;
import org.djunits.unit.ElectricalResistanceUnit;
import org.djunits.unit.EnergyUnit;
import org.djunits.unit.FlowMassUnit;
import org.djunits.unit.FlowVolumeUnit;
import org.djunits.unit.ForceUnit;
import org.djunits.unit.FrequencyUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.LinearDensityUnit;
import org.djunits.unit.MassUnit;
import org.djunits.unit.MoneyUnit;
import org.djunits.unit.PositionUnit;
import org.djunits.unit.PowerUnit;
import org.djunits.unit.PressureUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TemperatureUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.unit.TorqueUnit;
import org.djunits.unit.Unit;
import org.djunits.unit.VolumeUnit;

/**
 * DJUNITS Display Types to be used as part of a Sim0MQ message.
 * <p>
 * Copyright (c) 2016-2017 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://sim0mq.org/docs/current/license.html">Sim0MQ License</a>.
 * </p>
 * $LastChangedDate: 2015-07-24 02:58:59 +0200 (Fri, 24 Jul 2015) $, @version $Revision: 1147 $, by $Author: averbraeck $,
 * initial version Mar 4, 2017 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class Sim0MQDisplayType implements Serializable
{
    /** */
    private static final long serialVersionUID = 20170314L;

    /* ================================================== DIMENSIONLESS ================================================== */

    /** Dimensionless.SI unit type with code 0. */
    public static final Sim0MQDisplayType DIMENSIONLESS_SI =
            new Sim0MQDisplayType(Sim0MQUnitType.DIMENSIONLESS, 0, DimensionlessUnit.SI, "SI", "[]");

    /* ================================================== ACCELERATION =================================================== */

    /** Acceleration.METER_PER_SECOND_2 unit type with code 0. */
    public static final Sim0MQDisplayType ACCELERATION_METER_PER_SECOND_2 = new Sim0MQDisplayType(Sim0MQUnitType.ACCELERATION,
            0, AccelerationUnit.METER_PER_SECOND_2, "METER_PER_SECOND_2", "m/s2");

    /** Acceleration.KM_PER_HOUR_2 unit type with code 1. */
    public static final Sim0MQDisplayType ACCELERATION_KM_PER_HOUR_2 =
            new Sim0MQDisplayType(Sim0MQUnitType.ACCELERATION, 1, AccelerationUnit.KM_PER_HOUR_2, "KM_PER_HOUR_2", "km/h2");

    /** Acceleration.INCH_PER_SECOND_2 unit type with code 2. */
    public static final Sim0MQDisplayType ACCELERATION_INCH_PER_SECOND_2 = new Sim0MQDisplayType(Sim0MQUnitType.ACCELERATION, 2,
            AccelerationUnit.INCH_PER_SECOND_2, "INCH_PER_SECOND_2", "in/s2");

    /** Acceleration.FOOT_PER_SECOND_2 unit type with code 3. */
    public static final Sim0MQDisplayType ACCELERATION_FOOT_PER_SECOND_2 = new Sim0MQDisplayType(Sim0MQUnitType.ACCELERATION, 3,
            AccelerationUnit.FOOT_PER_SECOND_2, "FOOT_PER_SECOND_2", "ft/s2");

    /** Acceleration.MILE_PER_HOUR_2 unit type with code 4. */
    public static final Sim0MQDisplayType ACCELERATION_MILE_PER_HOUR_2 =
            new Sim0MQDisplayType(Sim0MQUnitType.ACCELERATION, 4, AccelerationUnit.MILE_PER_HOUR_2, "MILE_PER_HOUR_2", "mi/h2");

    /** Acceleration.MILE_PER_HOUR_PER_SECOND unit type with code 5. */
    public static final Sim0MQDisplayType ACCELERATION_MILE_PER_HOUR_PER_SECOND = new Sim0MQDisplayType(
            Sim0MQUnitType.ACCELERATION, 5, AccelerationUnit.MILE_PER_HOUR_PER_SECOND, "MILE_PER_HOUR_PER_SECOND", "mi/h/s");

    /** Acceleration.KNOT_PER_SECOND unit type with code 6. */
    public static final Sim0MQDisplayType ACCELERATION_KNOT_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.ACCELERATION, 6, AccelerationUnit.KNOT_PER_SECOND, "KNOT_PER_SECOND", "kt/s");

    /** Acceleration.GAL unit type with code 7. */
    public static final Sim0MQDisplayType ACCELERATION_GAL =
            new Sim0MQDisplayType(Sim0MQUnitType.ACCELERATION, 7, AccelerationUnit.GAL, "GAL", "gal");

    /** Acceleration.STANDARD_GRAVITY unit type with code 8. */
    public static final Sim0MQDisplayType ACCELERATION_STANDARD_GRAVITY =
            new Sim0MQDisplayType(Sim0MQUnitType.ACCELERATION, 8, AccelerationUnit.STANDARD_GRAVITY, "STANDARD_GRAVITY", "g");

    /* =================================================== ANGLESOLID ==================================================== */

    /** AngleSolid.STERADIAN unit type with code 0. */
    public static final Sim0MQDisplayType ANGLESOLID_STERADIAN =
            new Sim0MQDisplayType(Sim0MQUnitType.ANGLESOLID, 0, AngleSolidUnit.STERADIAN, "STERADIAN", "sr");

    /** AngleSolid.SQUARE_DEGREE unit type with code 1. */
    public static final Sim0MQDisplayType ANGLESOLID_SQUARE_DEGREE =
            new Sim0MQDisplayType(Sim0MQUnitType.ANGLESOLID, 1, AngleSolidUnit.SQUARE_DEGREE, "SQUARE_DEGREE", "sq.deg");

    /* ====================================================== ANGLE ====================================================== */

    /** Angle.RADIAN unit type with code 0. */
    public static final Sim0MQDisplayType ANGLE_RADIAN =
            new Sim0MQDisplayType(Sim0MQUnitType.ANGLE, 0, AngleUnit.RADIAN, "RADIAN", "rad");

    /** Angle.ARCMINUTE unit type with code 1. */
    public static final Sim0MQDisplayType ANGLE_ARCMINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.ANGLE, 1, AngleUnit.ARCMINUTE, "ARCMINUTE", "arcmin");

    /** Angle.ARCSECOND unit type with code 2. */
    public static final Sim0MQDisplayType ANGLE_ARCSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.ANGLE, 2, AngleUnit.ARCSECOND, "ARCSECOND", "arcsec");

    /** Angle.CENTESIMAL_ARCMINUTE unit type with code 3. */
    public static final Sim0MQDisplayType ANGLE_CENTESIMAL_ARCMINUTE = new Sim0MQDisplayType(Sim0MQUnitType.ANGLE, 3,
            AngleUnit.CENTESIMAL_ARCMINUTE, "CENTESIMAL_ARCMINUTE", "centesimal_arcmin");

    /** Angle.CENTESIMAL_ARCSECOND unit type with code 4. */
    public static final Sim0MQDisplayType ANGLE_CENTESIMAL_ARCSECOND = new Sim0MQDisplayType(Sim0MQUnitType.ANGLE, 4,
            AngleUnit.CENTESIMAL_ARCSECOND, "CENTESIMAL_ARCSECOND", "centesimal_arcsec");

    /** Angle.DEGREE unit type with code 5. */
    public static final Sim0MQDisplayType ANGLE_DEGREE =
            new Sim0MQDisplayType(Sim0MQUnitType.ANGLE, 5, AngleUnit.DEGREE, "DEGREE", "deg");

    /** Angle.GRAD unit type with code 6. */
    public static final Sim0MQDisplayType ANGLE_GRAD =
            new Sim0MQDisplayType(Sim0MQUnitType.ANGLE, 6, AngleUnit.GRAD, "GRAD", "grad");

    /* ==================================================== DIRECTION ==================================================== */

    /** Direction.NORTH_RADIAN unit type with code 0. */
    public static final Sim0MQDisplayType DIRECTION_NORTH_RADIAN =
            new Sim0MQDisplayType(Sim0MQUnitType.DIRECTION, 0, DirectionUnit.NORTH_RADIAN, "NORTH_RADIAN", "rad(N)");

    /** Direction.NORTH_DEGREE unit type with code 1. */
    public static final Sim0MQDisplayType DIRECTION_NORTH_DEGREE =
            new Sim0MQDisplayType(Sim0MQUnitType.DIRECTION, 1, DirectionUnit.NORTH_DEGREE, "NORTH_DEGREE", "deg(N)");

    /** Direction.EAST_RADIAN unit type with code 2. */
    public static final Sim0MQDisplayType DIRECTION_EAST_RADIAN =
            new Sim0MQDisplayType(Sim0MQUnitType.DIRECTION, 2, DirectionUnit.EAST_RADIAN, "EAST_RADIAN", "rad(E)");

    /** Direction.EAST_DEGREE unit type with code 2. */
    public static final Sim0MQDisplayType DIRECTION_EAST_DEGREE =
            new Sim0MQDisplayType(Sim0MQUnitType.DIRECTION, 3, DirectionUnit.EAST_DEGREE, "EAST_DEGREE", "deg(E)");

    /* ====================================================== AREA ======================================================= */

    /** Area.SQUARE_METER unit type with code 0. */
    public static final Sim0MQDisplayType AREA_SQUARE_METER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 0, AreaUnit.SQUARE_METER, "SQUARE_METER", "m2");

    /** Area.SQUARE_ATTOMETER unit type with code 1. */
    public static final Sim0MQDisplayType AREA_SQUARE_ATTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 1, AreaUnit.SQUARE_ATTOMETER, "SQUARE_ATTOMETER", "am2");

    /** Area.SQUARE_FEMTOMETER unit type with code 2. */
    public static final Sim0MQDisplayType AREA_SQUARE_FEMTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 2, AreaUnit.SQUARE_FEMTOMETER, "SQUARE_FEMTOMETER", "fm2");

    /** Area.SQUARE_PICOMETER unit type with code 3. */
    public static final Sim0MQDisplayType AREA_SQUARE_PICOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 3, AreaUnit.SQUARE_PICOMETER, "SQUARE_PICOMETER", "pm2");

    /** Area.SQUARE_NANOMETER unit type with code 4. */
    public static final Sim0MQDisplayType AREA_SQUARE_NANOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 4, AreaUnit.SQUARE_NANOMETER, "SQUARE_NANOMETER", "nm2");

    /** Area.SQUARE_MICROMETER unit type with code 5. */
    public static final Sim0MQDisplayType AREA_SQUARE_MICROMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 5, AreaUnit.SQUARE_MICROMETER, "SQUARE_MICROMETER", "μm2");

    /** Area.SQUARE_MILLIMETER unit type with code 6. */
    public static final Sim0MQDisplayType AREA_SQUARE_MILLIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 6, AreaUnit.SQUARE_MILLIMETER, "SQUARE_MILLIMETER", "mm2");

    /** Area.SQUARE_CENTIMETER unit type with code 7. */
    public static final Sim0MQDisplayType AREA_SQUARE_CENTIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 7, AreaUnit.SQUARE_CENTIMETER, "SQUARE_CENTIMETER", "cm2");

    /** Area.SQUARE_DECIMETER unit type with code 8. */
    public static final Sim0MQDisplayType AREA_SQUARE_DECIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 8, AreaUnit.SQUARE_DECIMETER, "SQUARE_DECIMETER", "dm2");

    /** Area.SQUARE_DEKAMETER unit type with code 9. */
    public static final Sim0MQDisplayType AREA_SQUARE_DEKAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 9, AreaUnit.SQUARE_DEKAMETER, "SQUARE_DEKAMETER", "dam2");

    /** Area.SQUARE_HECTOMETER unit type with code 10. */
    public static final Sim0MQDisplayType AREA_SQUARE_HECTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 10, AreaUnit.SQUARE_HECTOMETER, "SQUARE_HECTOMETER", "hm2");

    /** Area.SQUARE_KILOMETER unit type with code 11. */
    public static final Sim0MQDisplayType AREA_SQUARE_KILOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 11, AreaUnit.SQUARE_KILOMETER, "SQUARE_KILOMETER", "km2");

    /** Area.SQUARE_MEGAMETER unit type with code 12. */
    public static final Sim0MQDisplayType AREA_SQUARE_MEGAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 12, AreaUnit.SQUARE_MEGAMETER, "SQUARE_MEGAMETER", "Mm2");

    /** Area.SQUARE_INCH unit type with code 13. */
    public static final Sim0MQDisplayType AREA_SQUARE_INCH =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 13, AreaUnit.SQUARE_INCH, "SQUARE_INCH", "in2");

    /** Area.SQUARE_FOOT unit type with code 14. */
    public static final Sim0MQDisplayType AREA_SQUARE_FOOT =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 14, AreaUnit.SQUARE_FOOT, "SQUARE_FOOT", "ft2");

    /** Area.SQUARE_YARD unit type with code 15. */
    public static final Sim0MQDisplayType AREA_SQUARE_YARD =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 15, AreaUnit.SQUARE_YARD, "SQUARE_YARD", "yd2");

    /** Area.SQUARE_MILE unit type with code 16. */
    public static final Sim0MQDisplayType AREA_SQUARE_MILE =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 16, AreaUnit.SQUARE_MILE, "SQUARE_MILE", "mi2");

    /** Area.SQUARE_NAUTICAL_MILE unit type with code 17. */
    public static final Sim0MQDisplayType AREA_SQUARE_NAUTICAL_MILE =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 17, AreaUnit.SQUARE_NAUTICAL_MILE, "SQUARE_NAUTICAL_MILE", "NM2");

    /** Area.ACRE unit type with code 18. */
    public static final Sim0MQDisplayType AREA_ACRE =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 18, AreaUnit.ACRE, "ACRE", "acre");

    /** Area.ARE unit type with code 19. */
    public static final Sim0MQDisplayType AREA_ARE = new Sim0MQDisplayType(Sim0MQUnitType.AREA, 19, AreaUnit.ARE, "ARE", "a");

    /** Area.CENTIARE unit type with code 20. */
    public static final Sim0MQDisplayType AREA_CENTIARE =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 20, AreaUnit.CENTIARE, "CENTIARE", "ca");

    /** Area.HECTARE unit type with code 21. */
    public static final Sim0MQDisplayType AREA_HECTARE =
            new Sim0MQDisplayType(Sim0MQUnitType.AREA, 21, AreaUnit.HECTARE, "HECTARE", "ha");

    /* ===================================================== DENSITY ===================================================== */

    /** Density.KG_PER_METER_3 unit type with code 0. */
    public static final Sim0MQDisplayType DENSITY_KG_PER_METER_3 =
            new Sim0MQDisplayType(Sim0MQUnitType.DENSITY, 0, DensityUnit.KG_PER_METER_3, "KG_PER_METER_3", "kg/m3");

    /** Density.GRAM_PER_CENTIMETER_3 unit type with code 1. */
    public static final Sim0MQDisplayType DENSITY_GRAM_PER_CENTIMETER_3 = new Sim0MQDisplayType(Sim0MQUnitType.DENSITY, 1,
            DensityUnit.GRAM_PER_CENTIMETER_3, "GRAM_PER_CENTIMETER_3", "g/cm3");

    /* ================================================ ELECTRICALCHARGE ================================================= */

    /** ElectricalCharge.COULOMB unit type with code 0. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_COULOMB =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 0, ElectricalChargeUnit.COULOMB, "COULOMB", "C");

    /** ElectricalCharge.PICOCOULOMB unit type with code 1. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_PICOCOULOMB =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 1, ElectricalChargeUnit.PICOCOULOMB, "PICOCOULOMB", "pC");

    /** ElectricalCharge.NANOCOULOMB unit type with code 2. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_NANOCOULOMB =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 2, ElectricalChargeUnit.NANOCOULOMB, "NANOCOULOMB", "nC");

    /** ElectricalCharge.MICROCOULOMB unit type with code 3. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_MICROCOULOMB =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 3, ElectricalChargeUnit.MICROCOULOMB, "MICROCOULOMB", "μC");

    /** ElectricalCharge.MILLICOULOMB unit type with code 4. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_MILLICOULOMB =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 4, ElectricalChargeUnit.MILLICOULOMB, "MILLICOULOMB", "mC");

    /** ElectricalCharge.ABCOULOMB unit type with code 5. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_ABCOULOMB =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 5, ElectricalChargeUnit.ABCOULOMB, "ABCOULOMB", "abC");

    /** ElectricalCharge.ATOMIC_UNIT unit type with code 6. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_ATOMIC_UNIT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 6, ElectricalChargeUnit.ATOMIC_UNIT, "ATOMIC_UNIT", "au");

    /** ElectricalCharge.EMU unit type with code 7. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_EMU =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 7, ElectricalChargeUnit.EMU, "EMU", "emu");

    /** ElectricalCharge.ESU unit type with code 8. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_ESU =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 8, ElectricalChargeUnit.ESU, "ESU", "esu");

    /** ElectricalCharge.FARADAY unit type with code 9. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_FARADAY =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 9, ElectricalChargeUnit.FARADAY, "FARADAY", "F");

    /** ElectricalCharge.FRANKLIN unit type with code 10. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_FRANKLIN =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 10, ElectricalChargeUnit.FRANKLIN, "FRANKLIN  ", "Fr");

    /** ElectricalCharge.STATCOULOMB unit type with code 11. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_STATCOULOMB = new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE,
            11, ElectricalChargeUnit.STATCOULOMB, "STATCOULOMB", "statC");

    /** ElectricalCharge.MILLIAMPERE_HOUR unit type with code 12. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_MILLIAMPERE_HOUR = new Sim0MQDisplayType(
            Sim0MQUnitType.ELECTRICALCHARGE, 12, ElectricalChargeUnit.MILLIAMPERE_HOUR, "MILLIAMPERE_HOUR", "mAh");

    /** ElectricalCharge.AMPERE_HOUR unit type with code 13. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_AMPERE_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCHARGE, 13, ElectricalChargeUnit.AMPERE_HOUR, "AMPERE_HOUR", "Ah");

    /** ElectricalCharge.KILOAMPERE_HOUR unit type with code 14. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_KILOAMPERE_HOUR = new Sim0MQDisplayType(
            Sim0MQUnitType.ELECTRICALCHARGE, 14, ElectricalChargeUnit.KILOAMPERE_HOUR, "KILOAMPERE_HOUR", "kAh");

    /** ElectricalCharge.MEGAAMPERE_HOUR unit type with code 15. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_MEGAAMPERE_HOUR = new Sim0MQDisplayType(
            Sim0MQUnitType.ELECTRICALCHARGE, 15, ElectricalChargeUnit.MEGAAMPERE_HOUR, "MEGAAMPERE_HOUR", "MAh");

    /** ElectricalCharge.MILLIAMPERE_SECOND unit type with code 16. */
    public static final Sim0MQDisplayType ELECTRICALCHARGE_MILLIAMPERE_SECOND = new Sim0MQDisplayType(
            Sim0MQUnitType.ELECTRICALCHARGE, 16, ElectricalChargeUnit.MILLIAMPERE_SECOND, "MILLIAMPERE_SECOND", "mAs");

    /* ============================================== ELECTRICALCURRENT ================================================== */

    /** ElectricalCurrent.AMPERE unit type with code 0. */
    public static final Sim0MQDisplayType ELECTRICALCURRENT_AMPERE =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCURRENT, 0, ElectricalCurrentUnit.AMPERE, "AMPERE", "A");

    /** ElectricalCurrent.NANOAMPERE unit type with code 1. */
    public static final Sim0MQDisplayType ELECTRICALCURRENT_NANOAMPERE =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCURRENT, 1, ElectricalCurrentUnit.NANOAMPERE, "NANOAMPERE", "nA");

    /** ElectricalCurrent.MICROAMPERE unit type with code 2. */
    public static final Sim0MQDisplayType ELECTRICALCURRENT_MICROAMPERE =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCURRENT, 2, ElectricalCurrentUnit.MICROAMPERE, "MICROAMPERE", "μA");

    /** ElectricalCurrent.MILLIAMPERE unit type with code 3. */
    public static final Sim0MQDisplayType ELECTRICALCURRENT_MILLIAMPERE =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCURRENT, 3, ElectricalCurrentUnit.MILLIAMPERE, "MILLIAMPERE", "mA");

    /** ElectricalCurrent.KILOAMPERE unit type with code 4. */
    public static final Sim0MQDisplayType ELECTRICALCURRENT_KILOAMPERE =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCURRENT, 4, ElectricalCurrentUnit.KILOAMPERE, "KILOAMPERE", "kA");

    /** ElectricalCurrent.MEGAAMPERE unit type with code 5. */
    public static final Sim0MQDisplayType ELECTRICALCURRENT_MEGAAMPERE =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCURRENT, 5, ElectricalCurrentUnit.MEGAAMPERE, "MEGAAMPERE", "MA");

    /** ElectricalCurrent.ABAMPERE unit type with code 6. */
    public static final Sim0MQDisplayType ELECTRICALCURRENT_ABAMPERE =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCURRENT, 6, ElectricalCurrentUnit.ABAMPERE, "ABAMPERE", "abA");

    /** ElectricalCurrent.STATAMPERE unit type with code 7. */
    public static final Sim0MQDisplayType ELECTRICALCURRENT_STATAMPERE =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALCURRENT, 7, ElectricalCurrentUnit.STATAMPERE, "STATAMPERE", "statA");

    /* ============================================= ELECTRICALPOTENTIAL ================================================= */

    /** ElectricalPotential.VOLT unit type with code 0. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_VOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 0, ElectricalPotentialUnit.VOLT, "VOLT", "V");

    /** ElectricalPotential.NANOVOLT unit type with code 1. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_NANOVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 1, ElectricalPotentialUnit.NANOVOLT, "NANOVOLT", "nV");

    /** ElectricalPotential.MICROVOLT unit type with code 2. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_MICROVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 2, ElectricalPotentialUnit.MICROVOLT, "MICROVOLT", "μV");

    /** ElectricalPotential.MILLIVOLT unit type with code 3. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_MILLIVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 3, ElectricalPotentialUnit.MILLIVOLT, "MILLIVOLT", "mV");

    /** ElectricalPotential.KILOVOLT unit type with code 4. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_KILOVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 4, ElectricalPotentialUnit.KILOVOLT, "KILOVOLT", "kV");

    /** ElectricalPotential.MEGAVOLT unit type with code 5. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_MEGAVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 5, ElectricalPotentialUnit.MEGAVOLT, "MEGAVOLT", "MV");

    /** ElectricalPotential.GIGAVOLT unit type with code 6. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_GIGAVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 6, ElectricalPotentialUnit.GIGAVOLT, "GIGAVOLT", "GV");

    /** ElectricalPotential.ABVOLT unit type with code 7. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_ABVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 7, ElectricalPotentialUnit.ABVOLT, "ABVOLT", "abV");

    /** ElectricalPotential.STATVOLT unit type with code 8. */
    public static final Sim0MQDisplayType ELECTRICALPOTENTIAL_STATVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALPOTENTIAL, 8, ElectricalPotentialUnit.STATVOLT, "STATVOLT", "statV");

    /* ============================================ ELECTRICALRESISTANCE ================================================= */

    /** ElectricalResistance.OHM unit type with code 0. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_OHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 0, ElectricalResistanceUnit.OHM, "OHM", "Ω");

    /** ElectricalResistance.NANOOHM unit type with code 1. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_NANOOHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 1, ElectricalResistanceUnit.NANOOHM, "NANOOHM", "nΩ");

    /** ElectricalResistance.MICROOHM unit type with code 2. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_MICROOHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 2, ElectricalResistanceUnit.MICROOHM, "MICROOHM", "μΩ");

    /** ElectricalResistance.MILLIOHM unit type with code 3. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_MILLIOHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 3, ElectricalResistanceUnit.MILLIOHM, "MILLIOHM", "mΩ");

    /** ElectricalResistance.KILOOHM unit type with code 4. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_KILOOHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 4, ElectricalResistanceUnit.KILOOHM, "KILOOHM", "kΩ");

    /** ElectricalResistance.MEGAOHM unit type with code 5. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_MEGAOHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 5, ElectricalResistanceUnit.MEGAOHM, "MEGAOHM", "MΩ");

    /** ElectricalResistance.GIGAOHM unit type with code 6. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_GIGAOHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 6, ElectricalResistanceUnit.GIGAOHM, "GIGAOHM", "GΩ");

    /** ElectricalResistance.ABOHM unit type with code 7. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_ABOHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 7, ElectricalResistanceUnit.ABOHM, "ABOHM", "abΩ");

    /** ElectricalResistance.STATOHM unit type with code 8. */
    public static final Sim0MQDisplayType ELECTRICALRESISTANCE_STATOHM =
            new Sim0MQDisplayType(Sim0MQUnitType.ELECTRICALRESISTANCE, 8, ElectricalResistanceUnit.STATOHM, "STATOHM", "statΩ");

    /* ===================================================== ENERGY ====================================================== */

    /** Energy.JOULE unit type with code 0. */
    public static final Sim0MQDisplayType ENERGY_JOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 0, EnergyUnit.JOULE, "JOULE", "J");

    /** Energy.PICOJOULE unit type with code 1. */
    public static final Sim0MQDisplayType ENERGY_PICOJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 1, EnergyUnit.PICOJOULE, "PICOJOULE", "pJ");

    /** Energy.NANOJOULE unit type with code 2. */
    public static final Sim0MQDisplayType ENERGY_NANOJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 2, EnergyUnit.NANOJOULE, "NANOJOULE", "mJ");

    /** Energy.MICROJOULE unit type with code 3. */
    public static final Sim0MQDisplayType ENERGY_MICROJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 3, EnergyUnit.MICROJOULE, "MICROJOULE", "μJ");

    /** Energy.MILLIJOULE unit type with code 4. */
    public static final Sim0MQDisplayType ENERGY_MILLIJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 4, EnergyUnit.MILLIJOULE, "MILLIJOULE", "mJ");

    /** Energy.KILOJOULE unit type with code 5. */
    public static final Sim0MQDisplayType ENERGY_KILOJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 5, EnergyUnit.KILOJOULE, "KILOJOULE", "kJ");

    /** Energy.MEGAJOULE unit type with code 6. */
    public static final Sim0MQDisplayType ENERGY_MEGAJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 6, EnergyUnit.MEGAJOULE, "MEGAJOULE", "MJ");

    /** Energy.GIGAJOULE unit type with code 7. */
    public static final Sim0MQDisplayType ENERGY_GIGAJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 7, EnergyUnit.GIGAJOULE, "GIGAJOULE", "GJ");

    /** Energy.TERAJOULE unit type with code 8. */
    public static final Sim0MQDisplayType ENERGY_TERAJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 8, EnergyUnit.TERAJOULE, "TERAJOULE", "TJ");

    /** Energy.PETAJOULE unit type with code 9. */
    public static final Sim0MQDisplayType ENERGY_PETAJOULE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 9, EnergyUnit.PETAJOULE, "PETAJOULE", "PJ");

    /** Energy.ELECTRONVOLT unit type with code 10. */
    public static final Sim0MQDisplayType ENERGY_ELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 10, EnergyUnit.ELECTRONVOLT, "ELECTRONVOLT", "eV");

    /** Energy.MICROELECTRONVOLT unit type with code 11. */
    public static final Sim0MQDisplayType ENERGY_MICROELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 11, EnergyUnit.MICROELECTRONVOLT, "MICROELECTRONVOLT", "μeV");

    /** Energy.MILLIELECTRONVOLT unit type with code 12. */
    public static final Sim0MQDisplayType ENERGY_MILLIELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 12, EnergyUnit.MILLIELECTRONVOLT, "MILLIELECTRONVOLT", "meV");

    /** Energy.KILOELECTRONVOLT unit type with code 13. */
    public static final Sim0MQDisplayType ENERGY_KILOELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 13, EnergyUnit.KILOELECTRONVOLT, "KILOELECTRONVOLT", "keV");

    /** Energy.MEGAELECTRONVOLT unit type with code 14. */
    public static final Sim0MQDisplayType ENERGY_MEGAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 14, EnergyUnit.MEGAELECTRONVOLT, "MEGAELECTRONVOLT", "MeV");

    /** Energy.GIGAELECTRONVOLT unit type with code 15. */
    public static final Sim0MQDisplayType ENERGY_GIGAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 15, EnergyUnit.GIGAELECTRONVOLT, "GIGAELECTRONVOLT", "GeV");

    /** Energy.TERAELECTRONVOLT unit type with code 16. */
    public static final Sim0MQDisplayType ENERGY_TERAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 16, EnergyUnit.TERAELECTRONVOLT, "TERAELECTRONVOLT", "TeV");

    /** Energy.PETAELECTRONVOLT unit type with code 17. */
    public static final Sim0MQDisplayType ENERGY_PETAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 17, EnergyUnit.PETAELECTRONVOLT, "PETAELECTRONVOLT", "PeV");

    /** Energy.EXAELECTRONVOLT unit type with code 18. */
    public static final Sim0MQDisplayType ENERGY_EXAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 18, EnergyUnit.EXAELECTRONVOLT, "EXAELECTRONVOLT", "EeV");

    /** Energy.WATT_HOUR unit type with code 19. */
    public static final Sim0MQDisplayType ENERGY_WATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 19, EnergyUnit.WATT_HOUR, "WATT_HOUR", "Wh");

    /** Energy.FEMTOWATT_HOUR unit type with code 20. */
    public static final Sim0MQDisplayType ENERGY_FEMTOWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 20, EnergyUnit.FEMTOWATT_HOUR, "FEMTOWATT_HOUR", "fWh");

    /** Energy.PICOWATT_HOUR unit type with code 21. */
    public static final Sim0MQDisplayType ENERGY_PICOWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 21, EnergyUnit.PICOWATT_HOUR, "PICOWATT_HOUR", "pWh");

    /** Energy.NANOWATT_HOUR unit type with code 22. */
    public static final Sim0MQDisplayType ENERGY_NANOWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 22, EnergyUnit.NANOWATT_HOUR, "NANOWATT_HOUR", "mWh");

    /** Energy.MICROWATT_HOUR unit type with code 23. */
    public static final Sim0MQDisplayType ENERGY_MICROWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 23, EnergyUnit.MICROWATT_HOUR, "MICROWATT_HOUR", "μWh");

    /** Energy.MILLIWATT_HOUR unit type with code 24. */
    public static final Sim0MQDisplayType ENERGY_MILLIWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 24, EnergyUnit.MILLIWATT_HOUR, "MILLIWATT_HOUR", "mWh");

    /** Energy.KILOWATT_HOUR unit type with code 25. */
    public static final Sim0MQDisplayType ENERGY_KILOWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 25, EnergyUnit.KILOWATT_HOUR, "KILOWATT_HOUR", "kWh");

    /** Energy.MEGAWATT_HOUR unit type with code 26. */
    public static final Sim0MQDisplayType ENERGY_MEGAWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 26, EnergyUnit.MEGAWATT_HOUR, "MEGAWATT_HOUR", "MWh");

    /** Energy.GIGAWATT_HOUR unit type with code 27. */
    public static final Sim0MQDisplayType ENERGY_GIGAWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 27, EnergyUnit.GIGAWATT_HOUR, "GIGAWATT_HOUR", "GWh");

    /** Energy.TERAWATT_HOUR unit type with code 28. */
    public static final Sim0MQDisplayType ENERGY_TERAWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 28, EnergyUnit.TERAWATT_HOUR, "TERAWATT_HOUR", "TWh");

    /** Energy.PETAWATT_HOUR unit type with code 29. */
    public static final Sim0MQDisplayType ENERGY_PETAWATT_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 29, EnergyUnit.PETAWATT_HOUR, "PETAWATT_HOUR", "PWh");

    /** Energy.CALORIE unit type with code 30. */
    public static final Sim0MQDisplayType ENERGY_CALORIE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 30, EnergyUnit.CALORIE, "CALORIE", "cal");

    /** Energy.KILOCALORIE unit type with code 31. */
    public static final Sim0MQDisplayType ENERGY_KILOCALORIE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 31, EnergyUnit.KILOCALORIE, "KILOCALORIE", "kcal");

    /** Energy.CALORIE_IT unit type with code 32. */
    public static final Sim0MQDisplayType ENERGY_CALORIE_IT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 32, EnergyUnit.CALORIE_IT, "CALORIE_IT", "cal(IT)");

    /** Energy.INCH_POUND_FORCE unit type with code 33. */
    public static final Sim0MQDisplayType ENERGY_INCH_POUND_FORCE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 33, EnergyUnit.INCH_POUND_FORCE, "INCH_POUND_FORCE", "in lbf");

    /** Energy.FOOT_POUND_FORCE unit type with code 34. */
    public static final Sim0MQDisplayType ENERGY_FOOT_POUND_FORCE =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 34, EnergyUnit.FOOT_POUND_FORCE, "FOOT_POUND_FORCE", "ft lbf");

    /** Energy.ERG unit type with code 35. */
    public static final Sim0MQDisplayType ENERGY_ERG =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 35, EnergyUnit.ERG, "ERG", "erg");

    /** Energy.BTU_ISO unit type with code 36. */
    public static final Sim0MQDisplayType ENERGY_BTU_ISO =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 36, EnergyUnit.BTU_ISO, "BTU_ISO", "BTU(ISO)");

    /** Energy.BTU_IT unit type with code 37. */
    public static final Sim0MQDisplayType ENERGY_BTU_IT =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 37, EnergyUnit.BTU_IT, "BTU_IT", "BTU(IT)");

    /** Energy.STHENE_METER unit type with code 38. */
    public static final Sim0MQDisplayType ENERGY_STHENE_METER =
            new Sim0MQDisplayType(Sim0MQUnitType.ENERGY, 38, EnergyUnit.STHENE_METER, "STHENE_METER", "sth.m");

    /* ==================================================== FLOWMASS ===================================================== */

    /** FlowMass.KG_PER_SECOND unit type with code 0. */
    public static final Sim0MQDisplayType FLOWMASS_KG_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWMASS, 0, FlowMassUnit.KILOGRAM_PER_SECOND, "KG_PER_SECOND", "kg/s");

    /** FlowMass.POUND_PER_SECOND unit type with code 1. */
    public static final Sim0MQDisplayType FLOWMASS_POUND_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWMASS, 1, FlowMassUnit.POUND_PER_SECOND, "POUND_PER_SECOND", "lb/s");

    /* =================================================== FLOWVOLUME ==================================================== */

    /** FlowVolume.CUBIC_METER_PER_SECOND unit type with code 0. */
    public static final Sim0MQDisplayType FLOWVOLUME_CUBIC_METER_PER_SECOND = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME,
            0, FlowVolumeUnit.CUBIC_METER_PER_SECOND, "CUBIC_METER_PER_SECOND", "m3/s");

    /** FlowVolume.CUBIC_METER_PER_MINUTE unit type with code 1. */
    public static final Sim0MQDisplayType FLOWVOLUME_CUBIC_METER_PER_MINUTE = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME,
            1, FlowVolumeUnit.CUBIC_METER_PER_MINUTE, "CUBIC_METER_PER_MINUTE", "m3/min");

    /** FlowVolume.CUBIC_METER_PER_HOUR unit type with code 2. */
    public static final Sim0MQDisplayType FLOWVOLUME_CUBIC_METER_PER_HOUR = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 2,
            FlowVolumeUnit.CUBIC_METER_PER_HOUR, "CUBIC_METER_PER_HOUR", "m3/h");

    /** FlowVolume.CUBIC_METER_PER_DAY unit type with code 3. */
    public static final Sim0MQDisplayType FLOWVOLUME_CUBIC_METER_PER_DAY = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 3,
            FlowVolumeUnit.CUBIC_METER_PER_DAY, "CUBIC_METER_PER_DAY", "m3/day");

    /** FlowVolume.CUBIC_INCH_PER_SECOND unit type with code 4. */
    public static final Sim0MQDisplayType FLOWVOLUME_CUBIC_INCH_PER_SECOND = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 4,
            FlowVolumeUnit.CUBIC_INCH_PER_SECOND, "CUBIC_INCH_PER_SECOND", "in3/s");

    /** FlowVolume.CUBIC_INCH_PER_MINUTE unit type with code 5. */
    public static final Sim0MQDisplayType FLOWVOLUME_CUBIC_INCH_PER_MINUTE = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 5,
            FlowVolumeUnit.CUBIC_INCH_PER_MINUTE, "CUBIC_INCH_PER_MINUTE", "in3/min");

    /** FlowVolume.CUBIC_FEET_PER_SECOND unit type with code 6. */
    public static final Sim0MQDisplayType FLOWVOLUME_CUBIC_FEET_PER_SECOND = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 6,
            FlowVolumeUnit.CUBIC_FEET_PER_SECOND, "CUBIC_FEET_PER_SECOND", "ft3/s");

    /** FlowVolume.CUBIC_FEET_PER_MINUTE unit type with code 7. */
    public static final Sim0MQDisplayType FLOWVOLUME_CUBIC_FEET_PER_MINUTE = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 7,
            FlowVolumeUnit.CUBIC_FEET_PER_MINUTE, "CUBIC_FEET_PER_MINUTE", "ft3/min");

    /** FlowVolume.GALLON_PER_SECOND unit type with code 8. */
    public static final Sim0MQDisplayType FLOWVOLUME_GALLON_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 8, FlowVolumeUnit.GALLON_PER_SECOND, "GALLON_PER_SECOND", "gal/s");

    /** FlowVolume.GALLON_PER_MINUTE unit type with code 9. */
    public static final Sim0MQDisplayType FLOWVOLUME_GALLON_PER_MINUTE = new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 9,
            FlowVolumeUnit.GALLON_PER_MINUTE, "GALLON_PER_MINUTE", "gal/min");

    /** FlowVolume.GALLON_PER_HOUR unit type with code 10. */
    public static final Sim0MQDisplayType FLOWVOLUME_GALLON_PER_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 10, FlowVolumeUnit.GALLON_PER_HOUR, "GALLON_PER_HOUR", "gal/h");

    /** FlowVolume.GALLON_PER_DAY unit type with code 11. */
    public static final Sim0MQDisplayType FLOWVOLUME_GALLON_PER_DAY =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 11, FlowVolumeUnit.GALLON_PER_DAY, "GALLON_PER_DAY", "gal/day");

    /** FlowVolume.LITER_PER_SECOND unit type with code 12. */
    public static final Sim0MQDisplayType FLOWVOLUME_LITER_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 12, FlowVolumeUnit.LITER_PER_SECOND, "LITER_PER_SECOND", "l/s");

    /** FlowVolume.LITER_PER_MINUTE unit type with code 13. */
    public static final Sim0MQDisplayType FLOWVOLUME_LITER_PER_MINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 13, FlowVolumeUnit.LITER_PER_MINUTE, "LITER_PER_MINUTE", "l/min");

    /** FlowVolume.LITER_PER_HOUR unit type with code 14. */
    public static final Sim0MQDisplayType FLOWVOLUME_LITER_PER_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 14, FlowVolumeUnit.LITER_PER_HOUR, "LITER_PER_HOUR", "l/h");

    /** FlowVolume.LITER_PER_DAY unit type with code 15. */
    public static final Sim0MQDisplayType FLOWVOLUME_LITER_PER_DAY =
            new Sim0MQDisplayType(Sim0MQUnitType.FLOWVOLUME, 15, FlowVolumeUnit.LITER_PER_DAY, "LITER_PER_DAY", "l/day");

    /* ===================================================== FORCE ======================================================= */

    /** Force.NEWTON unit type with code 0. */
    public static final Sim0MQDisplayType FORCE_NEWTON =
            new Sim0MQDisplayType(Sim0MQUnitType.FORCE, 0, ForceUnit.NEWTON, "NEWTON", "N");

    /** Force.KILOGRAM_FORCE unit type with code 1. */
    public static final Sim0MQDisplayType FORCE_KILOGRAM_FORCE =
            new Sim0MQDisplayType(Sim0MQUnitType.FORCE, 1, ForceUnit.KILOGRAM_FORCE, "KILOGRAM_FORCE", "kgf");

    /** Force.OUNCE_FORCE unit type with code 2. */
    public static final Sim0MQDisplayType FORCE_OUNCE_FORCE =
            new Sim0MQDisplayType(Sim0MQUnitType.FORCE, 2, ForceUnit.OUNCE_FORCE, "OUNCE_FORCE", "ozf");

    /** Force.POUND_FORCE unit type with code 3. */
    public static final Sim0MQDisplayType FORCE_POUND_FORCE =
            new Sim0MQDisplayType(Sim0MQUnitType.FORCE, 3, ForceUnit.POUND_FORCE, "POUND_FORCE", "lbf");

    /** Force.TON_FORCE unit type with code 4. */
    public static final Sim0MQDisplayType FORCE_TON_FORCE =
            new Sim0MQDisplayType(Sim0MQUnitType.FORCE, 4, ForceUnit.TON_FORCE, "TON_FORCE", "tnf");

    /** Force.DYNE unit type with code 5. */
    public static final Sim0MQDisplayType FORCE_DYNE =
            new Sim0MQDisplayType(Sim0MQUnitType.FORCE, 5, ForceUnit.DYNE, "DYNE", "dyne");

    /** Force.STHENE unit type with code 6. */
    public static final Sim0MQDisplayType FORCE_STHENE =
            new Sim0MQDisplayType(Sim0MQUnitType.FORCE, 6, ForceUnit.STHENE, "STHENE", "sth");

    /* =================================================== FREQUENCY ===================================================== */

    /** Frequency.HERTZ unit type with code 0. */
    public static final Sim0MQDisplayType FREQUENCY_HERTZ =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 0, FrequencyUnit.HERTZ, "HERTZ", "Hz");

    /** Frequency.KILOHERTZ unit type with code 1. */
    public static final Sim0MQDisplayType FREQUENCY_KILOHERTZ =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 1, FrequencyUnit.KILOHERTZ, "KILOHERTZ", "kHz");

    /** Frequency.MEGAHERTZ unit type with code 2. */
    public static final Sim0MQDisplayType FREQUENCY_MEGAHERTZ =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 2, FrequencyUnit.MEGAHERTZ, "MEGAHERTZ", "MHz");

    /** Frequency.GIGAHERTZ unit type with code 3. */
    public static final Sim0MQDisplayType FREQUENCY_GIGAHERTZ =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 3, FrequencyUnit.GIGAHERTZ, "GIGAHERTZ", "GHz");

    /** Frequency.TERAHERTZ unit type with code 4. */
    public static final Sim0MQDisplayType FREQUENCY_TERAHERTZ =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 4, FrequencyUnit.TERAHERTZ, "TERAHERTZ", "THz");

    /** Frequency.PER_SECOND unit type with code 5. */
    public static final Sim0MQDisplayType FREQUENCY_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 5, FrequencyUnit.PER_SECOND, "PER_SECOND", "1/s");

    /** Frequency.PER_ATTOSECOND unit type with code 6. */
    public static final Sim0MQDisplayType FREQUENCY_PER_ATTOSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 6, FrequencyUnit.PER_ATTOSECOND, "PER_ATTOSECOND", "1/as");

    /** Frequency.PER_FEMTOSECOND unit type with code 7. */
    public static final Sim0MQDisplayType FREQUENCY_PER_FEMTOSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 7, FrequencyUnit.PER_FEMTOSECOND, "PER_FEMTOSECOND", "1/fs");

    /** Frequency.PER_PICOSECOND unit type with code 8. */
    public static final Sim0MQDisplayType FREQUENCY_PER_PICOSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 8, FrequencyUnit.PER_PICOSECOND, "PER_PICOSECOND", "1/ps");

    /** Frequency.PER_NANOSECOND unit type with code 9. */
    public static final Sim0MQDisplayType FREQUENCY_PER_NANOSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 9, FrequencyUnit.PER_NANOSECOND, "PER_NANOSECOND", "1/ns");

    /** Frequency.PER_MICROSECOND unit type with code 10. */
    public static final Sim0MQDisplayType FREQUENCY_PER_MICROSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 10, FrequencyUnit.PER_MICROSECOND, "PER_MICROSECOND", "1/μs");

    /** Frequency.PER_MILLISECOND unit type with code 11. */
    public static final Sim0MQDisplayType FREQUENCY_PER_MILLISECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 11, FrequencyUnit.PER_MILLISECOND, "PER_MILLISECOND", "1/ms");

    /** Frequency.PER_MINUTE unit type with code 12. */
    public static final Sim0MQDisplayType FREQUENCY_PER_MINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 12, FrequencyUnit.PER_MINUTE, "PER_MINUTE", "1/min");

    /** Frequency.PER_HOUR unit type with code 13. */
    public static final Sim0MQDisplayType FREQUENCY_PER_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 13, FrequencyUnit.PER_HOUR, "PER_HOUR", "1/hr");

    /** Frequency.PER_DAY unit type with code 14. */
    public static final Sim0MQDisplayType FREQUENCY_PER_DAY =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 14, FrequencyUnit.PER_DAY, "PER_DAY", "1/day");

    /** Frequency.PER_WEEK unit type with code 15. */
    public static final Sim0MQDisplayType FREQUENCY_PER_WEEK =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 15, FrequencyUnit.PER_WEEK, "PER_WEEK", "1/wk");

    /** Frequency.RPM unit type with code 16. */
    public static final Sim0MQDisplayType FREQUENCY_RPM =
            new Sim0MQDisplayType(Sim0MQUnitType.FREQUENCY, 16, FrequencyUnit.RPM, "RPM", "rpm");

    /* ===================================================== LENGTH ====================================================== */

    /** Length.METER unit type with code 0. */
    public static final Sim0MQDisplayType LENGTH_METER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 0, LengthUnit.METER, "METER", "m");

    /** Length.ATTOMETER unit type with code 1. */
    public static final Sim0MQDisplayType LENGTH_ATTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 1, LengthUnit.ATTOMETER, "ATTOMETER", "am");

    /** Length.FEMTOMETER unit type with code 2. */
    public static final Sim0MQDisplayType LENGTH_FEMTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 2, LengthUnit.FEMTOMETER, "FEMTOMETER", "fm");

    /** Length.PICOMETER unit type with code 3. */
    public static final Sim0MQDisplayType LENGTH_PICOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 3, LengthUnit.PICOMETER, "PICOMETER", "pm");

    /** Length.NANOMETER unit type with code 4. */
    public static final Sim0MQDisplayType LENGTH_NANOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 4, LengthUnit.NANOMETER, "NANOMETER", "nm");

    /** Length.MICROMETER unit type with code 5. */
    public static final Sim0MQDisplayType LENGTH_MICROMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 5, LengthUnit.MICROMETER, "MICROMETER", "μm");

    /** Length.MILLIMETER unit type with code 6. */
    public static final Sim0MQDisplayType LENGTH_MILLIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 6, LengthUnit.MILLIMETER, "MILLIMETER", "mm");

    /** Length.CENTIMETER unit type with code 7. */
    public static final Sim0MQDisplayType LENGTH_CENTIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 7, LengthUnit.CENTIMETER, "CENTIMETER", "cm");

    /** Length.DECIMETER unit type with code 8. */
    public static final Sim0MQDisplayType LENGTH_DECIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 8, LengthUnit.DECIMETER, "DECIMETER", "dm");

    /** Length.DEKAMETER unit type with code 9. */
    public static final Sim0MQDisplayType LENGTH_DEKAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 9, LengthUnit.DEKAMETER, "DEKAMETER", "dam");

    /** Length.HECTOMETER unit type with code 10. */
    public static final Sim0MQDisplayType LENGTH_HECTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 10, LengthUnit.HECTOMETER, "HECTOMETER", "hm");

    /** Length.KILOMETER unit type with code 11. */
    public static final Sim0MQDisplayType LENGTH_KILOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 11, LengthUnit.KILOMETER, "KILOMETER", "km");

    /** Length.MEGAMETER unit type with code 12. */
    public static final Sim0MQDisplayType LENGTH_MEGAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 12, LengthUnit.MEGAMETER, "MEGAMETER", "Mm");

    /** Length.INCH unit type with code 13. */
    public static final Sim0MQDisplayType LENGTH_INCH =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 13, LengthUnit.INCH, "INCH", "in");

    /** Length.FOOT unit type with code 14. */
    public static final Sim0MQDisplayType LENGTH_FOOT =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 14, LengthUnit.FOOT, "FOOT", "ft");

    /** Length.YARD unit type with code 15. */
    public static final Sim0MQDisplayType LENGTH_YARD =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 15, LengthUnit.YARD, "YARD", "yd");

    /** Length.MILE unit type with code 16. */
    public static final Sim0MQDisplayType LENGTH_MILE =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 16, LengthUnit.MILE, "MILE", "mi");

    /** Length.NAUTICAL_MILE unit type with code 17. */
    public static final Sim0MQDisplayType LENGTH_NAUTICAL_MILE =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 17, LengthUnit.NAUTICAL_MILE, "NAUTICAL_MILE", "NM");

    /** Length.ASTRONOMICAL_UNIT unit type with code 18. */
    public static final Sim0MQDisplayType LENGTH_ASTRONOMICAL_UNIT =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 18, LengthUnit.ASTRONOMICAL_UNIT, "ASTRONOMICAL_UNIT", "au");

    /** Length.PARSEC unit type with code 19. */
    public static final Sim0MQDisplayType LENGTH_PARSEC =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 19, LengthUnit.PARSEC, "PARSEC", "pc");

    /** Length.LIGHTYEAR unit type with code 20. */
    public static final Sim0MQDisplayType LENGTH_LIGHTYEAR =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 20, LengthUnit.LIGHTYEAR, "LIGHTYEAR", "ly");

    /** Length.ANGSTROM unit type with code 21. */
    public static final Sim0MQDisplayType LENGTH_ANGSTROM =
            new Sim0MQDisplayType(Sim0MQUnitType.LENGTH, 21, LengthUnit.ANGSTROM, "ANGSTROM", "Å");

    /* ==================================================== POSITION ===================================================== */

    /** Position.METER unit type with code 0. */
    public static final Sim0MQDisplayType POSITION_METER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 0, PositionUnit.METER, "METER", "m");

    /** Position.ATTOMETER unit type with code 1. */
    public static final Sim0MQDisplayType POSITION_ATTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 1, PositionUnit.ATTOMETER, "ATTOMETER", "am");

    /** Position.FEMTOMETER unit type with code 2. */
    public static final Sim0MQDisplayType POSITION_FEMTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 2, PositionUnit.FEMTOMETER, "FEMTOMETER", "fm");

    /** Position.PICOMETER unit type with code 3. */
    public static final Sim0MQDisplayType POSITION_PICOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 3, PositionUnit.PICOMETER, "PICOMETER", "pm");

    /** Position.NANOMETER unit type with code 4. */
    public static final Sim0MQDisplayType POSITION_NANOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 4, PositionUnit.NANOMETER, "NANOMETER", "nm");

    /** Position.MICROMETER unit type with code 5. */
    public static final Sim0MQDisplayType POSITION_MICROMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 5, PositionUnit.MICROMETER, "MICROMETER", "μm");

    /** Position.MILLIMETER unit type with code 6. */
    public static final Sim0MQDisplayType POSITION_MILLIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 6, PositionUnit.MILLIMETER, "MILLIMETER", "mm");

    /** Position.CENTIMETER unit type with code 7. */
    public static final Sim0MQDisplayType POSITION_CENTIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 7, PositionUnit.CENTIMETER, "CENTIMETER", "cm");

    /** Position.DECIMETER unit type with code 8. */
    public static final Sim0MQDisplayType POSITION_DECIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 8, PositionUnit.DECIMETER, "DECIMETER", "dm");

    /** Position.DEKAMETER unit type with code 9. */
    public static final Sim0MQDisplayType POSITION_DEKAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 9, PositionUnit.DEKAMETER, "DEKAMETER", "dam");

    /** Position.HECTOMETER unit type with code 10. */
    public static final Sim0MQDisplayType POSITION_HECTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 10, PositionUnit.HECTOMETER, "HECTOMETER", "hm");

    /** Position.KILOMETER unit type with code 11. */
    public static final Sim0MQDisplayType POSITION_KILOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 11, PositionUnit.KILOMETER, "KILOMETER", "km");

    /** Position.MEGAMETER unit type with code 12. */
    public static final Sim0MQDisplayType POSITION_MEGAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 12, PositionUnit.MEGAMETER, "MEGAMETER", "Mm");

    /** Position.INCH unit type with code 13. */
    public static final Sim0MQDisplayType POSITION_INCH =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 13, PositionUnit.INCH, "INCH", "in");

    /** Position.FOOT unit type with code 14. */
    public static final Sim0MQDisplayType POSITION_FOOT =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 14, PositionUnit.FOOT, "FOOT", "ft");

    /** Position.YARD unit type with code 15. */
    public static final Sim0MQDisplayType POSITION_YARD =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 15, PositionUnit.YARD, "YARD", "yd");

    /** Position.MILE unit type with code 16. */
    public static final Sim0MQDisplayType POSITION_MILE =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 16, PositionUnit.MILE, "MILE", "mi");

    /** Position.NAUTICAL_MILE unit type with code 17. */
    public static final Sim0MQDisplayType POSITION_NAUTICAL_MILE =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 17, PositionUnit.NAUTICAL_MILE, "NAUTICAL_MILE", "NM");

    /** Position.ASTRONOMICAL_UNIT unit type with code 18. */
    public static final Sim0MQDisplayType POSITION_ASTRONOMICAL_UNIT =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 18, PositionUnit.ASTRONOMICAL_UNIT, "ASTRONOMICAL_UNIT", "au");

    /** Position.PARSEC unit type with code 19. */
    public static final Sim0MQDisplayType POSITION_PARSEC =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 19, PositionUnit.PARSEC, "PARSEC", "pc");

    /** Position.LIGHTYEAR unit type with code 20. */
    public static final Sim0MQDisplayType POSITION_LIGHTYEAR =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 20, PositionUnit.LIGHTYEAR, "LIGHTYEAR", "ly");

    /** Position.ANGSTROM unit type with code 21. */
    public static final Sim0MQDisplayType POSITION_ANGSTROM =
            new Sim0MQDisplayType(Sim0MQUnitType.POSITION, 21, PositionUnit.ANGSTROM, "ANGSTROM", "Å");

    /* ================================================== LINEARDENSITY ================================================== */

    /** LinearDensity.PER_METER unit type with code 0. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_METER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 0, LinearDensityUnit.PER_METER, "PER_METER", "1/m");

    /** LinearDensity.PER_ATTOMETER unit type with code 1. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_ATTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 1, LinearDensityUnit.PER_ATTOMETER, "PER_ATTOMETER", "1/am");

    /** LinearDensity.PER_FEMTOMETER unit type with code 2. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_FEMTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 2, LinearDensityUnit.PER_FEMTOMETER, "PER_FEMTOMETER", "1/fm");

    /** LinearDensity.PER_PICOMETER unit type with code 3. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_PICOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 3, LinearDensityUnit.PER_PICOMETER, "PER_PICOMETER", "1/pm");

    /** LinearDensity.PER_NANOMETER unit type with code 4. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_NANOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 4, LinearDensityUnit.PER_NANOMETER, "PER_NANOMETER", "1/nm");

    /** LinearDensity.PER_MICROMETER unit type with code 5. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_MICROMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 5, LinearDensityUnit.PER_MICROMETER, "PER_MICROMETER", "1/μm");

    /** LinearDensity.PER_MILLIMETER unit type with code 6. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_MILLIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 6, LinearDensityUnit.PER_MILLIMETER, "PER_MILLIMETER", "1/mm");

    /** LinearDensity.PER_CENTIMETER unit type with code 7. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_CENTIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 7, LinearDensityUnit.PER_CENTIMETER, "PER_CENTIMETER", "1/cm");

    /** LinearDensity.PER_DECIMETER unit type with code 8. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_DECIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 8, LinearDensityUnit.PER_DECIMETER, "PER_DECIMETER", "1/dm");

    /** LinearDensity.PER_DEKAMETER unit type with code 9. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_DEKAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 9, LinearDensityUnit.PER_DEKAMETER, "PER_DEKAMETER", "1/dam");

    /** LinearDensity.PER_HECTOMETER unit type with code 10. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_HECTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 10, LinearDensityUnit.PER_HECTOMETER, "PER_HECTOMETER", "1/hm");

    /** LinearDensity.PER_KILOMETER unit type with code 11. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_KILOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 11, LinearDensityUnit.PER_KILOMETER, "PER_KILOMETER", "1/km");

    /** LinearDensity.PER_MEGAMETER unit type with code 12. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_MEGAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 12, LinearDensityUnit.PER_MEGAMETER, "PER_MEGAMETER", "1/Mm");

    /** LinearDensity.PER_INCH unit type with code 13. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_INCH =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 13, LinearDensityUnit.PER_INCH, "PER_INCH", "1/in");

    /** LinearDensity.PER_FOOT unit type with code 14. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_FOOT =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 14, LinearDensityUnit.PER_FOOT, "PER_FOOT", "1/ft");

    /** LinearDensity.PER_YARD unit type with code 15. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_YARD =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 15, LinearDensityUnit.PER_YARD, "PER_YARD", "1/yd");

    /** LinearDensity.PER_MILE unit type with code 16. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_MILE =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 16, LinearDensityUnit.PER_MILE, "PER_MILE", "1/mi");

    /** LinearDensity.PER_NAUTICAL_MILE unit type with code 17. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_NAUTICAL_MILE = new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY,
            17, LinearDensityUnit.PER_NAUTICAL_MILE, "PER_NAUTICAL_MILE", "1/NM");

    /** LinearDensity.PER_ASTRONOMICAL_UNIT unit type with code 18. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_ASTRONOMICAL_UNIT = new Sim0MQDisplayType(
            Sim0MQUnitType.LINEARDENSITY, 18, LinearDensityUnit.PER_ASTRONOMICAL_UNIT, "PER_ASTRONOMICAL_UNIT", "1/au");

    /** LinearDensity.PER_PARSEC unit type with code 19. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_PARSEC =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 19, LinearDensityUnit.PER_PARSEC, "PER_PARSEC", "1/pc");

    /** LinearDensity.PER_LIGHTYEAR unit type with code 20. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_LIGHTYEAR =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 20, LinearDensityUnit.PER_LIGHTYEAR, "PER_LIGHTYEAR", "1/ly");

    /** LinearDensity.PER_ANGSTROM unit type with code 21. */
    public static final Sim0MQDisplayType LINEARDENSITY_PER_ANGSTROM =
            new Sim0MQDisplayType(Sim0MQUnitType.LINEARDENSITY, 21, LinearDensityUnit.PER_ANGSTROM, "PER_ANGSTROM", "1/Å");

    /* ====================================================== MASS ======================================================= */

    /** Mass.KILOGRAM unit type with code 0. */
    public static final Sim0MQDisplayType MASS_KILOGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 0, MassUnit.KILOGRAM, "KILOGRAM", "kg");

    /** Mass.FEMTOGRAM unit type with code 1. */
    public static final Sim0MQDisplayType MASS_FEMTOGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 1, MassUnit.FEMTOGRAM, "FEMTOGRAM", "fg");

    /** Mass.PICOGRAM unit type with code 2. */
    public static final Sim0MQDisplayType MASS_PICOGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 2, MassUnit.PICOGRAM, "PICOGRAM", "pg");

    /** Mass.NANOGRAM unit type with code 3. */
    public static final Sim0MQDisplayType MASS_NANOGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 3, MassUnit.NANOGRAM, "NANOGRAM", "mg");

    /** Mass.MICROGRAM unit type with code 4. */
    public static final Sim0MQDisplayType MASS_MICROGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 4, MassUnit.MICROGRAM, "MICROGRAM", "μg");

    /** Mass.MILLIGRAM unit type with code 5. */
    public static final Sim0MQDisplayType MASS_MILLIGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 5, MassUnit.MILLIGRAM, "MILLIGRAM", "mg");

    /** Mass.GRAM unit type with code 6. */
    public static final Sim0MQDisplayType MASS_GRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 6, MassUnit.GRAM, "GRAM", "kg");

    /** Mass.MEGAGRAM unit type with code 7. */
    public static final Sim0MQDisplayType MASS_MEGAGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 7, MassUnit.MEGAGRAM, "MEGAGRAM", "Mg");

    /** Mass.GIGAGRAM unit type with code 8. */
    public static final Sim0MQDisplayType MASS_GIGAGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 8, MassUnit.GIGAGRAM, "GIGAGRAM", "Gg");

    /** Mass.TERAGRAM unit type with code 9. */
    public static final Sim0MQDisplayType MASS_TERAGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 9, MassUnit.TERAGRAM, "TERAGRAM", "Tg");

    /** Mass.PETAGRAM unit type with code 10. */
    public static final Sim0MQDisplayType MASS_PETAGRAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 10, MassUnit.PETAGRAM, "PETAGRAM", "Pg");

    /** Mass.MICROELECTRONVOLT unit type with code 11. */
    public static final Sim0MQDisplayType MASS_MICROELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 11, MassUnit.MICROELECTRONVOLT, "MICROELECTRONVOLT", "μeV");

    /** Mass.MILLIELECTRONVOLT unit type with code 12. */
    public static final Sim0MQDisplayType MASS_MILLIELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 12, MassUnit.MILLIELECTRONVOLT, "MILLIELECTRONVOLT", "meV");

    /** Mass.ELECTRONVOLT unit type with code 13. */
    public static final Sim0MQDisplayType MASS_ELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 13, MassUnit.ELECTRONVOLT, "ELECTRONVOLT", "eV");

    /** Mass.KILOELECTRONVOLT unit type with code 14. */
    public static final Sim0MQDisplayType MASS_KILOELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 14, MassUnit.KILOELECTRONVOLT, "KILOELECTRONVOLT", "keV");

    /** Mass.MEGAELECTRONVOLT unit type with code 15. */
    public static final Sim0MQDisplayType MASS_MEGAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 15, MassUnit.MEGAELECTRONVOLT, "MEGAELECTRONVOLT", "MeV");

    /** Mass.GIGAELECTRONVOLT unit type with code 16. */
    public static final Sim0MQDisplayType MASS_GIGAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 16, MassUnit.GIGAELECTRONVOLT, "GIGAELECTRONVOLT", "GeV");

    /** Mass.TERAELECTRONVOLT unit type with code 17. */
    public static final Sim0MQDisplayType MASS_TERAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 17, MassUnit.TERAELECTRONVOLT, "TERAELECTRONVOLT", "TeV");

    /** Mass.PETAELECTRONVOLT unit type with code 18. */
    public static final Sim0MQDisplayType MASS_PETAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 18, MassUnit.PETAELECTRONVOLT, "PETAELECTRONVOLT", "PeV");

    /** Mass.EXAELECTRONVOLT unit type with code 19. */
    public static final Sim0MQDisplayType MASS_EXAELECTRONVOLT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 19, MassUnit.EXAELECTRONVOLT, "EXAELECTRONVOLT", "EeV");

    /** Mass.OUNCE unit type with code 20. */
    public static final Sim0MQDisplayType MASS_OUNCE =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 20, MassUnit.OUNCE, "OUNCE", "oz");

    /** Mass.POUND unit type with code 21. */
    public static final Sim0MQDisplayType MASS_POUND =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 21, MassUnit.POUND, "POUND", "lb");

    /** Mass.DALTON unit type with code 22. */
    public static final Sim0MQDisplayType MASS_DALTON =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 22, MassUnit.DALTON, "DALTON", "Da");

    /** Mass.TON_LONG unit type with code 23. */
    public static final Sim0MQDisplayType MASS_TON_LONG =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 23, MassUnit.TON_LONG, "TON_LONG", "ton (long)");

    /** Mass.TON_SHORT unit type with code 24. */
    public static final Sim0MQDisplayType MASS_TON_SHORT =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 24, MassUnit.TON_SHORT, "TON_SHORT", "ton (short)");

    /** Mass.TONNE unit type with code 25. */
    public static final Sim0MQDisplayType MASS_TONNE =
            new Sim0MQDisplayType(Sim0MQUnitType.MASS, 25, MassUnit.TONNE, "TONNE", "tonne");

    /* ===================================================== POWER ======================================================= */

    /** Power.WATT unit type with code 0. */
    public static final Sim0MQDisplayType POWER_WATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 0, PowerUnit.WATT, "WATT", "W");

    /** Power.FEMTOWATT unit type with code 1. */
    public static final Sim0MQDisplayType POWER_FEMTOWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 1, PowerUnit.FEMTOWATT, "FEMTOWATT", "fW");

    /** Power.PICOWATT unit type with code 2. */
    public static final Sim0MQDisplayType POWER_PICOWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 2, PowerUnit.PICOWATT, "PICOWATT", "pW");

    /** Power.NANOWATT unit type with code 3. */
    public static final Sim0MQDisplayType POWER_NANOWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 3, PowerUnit.NANOWATT, "NANOWATT", "mW");

    /** Power.MICROWATT unit type with code 4. */
    public static final Sim0MQDisplayType POWER_MICROWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 4, PowerUnit.MICROWATT, "MICROWATT", "μW");

    /** Power.MILLIWATT unit type with code 5. */
    public static final Sim0MQDisplayType POWER_MILLIWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 5, PowerUnit.MILLIWATT, "MILLIWATT", "mW");

    /** Power.KILOWATT unit type with code 6. */
    public static final Sim0MQDisplayType POWER_KILOWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 6, PowerUnit.KILOWATT, "KILOWATT", "kW");

    /** Power.MEGAWATT unit type with code 7. */
    public static final Sim0MQDisplayType POWER_MEGAWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 7, PowerUnit.MEGAWATT, "MEGAWATT", "MW");

    /** Power.GIGAWATT unit type with code 8. */
    public static final Sim0MQDisplayType POWER_GIGAWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 8, PowerUnit.GIGAWATT, "GIGAWATT", "GW");

    /** Power.TERAWATT unit type with code 9. */
    public static final Sim0MQDisplayType POWER_TERAWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 9, PowerUnit.TERAWATT, "TERAWATT", "TW");

    /** Power.PETAWATT unit type with code 10. */
    public static final Sim0MQDisplayType POWER_PETAWATT =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 10, PowerUnit.PETAWATT, "PETAWATT", "PW");

    /** Power.ERG_PER_SECOND unit type with code 11. */
    public static final Sim0MQDisplayType POWER_ERG_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 11, PowerUnit.ERG_PER_SECOND, "ERG_PER_SECOND", "erg/s");

    /** Power.FOOT_POUND_FORCE_PER_SECOND unit type with code 12. */
    public static final Sim0MQDisplayType POWER_FOOT_POUND_FORCE_PER_SECOND = new Sim0MQDisplayType(Sim0MQUnitType.POWER, 12,
            PowerUnit.FOOT_POUND_FORCE_PER_SECOND, "FOOT_POUND_FORCE_PER_SECOND", "ft.lbf/s");

    /** Power.FOOT_POUND_FORCE_PER_MINUTE unit type with code 13. */
    public static final Sim0MQDisplayType POWER_FOOT_POUND_FORCE_PER_MINUTE = new Sim0MQDisplayType(Sim0MQUnitType.POWER, 13,
            PowerUnit.FOOT_POUND_FORCE_PER_MINUTE, "FOOT_POUND_FORCE_PER_MINUTE", "ft.lbf/min");

    /** Power.FOOT_POUND_FORCE_PER_HOUR unit type with code 14. */
    public static final Sim0MQDisplayType POWER_FOOT_POUND_FORCE_PER_HOUR = new Sim0MQDisplayType(Sim0MQUnitType.POWER, 14,
            PowerUnit.FOOT_POUND_FORCE_PER_HOUR, "FOOT_POUND_FORCE_PER_HOUR", "ft.lbf/h");

    /** Power.HORSEPOWER_METRIC unit type with code 15. */
    public static final Sim0MQDisplayType POWER_HORSEPOWER_METRIC =
            new Sim0MQDisplayType(Sim0MQUnitType.POWER, 15, PowerUnit.HORSEPOWER_METRIC, "HORSEPOWER_METRIC", "hp");

    /** Power.STHENE_METER_PER_SECOND unit type with code 16. */
    public static final Sim0MQDisplayType POWER_STHENE_METER_PER_SECOND = new Sim0MQDisplayType(Sim0MQUnitType.POWER, 16,
            PowerUnit.STHENE_METER_PER_SECOND, "STHENE_METER_PER_SECOND", "sth/s");

    /* ===================================================== PRESSURE ==================================================== */

    /** Pressure.PASCAL unit type with code 0. */
    public static final Sim0MQDisplayType PRESSURE_PASCAL =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 0, PressureUnit.PASCAL, "PASCAL", "Pa");

    /** Pressure.HECTOPASCAL unit type with code 1. */
    public static final Sim0MQDisplayType PRESSURE_HECTOPASCAL =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 1, PressureUnit.HECTOPASCAL, "HECTOPASCAL", "hPa");

    /** Pressure.KILOPASCAL unit type with code 2. */
    public static final Sim0MQDisplayType PRESSURE_KILOPASCAL =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 2, PressureUnit.KILOPASCAL, "KILOPASCAL", "kPa");

    /** Pressure.ATMOSPHERE_STANDARD unit type with code 3. */
    public static final Sim0MQDisplayType PRESSURE_ATMOSPHERE_STANDARD =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 3, PressureUnit.ATMOSPHERE_STANDARD, "ATMOSPHERE_STANDARD", "atm");

    /** Pressure.ATMOSPHERE_TECHNICAL unit type with code 4. */
    public static final Sim0MQDisplayType PRESSURE_ATMOSPHERE_TECHNICAL =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 4, PressureUnit.ATMOSPHERE_TECHNICAL, "ATMOSPHERE_TECHNICAL", "at");

    /** Pressure.MILLIBAR unit type with code 5. */
    public static final Sim0MQDisplayType PRESSURE_MILLIBAR =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 5, PressureUnit.MILLIBAR, "MILLIBAR", "mbar");

    /** Pressure.BAR unit type with code 6. */
    public static final Sim0MQDisplayType PRESSURE_BAR =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 6, PressureUnit.BAR, "BAR", "bar");

    /** Pressure.BARYE unit type with code 7. */
    public static final Sim0MQDisplayType PRESSURE_BARYE =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 7, PressureUnit.BARYE, "BARYE", "Ba");

    /** Pressure.MILLIMETER_MERCURY unit type with code 8. */
    public static final Sim0MQDisplayType PRESSURE_MILLIMETER_MERCURY =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 8, PressureUnit.MILLIMETER_MERCURY, "MILLIMETER_MERCURY", "mmHg");

    /** Pressure.CENTIMETER_MERCURY unit type with code 9. */
    public static final Sim0MQDisplayType PRESSURE_CENTIMETER_MERCURY =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 9, PressureUnit.CENTIMETER_MERCURY, "CENTIMETER_MERCURY", "cmHg");

    /** Pressure.INCH_MERCURY unit type with code 10. */
    public static final Sim0MQDisplayType PRESSURE_INCH_MERCURY =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 10, PressureUnit.INCH_MERCURY, "INCH_MERCURY", "inHg");

    /** Pressure.FOOT_MERCURY unit type with code 11. */
    public static final Sim0MQDisplayType PRESSURE_FOOT_MERCURY =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 11, PressureUnit.FOOT_MERCURY, "FOOT_MERCURY", "ftHg");

    /** Pressure.KGF_PER_SQUARE_MM unit type with code 12. */
    public static final Sim0MQDisplayType PRESSURE_KGF_PER_SQUARE_MM =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 12, PressureUnit.KGF_PER_SQUARE_MM, "KGF_PER_SQUARE_MM", "kgf/mm2");

    /** Pressure.PIEZE unit type with code 13. */
    public static final Sim0MQDisplayType PRESSURE_PIEZE =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 13, PressureUnit.PIEZE, "PIEZE", "pz");

    /** Pressure.POUND_PER_SQUARE_INCH unit type with code 14. */
    public static final Sim0MQDisplayType PRESSURE_POUND_PER_SQUARE_INCH = new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 14,
            PressureUnit.POUND_PER_SQUARE_INCH, "POUND_PER_SQUARE_INCH", "lb/in2");

    /** Pressure.POUND_PER_SQUARE_FOOT unit type with code 15. */
    public static final Sim0MQDisplayType PRESSURE_POUND_PER_SQUARE_FOOT = new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 15,
            PressureUnit.POUND_PER_SQUARE_FOOT, "POUND_PER_SQUARE_FOOT", "lb/ft2");

    /** Pressure.TORR unit type with code 16. */
    public static final Sim0MQDisplayType PRESSURE_TORR =
            new Sim0MQDisplayType(Sim0MQUnitType.PRESSURE, 16, PressureUnit.TORR, "TORR", "torr");

    /* ===================================================== SPEED ======================================================= */

    /** Speed.METER_PER_SECOND unit type with code 0. */
    public static final Sim0MQDisplayType SPEED_METER_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 0, SpeedUnit.METER_PER_SECOND, "METER_PER_SECOND", "m/s");

    /** Speed.METER_PER_HOUR unit type with code 1. */
    public static final Sim0MQDisplayType SPEED_METER_PER_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 1, SpeedUnit.METER_PER_HOUR, "METER_PER_HOUR", "m/h");

    /** Speed.KM_PER_SECOND unit type with code 2. */
    public static final Sim0MQDisplayType SPEED_KM_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 2, SpeedUnit.KM_PER_SECOND, "KM_PER_SECOND", "km/s");

    /** Speed.KM_PER_HOUR unit type with code 3. */
    public static final Sim0MQDisplayType SPEED_KM_PER_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 3, SpeedUnit.KM_PER_HOUR, "KM_PER_HOUR", "km/h");

    /** Speed.INCH_PER_SECOND unit type with code 4. */
    public static final Sim0MQDisplayType SPEED_INCH_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 4, SpeedUnit.INCH_PER_SECOND, "INCH_PER_SECOND", "in/s");

    /** Speed.INCH_PER_MINUTE unit type with code 5. */
    public static final Sim0MQDisplayType SPEED_INCH_PER_MINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 5, SpeedUnit.INCH_PER_MINUTE, "INCH_PER_MINUTE", "in/min");

    /** Speed.INCH_PER_HOUR unit type with code 6. */
    public static final Sim0MQDisplayType SPEED_INCH_PER_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 6, SpeedUnit.INCH_PER_HOUR, "INCH_PER_HOUR", "in/h");

    /** Speed.FOOT_PER_SECOND unit type with code 7. */
    public static final Sim0MQDisplayType SPEED_FOOT_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 7, SpeedUnit.FOOT_PER_SECOND, "FOOT_PER_SECOND", "ft/s");

    /** Speed.FOOT_PER_MINUTE unit type with code 8. */
    public static final Sim0MQDisplayType SPEED_FOOT_PER_MINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 8, SpeedUnit.FOOT_PER_MINUTE, "FOOT_PER_MINUTE", "ft/min");

    /** Speed.FOOT_PER_HOUR unit type with code 9. */
    public static final Sim0MQDisplayType SPEED_FOOT_PER_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 9, SpeedUnit.FOOT_PER_HOUR, "FOOT_PER_HOUR", "ft/h");

    /** Speed.MILE_PER_SECOND unit type with code 10. */
    public static final Sim0MQDisplayType SPEED_MILE_PER_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 10, SpeedUnit.MILE_PER_SECOND, "MILE_PER_SECOND", "mi/s");

    /** Speed.MILE_PER_MINUTE unit type with code 11. */
    public static final Sim0MQDisplayType SPEED_MILE_PER_MINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 11, SpeedUnit.MILE_PER_MINUTE, "MILE_PER_MINUTE", "mi/min");

    /** Speed.MILE_PER_HOUR unit type with code 12. */
    public static final Sim0MQDisplayType SPEED_MILE_PER_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 12, SpeedUnit.MILE_PER_HOUR, "MILE_PER_HOUR", "mi/h");

    /** Speed.KNOT unit type with code 13. */
    public static final Sim0MQDisplayType SPEED_KNOT =
            new Sim0MQDisplayType(Sim0MQUnitType.SPEED, 13, SpeedUnit.KNOT, "KNOT", "kt");

    /* =================================================== TEMPERATURE =================================================== */

    /** Temperature.KELVIN unit type with code 0. */
    public static final Sim0MQDisplayType TEMPERATURE_KELVIN =
            new Sim0MQDisplayType(Sim0MQUnitType.TEMPERATURE, 0, TemperatureUnit.KELVIN, "KELVIN", "K");

    /** Temperature.DEGREE_CELSIUS unit type with code 1. */
    public static final Sim0MQDisplayType TEMPERATURE_DEGREE_CELSIUS =
            new Sim0MQDisplayType(Sim0MQUnitType.TEMPERATURE, 1, TemperatureUnit.DEGREE_CELSIUS, "DEGREE_CELSIUS", "OC");

    /** Temperature.DEGREE_FAHRENHEIT unit type with code 2. */
    public static final Sim0MQDisplayType TEMPERATURE_DEGREE_FAHRENHEIT =
            new Sim0MQDisplayType(Sim0MQUnitType.TEMPERATURE, 2, TemperatureUnit.DEGREE_FAHRENHEIT, "DEGREE_FAHRENHEIT", "OF");

    /** Temperature.DEGREE_RANKINE unit type with code 3. */
    public static final Sim0MQDisplayType TEMPERATURE_DEGREE_RANKINE =
            new Sim0MQDisplayType(Sim0MQUnitType.TEMPERATURE, 3, TemperatureUnit.DEGREE_RANKINE, "DEGREE_RANKINE", "OR");

    /** Temperature.DEGREE_REAUMUR unit type with code 4. */
    public static final Sim0MQDisplayType TEMPERATURE_DEGREE_REAUMUR =
            new Sim0MQDisplayType(Sim0MQUnitType.TEMPERATURE, 4, TemperatureUnit.DEGREE_REAUMUR, "DEGREE_REAUMUR", "ORé");

    /* ============================================== ABSOLUTETEMPERATURE ================================================ */

    /** AbsoluteTemperature.KELVIN unit type with code 0. */
    public static final Sim0MQDisplayType ABSOLUTETEMPERATURE_KELVIN =
            new Sim0MQDisplayType(Sim0MQUnitType.ABSOLUTETEMPERATURE, 0, AbsoluteTemperatureUnit.KELVIN, "KELVIN", "K");

    /** AbsoluteTemperature.DEGREE_CELSIUS unit type with code 1. */
    public static final Sim0MQDisplayType ABSOLUTETEMPERATURE_DEGREE_CELSIUS = new Sim0MQDisplayType(
            Sim0MQUnitType.ABSOLUTETEMPERATURE, 1, AbsoluteTemperatureUnit.DEGREE_CELSIUS, "DEGREE_CELSIUS", "OC");

    /** AbsoluteTemperature.DEGREE_FAHRENHEIT unit type with code 2. */
    public static final Sim0MQDisplayType ABSOLUTETEMPERATURE_DEGREE_FAHRENHEIT = new Sim0MQDisplayType(
            Sim0MQUnitType.ABSOLUTETEMPERATURE, 2, AbsoluteTemperatureUnit.DEGREE_FAHRENHEIT, "DEGREE_FAHRENHEIT", "OF");

    /** AbsoluteTemperature.DEGREE_RANKINE unit type with code 3. */
    public static final Sim0MQDisplayType ABSOLUTETEMPERATURE_DEGREE_RANKINE = new Sim0MQDisplayType(
            Sim0MQUnitType.ABSOLUTETEMPERATURE, 3, AbsoluteTemperatureUnit.DEGREE_RANKINE, "DEGREE_RANKINE", "OR");

    /** AbsoluteTemperature.DEGREE_REAUMUR unit type with code 4. */
    public static final Sim0MQDisplayType ABSOLUTETEMPERATURE_DEGREE_REAUMUR = new Sim0MQDisplayType(
            Sim0MQUnitType.ABSOLUTETEMPERATURE, 4, AbsoluteTemperatureUnit.DEGREE_REAUMUR, "DEGREE_REAUMUR", "ORé");

    /* ==================================================== DURATION ===================================================== */

    /** Duration.SECOND unit type with code 0. */
    public static final Sim0MQDisplayType DURATION_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 0, DurationUnit.SECOND, "SECOND", "s");

    /** Duration.ATTOSECOND unit type with code 1. */
    public static final Sim0MQDisplayType DURATION_ATTOSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 1, DurationUnit.ATTOSECOND, "ATTOSECOND", "as");

    /** Duration.FEMTOSECOND unit type with code 2. */
    public static final Sim0MQDisplayType DURATION_FEMTOSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 2, DurationUnit.FEMTOSECOND, "FEMTOSECOND", "fs");

    /** Duration.PICOSECOND unit type with code 3. */
    public static final Sim0MQDisplayType DURATION_PICOSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 3, DurationUnit.PICOSECOND, "PICOSECOND", "ps");

    /** Duration.NANOSECOND unit type with code 4. */
    public static final Sim0MQDisplayType DURATION_NANOSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 4, DurationUnit.NANOSECOND, "NANOSECOND", "ns");

    /** Duration.MICROSECOND unit type with code 5. */
    public static final Sim0MQDisplayType DURATION_MICROSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 5, DurationUnit.MICROSECOND, "MICROSECOND", "μs");

    /** Duration.MILLISECOND unit type with code 6. */
    public static final Sim0MQDisplayType DURATION_MILLISECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 6, DurationUnit.MILLISECOND, "MILLISECOND", "ms");

    /** Duration.MINUTE unit type with code 7. */
    public static final Sim0MQDisplayType DURATION_MINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 7, DurationUnit.MINUTE, "MINUTE", "min");

    /** Duration.HOUR unit type with code 8. */
    public static final Sim0MQDisplayType DURATION_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 8, DurationUnit.HOUR, "HOUR", "hr");

    /** Duration.DAY unit type with code 9. */
    public static final Sim0MQDisplayType DURATION_DAY =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 9, DurationUnit.DAY, "DAY", "day");

    /** Duration.WEEK unit type with code 10. */
    public static final Sim0MQDisplayType DURATION_WEEK =
            new Sim0MQDisplayType(Sim0MQUnitType.DURATION, 10, DurationUnit.WEEK, "WEEK", "wk");

    /* ====================================================== TIME ======================================================= */

    /** Time.BASE_SECOND unit type with code 0. */
    public static final Sim0MQDisplayType TIME_BASE_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 0, TimeUnit.BASE_SECOND, "SECOND", "s");

    /** Time.BASE_MICROSECOND unit type with code 1. */
    public static final Sim0MQDisplayType TIME_BASE_MICROSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 1, TimeUnit.BASE_MICROSECOND, "MICROSECOND", "μs");

    /** Time.BASE_MILLISECOND unit type with code 2. */
    public static final Sim0MQDisplayType TIME_BASE_MILLISECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 2, TimeUnit.BASE_MILLISECOND, "MILLISECOND", "ms");

    /** Time.BASE_MINUTE unit type with code 3. */
    public static final Sim0MQDisplayType TIME_BASE_MINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 3, TimeUnit.BASE_MINUTE, "MINUTE", "min");

    /** Time.BASE_HOUR unit type with code 4. */
    public static final Sim0MQDisplayType TIME_BASE_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 4, TimeUnit.BASE_HOUR, "HOUR", "hr");

    /** Time.BASE_DAY unit type with code 5. */
    public static final Sim0MQDisplayType TIME_BASE_DAY =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 5, TimeUnit.BASE_DAY, "DAY", "day");

    /** Time.BASE_WEEK unit type with code 6. */
    public static final Sim0MQDisplayType TIME_BASE_WEEK =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 6, TimeUnit.BASE_WEEK, "WEEK", "wk");

    /** Time.EPOCH_SECOND unit type with code 7. */
    public static final Sim0MQDisplayType TIME_EPOCH_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 7, TimeUnit.EPOCH_SECOND, "SECOND (1-1-70)", "s(POSIX)");

    /** Time.EPOCH_MICROSECOND unit type with code 8. */
    public static final Sim0MQDisplayType TIME_EPOCH_MICROSECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 8, TimeUnit.BASE_MICROSECOND, "MICROSECOND (1-1-70)", "μs(POSIX)");

    /** Time.EPOCH_MILLISECOND unit type with code 9. */
    public static final Sim0MQDisplayType TIME_EPOCH_MILLISECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 9, TimeUnit.BASE_MILLISECOND, "MILLISECOND (1-1-70)", "ms(POSIX)");

    /** Time.EPOCH_MINUTE unit type with code 10. */
    public static final Sim0MQDisplayType TIME_EPOCH_MINUTE =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 10, TimeUnit.BASE_MINUTE, "MINUTE (1-1-70)", "min(POSIX)");

    /** Time.EPOCH_HOUR unit type with code 11. */
    public static final Sim0MQDisplayType TIME_EPOCH_HOUR =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 11, TimeUnit.BASE_HOUR, "HOUR (1-1-70)", "hr(POSIX)");

    /** Time.EPOCH_DAY unit type with code 12. */
    public static final Sim0MQDisplayType TIME_EPOCH_DAY =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 12, TimeUnit.BASE_DAY, "DAY (1-1-70)", "day(POSIX)");

    /** Time.EPOCH_WEEK unit type with code 13. */
    public static final Sim0MQDisplayType TIME_EPOCH_WEEK =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 13, TimeUnit.BASE_WEEK, "WEEK (1-1-70)", "wk(POSIX)");

    /** Time.TIME_YEAR1_SECOND unit type with code 14. */
    public static final Sim0MQDisplayType TIME_YEAR1_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 14, TimeUnit.EPOCH_YEAR_1, "SECOND (1-1-0001)", "s(1-1-0001)");

    /** Time.TIME_J2000_SECOND unit type with code 15. */
    public static final Sim0MQDisplayType TIME_J2000_SECOND =
            new Sim0MQDisplayType(Sim0MQUnitType.TIME, 15, TimeUnit.EPOCH_J2000_1, "SECOND (1-1-2000 12:00)", "s(1-1-2000)");

    /* ===================================================== TORQUE ====================================================== */

    /** Torque.NEWTON_METER unit type with code 0. */
    public static final Sim0MQDisplayType TORQUE_NEWTON_METER =
            new Sim0MQDisplayType(Sim0MQUnitType.TORQUE, 0, TorqueUnit.NEWTON_METER, "NEWTON_METER", "Nm");

    /** Torque.POUND_FOOT unit type with code 1. */
    public static final Sim0MQDisplayType TORQUE_POUND_FOOT =
            new Sim0MQDisplayType(Sim0MQUnitType.TORQUE, 1, TorqueUnit.POUND_FOOT, "POUND_FOOT", "lb.ft");

    /** Torque.POUND_INCH unit type with code 2. */
    public static final Sim0MQDisplayType TORQUE_POUND_INCH =
            new Sim0MQDisplayType(Sim0MQUnitType.TORQUE, 2, TorqueUnit.POUND_INCH, "POUND_INCH", "lb.in");

    /** Torque.METER_KILOGRAM_FORCE unit type with code 3. */
    public static final Sim0MQDisplayType TORQUE_METER_KILOGRAM_FORCE =
            new Sim0MQDisplayType(Sim0MQUnitType.TORQUE, 3, TorqueUnit.METER_KILOGRAM_FORCE, "METER_KILOGRAM_FORCE", "m.kgf");

    /* ===================================================== VOLUME ====================================================== */

    /** Volume.CUBIC_METER unit type with code 0. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_METER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 0, VolumeUnit.CUBIC_METER, "CUBIC_METER", "m3");

    /** Volume.CUBIC_ATTOMETER unit type with code 1. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_ATTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 1, VolumeUnit.CUBIC_ATTOMETER, "CUBIC_ATTOMETER", "am3");

    /** Volume.CUBIC_FEMTOMETER unit type with code 2. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_FEMTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 2, VolumeUnit.CUBIC_FEMTOMETER, "CUBIC_FEMTOMETER", "fm3");

    /** Volume.CUBIC_PICOMETER unit type with code 3. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_PICOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 3, VolumeUnit.CUBIC_PICOMETER, "CUBIC_PICOMETER", "pm3");

    /** Volume.CUBIC_NANOMETER unit type with code 4. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_NANOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 4, VolumeUnit.CUBIC_NANOMETER, "CUBIC_NANOMETER", "nm3");

    /** Volume.CUBIC_MICROMETER unit type with code 5. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_MICROMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 5, VolumeUnit.CUBIC_MICROMETER, "CUBIC_MICROMETER", "μm3");

    /** Volume.CUBIC_MILLIMETER unit type with code 6. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_MILLIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 6, VolumeUnit.CUBIC_MILLIMETER, "CUBIC_MILLIMETER", "mm3");

    /** Volume.CUBIC_CENTIMETER unit type with code 7. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_CENTIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 7, VolumeUnit.CUBIC_CENTIMETER, "CUBIC_CENTIMETER", "cm3");

    /** Volume.CUBIC_DECIMETER unit type with code 8. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_DECIMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 8, VolumeUnit.CUBIC_DECIMETER, "CUBIC_DECIMETER", "dm3");

    /** Volume.CUBIC_DEKAMETER unit type with code 9. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_DEKAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 9, VolumeUnit.CUBIC_DEKAMETER, "CUBIC_DEKAMETER", "dam3");

    /** Volume.CUBIC_HECTOMETER unit type with code 10. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_HECTOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 10, VolumeUnit.CUBIC_HECTOMETER, "CUBIC_HECTOMETER", "hm3");

    /** Volume.CUBIC_KILOMETER unit type with code 11. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_KILOMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 11, VolumeUnit.CUBIC_KILOMETER, "CUBIC_KILOMETER", "km3");

    /** Volume.CUBIC_MEGAMETER unit type with code 12. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_MEGAMETER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 12, VolumeUnit.CUBIC_MEGAMETER, "CUBIC_MEGAMETER", "Mm3");

    /** Volume.CUBIC_INCH unit type with code 13. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_INCH =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 13, VolumeUnit.CUBIC_INCH, "CUBIC_INCH", "in3");

    /** Volume.CUBIC_FOOT unit type with code 14. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_FOOT =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 14, VolumeUnit.CUBIC_FOOT, "CUBIC_FOOT", "ft3");

    /** Volume.CUBIC_YARD unit type with code 15. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_YARD =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 15, VolumeUnit.CUBIC_YARD, "CUBIC_YARD", "yd3");

    /** Volume.CUBIC_MILE unit type with code 16. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_MILE =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 16, VolumeUnit.CUBIC_MILE, "CUBIC_MILE", "mi3");

    /** Volume.LITER unit type with code 17. */
    public static final Sim0MQDisplayType VOLUME_LITER =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 17, VolumeUnit.LITER, "LITER", "l");

    /** Volume.GALLON_IMP unit type with code 18. */
    public static final Sim0MQDisplayType VOLUME_GALLON_IMP =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 18, VolumeUnit.GALLON_IMP, "GALLON_IMP", "gal (imp)");

    /** Volume.GALLON_US_FLUID unit type with code 19. */
    public static final Sim0MQDisplayType VOLUME_GALLON_US_FLUID =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 19, VolumeUnit.GALLON_US_FLUID, "GALLON_US_FLUID", "gal (US)");

    /** Volume.OUNCE_IMP_FLUID unit type with code 20. */
    public static final Sim0MQDisplayType VOLUME_OUNCE_IMP_FLUID =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 20, VolumeUnit.OUNCE_IMP_FLUID, "OUNCE_IMP_FLUID", "oz (imp)");

    /** Volume.OUNCE_US_FLUID unit type with code 21. */
    public static final Sim0MQDisplayType VOLUME_OUNCE_US_FLUID =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 21, VolumeUnit.OUNCE_US_FLUID, "OUNCE_US_FLUID", "oz (US)");

    /** Volume.PINT_IMP unit type with code 22. */
    public static final Sim0MQDisplayType VOLUME_PINT_IMP =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 22, VolumeUnit.PINT_IMP, "PINT_IMP", "pt (imp)");

    /** Volume.PINT_US_FLUID unit type with code 23. */
    public static final Sim0MQDisplayType VOLUME_PINT_US_FLUID =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 23, VolumeUnit.PINT_US_FLUID, "PINT_US_FLUID", "pt (US)");

    /** Volume.QUART_IMP unit type with code 24. */
    public static final Sim0MQDisplayType VOLUME_QUART_IMP =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 24, VolumeUnit.QUART_IMP, "QUART_IMP", "qt (imp)");

    /** Volume.QUART_US_FLUID unit type with code 25. */
    public static final Sim0MQDisplayType VOLUME_QUART_US_FLUID =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 25, VolumeUnit.QUART_US_FLUID, "QUART_US_FLUID", "qt (US)");

    /** Volume.CUBIC_PARSEC unit type with code 26. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_PARSEC =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 26, VolumeUnit.CUBIC_PARSEC, "CUBIC_PARSEC", "pc3");

    /** Volume.CUBIC_LIGHTYEAR unit type with code 27. */
    public static final Sim0MQDisplayType VOLUME_CUBIC_LIGHTYEAR =
            new Sim0MQDisplayType(Sim0MQUnitType.VOLUME, 27, VolumeUnit.CUBIC_LIGHTYEAR, "CUBIC_LIGHTYEAR", "ly3");

    /* ====================================================== MONEY ====================================================== */

    /** Money.AED unit type with code 784. */
    public static final Sim0MQDisplayType MONEY_AED =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 784, MoneyUnit.AED, "AED", "United Arab Emirates dirham");

    /** Money.AFN unit type with code 971. */
    public static final Sim0MQDisplayType MONEY_AFN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 971, MoneyUnit.AFN, "AFN", "Afghan afghani");

    /** Money.ALL unit type with code 8. */
    public static final Sim0MQDisplayType MONEY_ALL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 8, MoneyUnit.ALL, "ALL", "Albanian lek");

    /** Money.AMD unit type with code 51. */
    public static final Sim0MQDisplayType MONEY_AMD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 51, MoneyUnit.AMD, "AMD", "Armenian dram");

    /** Money.ANG unit type with code 532. */
    public static final Sim0MQDisplayType MONEY_ANG =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 532, MoneyUnit.ANG, "ANG", "Netherlands Antillean guilder");

    /** Money.AOA unit type with code 973. */
    public static final Sim0MQDisplayType MONEY_AOA =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 973, MoneyUnit.AOA, "AOA", "Angolan kwanza");

    /** Money.ARS unit type with code 32. */
    public static final Sim0MQDisplayType MONEY_ARS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 32, MoneyUnit.ARS, "ARS", "Argentine peso");

    /** Money.AUD unit type with code 36. */
    public static final Sim0MQDisplayType MONEY_AUD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 36, MoneyUnit.AUD, "AUD", "Australian dollar");

    /** Money.AWG unit type with code 533. */
    public static final Sim0MQDisplayType MONEY_AWG =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 533, MoneyUnit.AWG, "AWG", "Aruban florin");

    /** Money.AZN unit type with code 944. */
    public static final Sim0MQDisplayType MONEY_AZN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 944, MoneyUnit.AZN, "AZN", "Azerbaijani manat");

    /** Money.BAM unit type with code 977. */
    public static final Sim0MQDisplayType MONEY_BAM =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 977, MoneyUnit.BAM, "BAM", "Bosnia and Herzegovina convertible mark");

    /** Money.BBD unit type with code 52. */
    public static final Sim0MQDisplayType MONEY_BBD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 52, MoneyUnit.BBD, "BBD", "Barbados dollar");

    /** Money.BDT unit type with code 50. */
    public static final Sim0MQDisplayType MONEY_BDT =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 50, MoneyUnit.BDT, "BDT", "Bangladeshi taka");

    /** Money.BGN unit type with code 975. */
    public static final Sim0MQDisplayType MONEY_BGN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 975, MoneyUnit.BGN, "BGN", "Bulgarian lev");

    /** Money.BHD unit type with code 48. */
    public static final Sim0MQDisplayType MONEY_BHD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 48, MoneyUnit.BHD, "BHD", "Bahraini dinar");

    /** Money.BIF unit type with code 108. */
    public static final Sim0MQDisplayType MONEY_BIF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 108, MoneyUnit.BIF, "BIF", "Burundian franc");

    /** Money.BMD unit type with code 60. */
    public static final Sim0MQDisplayType MONEY_BMD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 60, MoneyUnit.BMD, "BMD", "Bermudian dollar");

    /** Money.BND unit type with code 96. */
    public static final Sim0MQDisplayType MONEY_BND =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 96, MoneyUnit.BND, "BND", "Brunei dollar");

    /** Money.BOB unit type with code 68. */
    public static final Sim0MQDisplayType MONEY_BOB =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 68, MoneyUnit.BOB, "BOB", "Boliviano");

    /** Money.BOV unit type with code 984. */
    public static final Sim0MQDisplayType MONEY_BOV =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 984, MoneyUnit.BOV, "BOV", "Bolivian Mvdol (funds code)");

    /** Money.BRL unit type with code 986. */
    public static final Sim0MQDisplayType MONEY_BRL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 986, MoneyUnit.BRL, "BRL", "Brazilian real");

    /** Money.BSD unit type with code 44. */
    public static final Sim0MQDisplayType MONEY_BSD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 44, MoneyUnit.BSD, "BSD", "Bahamian dollar");

    /** Money.BTN unit type with code 64. */
    public static final Sim0MQDisplayType MONEY_BTN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 64, MoneyUnit.BTN, "BTN", "Bhutanese ngultrum");

    /** Money.BWP unit type with code 72. */
    public static final Sim0MQDisplayType MONEY_BWP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 72, MoneyUnit.BWP, "BWP", "Botswana pula");

    /** Money.BYN unit type with code 933. */
    public static final Sim0MQDisplayType MONEY_BYN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 933, MoneyUnit.BYN, "BYN", "New Belarusian ruble");

    /** Money.BYR unit type with code 974. */
    public static final Sim0MQDisplayType MONEY_BYR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 974, MoneyUnit.BYR, "BYR", "Belarusian ruble");

    /** Money.BZD unit type with code 84. */
    public static final Sim0MQDisplayType MONEY_BZD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 84, MoneyUnit.BZD, "BZD", "Belize dollar");

    /** Money.CAD unit type with code 124. */
    public static final Sim0MQDisplayType MONEY_CAD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 124, MoneyUnit.CAD, "CAD", "Canadian dollar");

    /** Money.CDF unit type with code 976. */
    public static final Sim0MQDisplayType MONEY_CDF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 976, MoneyUnit.CDF, "CDF", "Congolese franc");

    /** Money.CHE unit type with code 947. */
    public static final Sim0MQDisplayType MONEY_CHE =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 947, MoneyUnit.CHE, "CHE", "WIR Euro (complementary currency)");

    /** Money.CHF unit type with code 756. */
    public static final Sim0MQDisplayType MONEY_CHF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 756, MoneyUnit.CHF, "CHF", "Swiss franc");

    /** Money.CHW unit type with code 948. */
    public static final Sim0MQDisplayType MONEY_CHW =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 948, MoneyUnit.CHW, "CHW", "WIR Franc (complementary currency)");

    /** Money.CLF unit type with code 990. */
    public static final Sim0MQDisplayType MONEY_CLF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 990, MoneyUnit.CLF, "CLF", "Unidad de Fomento (funds code)");

    /** Money.CLP unit type with code 152. */
    public static final Sim0MQDisplayType MONEY_CLP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 152, MoneyUnit.CLP, "CLP", "Chilean peso");

    /** Money.CNY unit type with code 156. */
    public static final Sim0MQDisplayType MONEY_CNY =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 156, MoneyUnit.CNY, "CNY", "Chinese yuan");

    /** Money.COP unit type with code 170. */
    public static final Sim0MQDisplayType MONEY_COP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 170, MoneyUnit.COP, "COP", "Colombian peso");

    /** Money.COU unit type with code 970. */
    public static final Sim0MQDisplayType MONEY_COU =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 970, MoneyUnit.COU, "COU", "Unidad de Valor Real (UVR) (funds code)");

    /** Money.CRC unit type with code 188. */
    public static final Sim0MQDisplayType MONEY_CRC =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 188, MoneyUnit.CRC, "CRC", "Costa Rican colon");

    /** Money.CUC unit type with code 931. */
    public static final Sim0MQDisplayType MONEY_CUC =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 931, MoneyUnit.CUC, "CUC", "Cuban convertible peso");

    /** Money.CUP unit type with code 192. */
    public static final Sim0MQDisplayType MONEY_CUP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 192, MoneyUnit.CUP, "CUP", "Cuban peso");

    /** Money.CVE unit type with code 132. */
    public static final Sim0MQDisplayType MONEY_CVE =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 132, MoneyUnit.CVE, "CVE", "Cape Verde escudo");

    /** Money.CZK unit type with code 203. */
    public static final Sim0MQDisplayType MONEY_CZK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 203, MoneyUnit.CZK, "CZK", "Czech koruna");

    /** Money.DJF unit type with code 262. */
    public static final Sim0MQDisplayType MONEY_DJF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 262, MoneyUnit.DJF, "DJF", "Djiboutian franc");

    /** Money.DKK unit type with code 208. */
    public static final Sim0MQDisplayType MONEY_DKK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 208, MoneyUnit.DKK, "DKK", "Danish krone");

    /** Money.DOP unit type with code 214. */
    public static final Sim0MQDisplayType MONEY_DOP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 214, MoneyUnit.DOP, "DOP", "Dominican peso");

    /** Money.DZD unit type with code 12. */
    public static final Sim0MQDisplayType MONEY_DZD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 12, MoneyUnit.DZD, "DZD", "Algerian dinar");

    /** Money.EGP unit type with code 818. */
    public static final Sim0MQDisplayType MONEY_EGP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 818, MoneyUnit.EGP, "EGP", "Egyptian pound");

    /** Money.ERN unit type with code 232. */
    public static final Sim0MQDisplayType MONEY_ERN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 232, MoneyUnit.ERN, "ERN", "Eritrean nakfa");

    /** Money.ETB unit type with code 230. */
    public static final Sim0MQDisplayType MONEY_ETB =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 230, MoneyUnit.ETB, "ETB", "Ethiopian birr");

    /** Money.EUR unit type with code 978. */
    public static final Sim0MQDisplayType MONEY_EUR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 978, MoneyUnit.EUR, "EUR", "Euro");

    /** Money.FJD unit type with code 242. */
    public static final Sim0MQDisplayType MONEY_FJD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 242, MoneyUnit.FJD, "FJD", "Fiji dollar");

    /** Money.FKP unit type with code 238. */
    public static final Sim0MQDisplayType MONEY_FKP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 238, MoneyUnit.FKP, "FKP", "Falkland Islands pound");

    /** Money.GBP unit type with code 826. */
    public static final Sim0MQDisplayType MONEY_GBP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 826, MoneyUnit.GBP, "GBP", "Pound sterling");

    /** Money.GEL unit type with code 981. */
    public static final Sim0MQDisplayType MONEY_GEL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 981, MoneyUnit.GEL, "GEL", "Georgian lari");

    /** Money.GHS unit type with code 936. */
    public static final Sim0MQDisplayType MONEY_GHS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 936, MoneyUnit.GHS, "GHS", "Ghanaian cedi");

    /** Money.GIP unit type with code 292. */
    public static final Sim0MQDisplayType MONEY_GIP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 292, MoneyUnit.GIP, "GIP", "Gibraltar pound");

    /** Money.GMD unit type with code 270. */
    public static final Sim0MQDisplayType MONEY_GMD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 270, MoneyUnit.GMD, "GMD", "Gambian dalasi");

    /** Money.GNF unit type with code 324. */
    public static final Sim0MQDisplayType MONEY_GNF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 324, MoneyUnit.GNF, "GNF", "Guinean franc");

    /** Money.GTQ unit type with code 320. */
    public static final Sim0MQDisplayType MONEY_GTQ =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 320, MoneyUnit.GTQ, "GTQ", "Guatemalan quetzal");

    /** Money.GYD unit type with code 328. */
    public static final Sim0MQDisplayType MONEY_GYD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 328, MoneyUnit.GYD, "GYD", "Guyanese dollar");

    /** Money.HKD unit type with code 344. */
    public static final Sim0MQDisplayType MONEY_HKD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 344, MoneyUnit.HKD, "HKD", "Hong Kong dollar");

    /** Money.HNL unit type with code 340. */
    public static final Sim0MQDisplayType MONEY_HNL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 340, MoneyUnit.HNL, "HNL", "Honduran lempira");

    /** Money.HRK unit type with code 191. */
    public static final Sim0MQDisplayType MONEY_HRK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 191, MoneyUnit.HRK, "HRK", "Croatian kuna");

    /** Money.HTG unit type with code 332. */
    public static final Sim0MQDisplayType MONEY_HTG =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 332, MoneyUnit.HTG, "HTG", "Haitian gourde");

    /** Money.HUF unit type with code 348. */
    public static final Sim0MQDisplayType MONEY_HUF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 348, MoneyUnit.HUF, "HUF", "Hungarian forint");

    /** Money.IDR unit type with code 360. */
    public static final Sim0MQDisplayType MONEY_IDR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 360, MoneyUnit.IDR, "IDR", "Indonesian rupiah");

    /** Money.ILS unit type with code 376. */
    public static final Sim0MQDisplayType MONEY_ILS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 376, MoneyUnit.ILS, "ILS", "Israeli new shekel");

    /** Money.INR unit type with code 356. */
    public static final Sim0MQDisplayType MONEY_INR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 356, MoneyUnit.INR, "INR", "Indian rupee");

    /** Money.IQD unit type with code 368. */
    public static final Sim0MQDisplayType MONEY_IQD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 368, MoneyUnit.IQD, "IQD", "Iraqi dinar");

    /** Money.IRR unit type with code 364. */
    public static final Sim0MQDisplayType MONEY_IRR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 364, MoneyUnit.IRR, "IRR", "Iranian rial");

    /** Money.ISK unit type with code 352. */
    public static final Sim0MQDisplayType MONEY_ISK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 352, MoneyUnit.ISK, "ISK", "Icelandic króna");

    /** Money.JMD unit type with code 388. */
    public static final Sim0MQDisplayType MONEY_JMD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 388, MoneyUnit.JMD, "JMD", "Jamaican dollar");

    /** Money.JOD unit type with code 400. */
    public static final Sim0MQDisplayType MONEY_JOD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 400, MoneyUnit.JOD, "JOD", "Jordanian dinar");

    /** Money.JPY unit type with code 392. */
    public static final Sim0MQDisplayType MONEY_JPY =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 392, MoneyUnit.JPY, "JPY", "Japanese yen");

    /** Money.KES unit type with code 404. */
    public static final Sim0MQDisplayType MONEY_KES =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 404, MoneyUnit.KES, "KES", "Kenyan shilling");

    /** Money.KGS unit type with code 417. */
    public static final Sim0MQDisplayType MONEY_KGS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 417, MoneyUnit.KGS, "KGS", "Kyrgyzstani som");

    /** Money.KHR unit type with code 116. */
    public static final Sim0MQDisplayType MONEY_KHR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 116, MoneyUnit.KHR, "KHR", "Cambodian riel");

    /** Money.KMF unit type with code 174. */
    public static final Sim0MQDisplayType MONEY_KMF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 174, MoneyUnit.KMF, "KMF", "Comoro franc");

    /** Money.KPW unit type with code 408. */
    public static final Sim0MQDisplayType MONEY_KPW =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 408, MoneyUnit.KPW, "KPW", "North Korean won");

    /** Money.KRW unit type with code 410. */
    public static final Sim0MQDisplayType MONEY_KRW =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 410, MoneyUnit.KRW, "KRW", "South Korean won");

    /** Money.KWD unit type with code 414. */
    public static final Sim0MQDisplayType MONEY_KWD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 414, MoneyUnit.KWD, "KWD", "Kuwaiti dinar");

    /** Money.KYD unit type with code 136. */
    public static final Sim0MQDisplayType MONEY_KYD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 136, MoneyUnit.KYD, "KYD", "Cayman Islands dollar");

    /** Money.KZT unit type with code 398. */
    public static final Sim0MQDisplayType MONEY_KZT =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 398, MoneyUnit.KZT, "KZT", "Kazakhstani tenge");

    /** Money.LAK unit type with code 418. */
    public static final Sim0MQDisplayType MONEY_LAK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 418, MoneyUnit.LAK, "LAK", "Lao kip");

    /** Money.LBP unit type with code 422. */
    public static final Sim0MQDisplayType MONEY_LBP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 422, MoneyUnit.LBP, "LBP", "Lebanese pound");

    /** Money.LKR unit type with code 144. */
    public static final Sim0MQDisplayType MONEY_LKR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 144, MoneyUnit.LKR, "LKR", "Sri Lankan rupee");

    /** Money.LRD unit type with code 430. */
    public static final Sim0MQDisplayType MONEY_LRD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 430, MoneyUnit.LRD, "LRD", "Liberian dollar");

    /** Money.LSL unit type with code 426. */
    public static final Sim0MQDisplayType MONEY_LSL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 426, MoneyUnit.LSL, "LSL", "Lesotho loti");

    /** Money.LYD unit type with code 434. */
    public static final Sim0MQDisplayType MONEY_LYD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 434, MoneyUnit.LYD, "LYD", "Libyan dinar");

    /** Money.MAD unit type with code 504. */
    public static final Sim0MQDisplayType MONEY_MAD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 504, MoneyUnit.MAD, "MAD", "Moroccan dirham");

    /** Money.MDL unit type with code 498. */
    public static final Sim0MQDisplayType MONEY_MDL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 498, MoneyUnit.MDL, "MDL", "Moldovan leu");

    /** Money.MGA unit type with code 969. */
    public static final Sim0MQDisplayType MONEY_MGA =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 969, MoneyUnit.MGA, "MGA", "Malagasy ariary");

    /** Money.MKD unit type with code 807. */
    public static final Sim0MQDisplayType MONEY_MKD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 807, MoneyUnit.MKD, "MKD", "Macedonian denar");

    /** Money.MMK unit type with code 104. */
    public static final Sim0MQDisplayType MONEY_MMK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 104, MoneyUnit.MMK, "MMK", "Myanmar kyat");

    /** Money.MNT unit type with code 496. */
    public static final Sim0MQDisplayType MONEY_MNT =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 496, MoneyUnit.MNT, "MNT", "Mongolian tögrög");

    /** Money.MOP unit type with code 446. */
    public static final Sim0MQDisplayType MONEY_MOP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 446, MoneyUnit.MOP, "MOP", "Macanese pataca");

    /** Money.MRO unit type with code 478. */
    public static final Sim0MQDisplayType MONEY_MRO =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 478, MoneyUnit.MRO, "MRO", "Mauritanian ouguiya");

    /** Money.MUR unit type with code 480. */
    public static final Sim0MQDisplayType MONEY_MUR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 480, MoneyUnit.MUR, "MUR", "Mauritian rupee");

    /** Money.MVR unit type with code 462. */
    public static final Sim0MQDisplayType MONEY_MVR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 462, MoneyUnit.MVR, "MVR", "Maldivian rufiyaa");

    /** Money.MWK unit type with code 454. */
    public static final Sim0MQDisplayType MONEY_MWK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 454, MoneyUnit.MWK, "MWK", "Malawian kwacha");

    /** Money.MXN unit type with code 484. */
    public static final Sim0MQDisplayType MONEY_MXN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 484, MoneyUnit.MXN, "MXN", "Mexican peso");

    /** Money.MXV unit type with code 979. */
    public static final Sim0MQDisplayType MONEY_MXV = new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 979, MoneyUnit.MXV, "MXV",
            "Mexican Unidad de Inversion (UDI) (funds code)");

    /** Money.MYR unit type with code 458. */
    public static final Sim0MQDisplayType MONEY_MYR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 458, MoneyUnit.MYR, "MYR", "Malaysian ringgit");

    /** Money.MZN unit type with code 943. */
    public static final Sim0MQDisplayType MONEY_MZN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 943, MoneyUnit.MZN, "MZN", "Mozambican metical");

    /** Money.NAD unit type with code 516. */
    public static final Sim0MQDisplayType MONEY_NAD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 516, MoneyUnit.NAD, "NAD", "Namibian dollar");

    /** Money.NGN unit type with code 566. */
    public static final Sim0MQDisplayType MONEY_NGN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 566, MoneyUnit.NGN, "NGN", "Nigerian naira");

    /** Money.NIO unit type with code 558. */
    public static final Sim0MQDisplayType MONEY_NIO =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 558, MoneyUnit.NIO, "NIO", "Nicaraguan córdoba");

    /** Money.NOK unit type with code 578. */
    public static final Sim0MQDisplayType MONEY_NOK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 578, MoneyUnit.NOK, "NOK", "Norwegian krone");

    /** Money.NPR unit type with code 524. */
    public static final Sim0MQDisplayType MONEY_NPR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 524, MoneyUnit.NPR, "NPR", "Nepalese rupee");

    /** Money.NZD unit type with code 554. */
    public static final Sim0MQDisplayType MONEY_NZD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 554, MoneyUnit.NZD, "NZD", "New Zealand dollar");

    /** Money.OMR unit type with code 512. */
    public static final Sim0MQDisplayType MONEY_OMR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 512, MoneyUnit.OMR, "OMR", "Omani rial");

    /** Money.PAB unit type with code 590. */
    public static final Sim0MQDisplayType MONEY_PAB =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 590, MoneyUnit.PAB, "PAB", "Panamanian balboa");

    /** Money.PEN unit type with code 604. */
    public static final Sim0MQDisplayType MONEY_PEN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 604, MoneyUnit.PEN, "PEN", "Peruvian Sol");

    /** Money.PGK unit type with code 598. */
    public static final Sim0MQDisplayType MONEY_PGK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 598, MoneyUnit.PGK, "PGK", "Papua New Guinean kina");

    /** Money.PHP unit type with code 608. */
    public static final Sim0MQDisplayType MONEY_PHP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 608, MoneyUnit.PHP, "PHP", "Philippine peso");

    /** Money.PKR unit type with code 586. */
    public static final Sim0MQDisplayType MONEY_PKR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 586, MoneyUnit.PKR, "PKR", "Pakistani rupee");

    /** Money.PLN unit type with code 985. */
    public static final Sim0MQDisplayType MONEY_PLN =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 985, MoneyUnit.PLN, "PLN", "Polish zloty");

    /** Money.PYG unit type with code 600. */
    public static final Sim0MQDisplayType MONEY_PYG =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 600, MoneyUnit.PYG, "PYG", "Paraguayan guaraní");

    /** Money.QAR unit type with code 634. */
    public static final Sim0MQDisplayType MONEY_QAR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 634, MoneyUnit.QAR, "QAR", "Qatari riyal");

    /** Money.RON unit type with code 946. */
    public static final Sim0MQDisplayType MONEY_RON =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 946, MoneyUnit.RON, "RON", "Romanian leu");

    /** Money.RSD unit type with code 941. */
    public static final Sim0MQDisplayType MONEY_RSD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 941, MoneyUnit.RSD, "RSD", "Serbian dinar");

    /** Money.RUB unit type with code 643. */
    public static final Sim0MQDisplayType MONEY_RUB =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 643, MoneyUnit.RUB, "RUB", "Russian ruble");

    /** Money.RWF unit type with code 646. */
    public static final Sim0MQDisplayType MONEY_RWF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 646, MoneyUnit.RWF, "RWF", "Rwandan franc");

    /** Money.SAR unit type with code 682. */
    public static final Sim0MQDisplayType MONEY_SAR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 682, MoneyUnit.SAR, "SAR", "Saudi riyal");

    /** Money.SBD unit type with code 90. */
    public static final Sim0MQDisplayType MONEY_SBD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 90, MoneyUnit.SBD, "SBD", "Solomon Islands dollar");

    /** Money.SCR unit type with code 690. */
    public static final Sim0MQDisplayType MONEY_SCR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 690, MoneyUnit.SCR, "SCR", "Seychelles rupee");

    /** Money.SDG unit type with code 938. */
    public static final Sim0MQDisplayType MONEY_SDG =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 938, MoneyUnit.SDG, "SDG", "Sudanese pound");

    /** Money.SEK unit type with code 752. */
    public static final Sim0MQDisplayType MONEY_SEK =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 752, MoneyUnit.SEK, "SEK", "Swedish krona/kronor");

    /** Money.SGD unit type with code 702. */
    public static final Sim0MQDisplayType MONEY_SGD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 702, MoneyUnit.SGD, "SGD", "Singapore dollar");

    /** Money.SHP unit type with code 654. */
    public static final Sim0MQDisplayType MONEY_SHP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 654, MoneyUnit.SHP, "SHP", "Saint Helena pound");

    /** Money.SLL unit type with code 694. */
    public static final Sim0MQDisplayType MONEY_SLL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 694, MoneyUnit.SLL, "SLL", "Sierra Leonean leone");

    /** Money.SOS unit type with code 706. */
    public static final Sim0MQDisplayType MONEY_SOS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 706, MoneyUnit.SOS, "SOS", "Somali shilling");

    /** Money.SRD unit type with code 968. */
    public static final Sim0MQDisplayType MONEY_SRD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 968, MoneyUnit.SRD, "SRD", "Surinamese dollar");

    /** Money.SSP unit type with code 728. */
    public static final Sim0MQDisplayType MONEY_SSP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 728, MoneyUnit.SSP, "SSP", "South Sudanese pound");

    /** Money.STD unit type with code 678. */
    public static final Sim0MQDisplayType MONEY_STD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 678, MoneyUnit.STD, "STD", "São Tomé and Príncipe dobra");

    /** Money.SVC unit type with code 222. */
    public static final Sim0MQDisplayType MONEY_SVC =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 222, MoneyUnit.SVC, "SVC", "Salvadoran colón");

    /** Money.SYP unit type with code 760. */
    public static final Sim0MQDisplayType MONEY_SYP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 760, MoneyUnit.SYP, "SYP", "Syrian pound");

    /** Money.SZL unit type with code 748. */
    public static final Sim0MQDisplayType MONEY_SZL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 748, MoneyUnit.SZL, "SZL", "Swazi lilangeni");

    /** Money.THB unit type with code 764. */
    public static final Sim0MQDisplayType MONEY_THB =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 764, MoneyUnit.THB, "THB", "Thai baht");

    /** Money.TJS unit type with code 972. */
    public static final Sim0MQDisplayType MONEY_TJS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 972, MoneyUnit.TJS, "TJS", "Tajikistani somoni");

    /** Money.TMT unit type with code 934. */
    public static final Sim0MQDisplayType MONEY_TMT =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 934, MoneyUnit.TMT, "TMT", "Turkmenistani manat");

    /** Money.TND unit type with code 788. */
    public static final Sim0MQDisplayType MONEY_TND =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 788, MoneyUnit.TND, "TND", "Tunisian dinar");

    /** Money.TOP unit type with code 776. */
    public static final Sim0MQDisplayType MONEY_TOP =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 776, MoneyUnit.TOP, "TOP", "Tongan pa?anga");

    /** Money.TRY unit type with code 949. */
    public static final Sim0MQDisplayType MONEY_TRY =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 949, MoneyUnit.TRY, "TRY", "Turkish lira");

    /** Money.TTD unit type with code 780. */
    public static final Sim0MQDisplayType MONEY_TTD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 780, MoneyUnit.TTD, "TTD", "Trinidad and Tobago dollar");

    /** Money.TWD unit type with code 901. */
    public static final Sim0MQDisplayType MONEY_TWD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 901, MoneyUnit.TWD, "TWD", "New Taiwan dollar");

    /** Money.TZS unit type with code 834. */
    public static final Sim0MQDisplayType MONEY_TZS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 834, MoneyUnit.TZS, "TZS", "Tanzanian shilling");

    /** Money.UAH unit type with code 980. */
    public static final Sim0MQDisplayType MONEY_UAH =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 980, MoneyUnit.UAH, "UAH", "Ukrainian hryvnia");

    /** Money.UGX unit type with code 800. */
    public static final Sim0MQDisplayType MONEY_UGX =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 800, MoneyUnit.UGX, "UGX", "Ugandan shilling");

    /** Money.USD unit type with code 840. */
    public static final Sim0MQDisplayType MONEY_USD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 840, MoneyUnit.USD, "USD", "United States dollar");

    /** Money.USN unit type with code 997. */
    public static final Sim0MQDisplayType MONEY_USN = new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 997, MoneyUnit.USN, "USN",
            "United States dollar (next day) (funds code)");

    /** Money.UYI unit type with code 940. */
    public static final Sim0MQDisplayType MONEY_UYI = new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 940, MoneyUnit.UYI, "UYI",
            "Uruguay Peso en Unidades Indexadas (URUIURUI) (funds code)");

    /** Money.UYU unit type with code 858. */
    public static final Sim0MQDisplayType MONEY_UYU =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 858, MoneyUnit.UYU, "UYU", "Uruguayan peso");

    /** Money.UZS unit type with code 860. */
    public static final Sim0MQDisplayType MONEY_UZS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 860, MoneyUnit.UZS, "UZS", "Uzbekistan som");

    /** Money.VEF unit type with code 937. */
    public static final Sim0MQDisplayType MONEY_VEF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 937, MoneyUnit.VEF, "VEF", "Venezuelan bolívar");

    /** Money.VND unit type with code 704. */
    public static final Sim0MQDisplayType MONEY_VND =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 704, MoneyUnit.VND, "VND", "Vietnamese dong");

    /** Money.VUV unit type with code 548. */
    public static final Sim0MQDisplayType MONEY_VUV =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 548, MoneyUnit.VUV, "VUV", "Vanuatu vatu");

    /** Money.WST unit type with code 882. */
    public static final Sim0MQDisplayType MONEY_WST =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 882, MoneyUnit.WST, "WST", "Samoan tala");

    /** Money.XAF unit type with code 950. */
    public static final Sim0MQDisplayType MONEY_XAF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 950, MoneyUnit.XAF, "XAF", "CFA franc BEAC");

    /** Money.XAG unit type with code 961. */
    public static final Sim0MQDisplayType MONEY_XAG =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 961, MoneyUnit.XAG, "XAG", "Silver (one troy ounce)");

    /** Money.XAU unit type with code 959. */
    public static final Sim0MQDisplayType MONEY_XAU =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 959, MoneyUnit.XAU, "XAU", "Gold (one troy ounce)");

    /** Money.XBA unit type with code 955. */
    public static final Sim0MQDisplayType MONEY_XBA = new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 955, MoneyUnit.XBA, "XBA",
            "European Composite Unit (EURCO) (bond market unit)");

    /** Money.XBB unit type with code 956. */
    public static final Sim0MQDisplayType MONEY_XBB = new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 956, MoneyUnit.XBB, "XBB",
            "European Monetary Unit (E.M.U.-6) (bond market unit)");

    /** Money.XBC unit type with code 957. */
    public static final Sim0MQDisplayType MONEY_XBC = new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 957, MoneyUnit.XBC, "XBC",
            "European Unit of Account 9 (E.U.A.-9) (bond market unit)");

    /** Money.XBD unit type with code 958. */
    public static final Sim0MQDisplayType MONEY_XBD = new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 958, MoneyUnit.XBD, "XBD",
            "European Unit of Account 17 (E.U.A.-17) (bond market unit)");

    /** Money.XCD unit type with code 951. */
    public static final Sim0MQDisplayType MONEY_XCD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 951, MoneyUnit.XCD, "XCD", "East Caribbean dollar");

    /** Money.XDR unit type with code 960. */
    public static final Sim0MQDisplayType MONEY_XDR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 960, MoneyUnit.XDR, "XDR", "Special drawing rights");

    /** Money.XOF unit type with code 952. */
    public static final Sim0MQDisplayType MONEY_XOF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 952, MoneyUnit.XOF, "XOF", "CFA franc BCEAO");

    /** Money.XPD unit type with code 964. */
    public static final Sim0MQDisplayType MONEY_XPD =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 964, MoneyUnit.XPD, "XPD", "Palladium (one troy ounce)");

    /** Money.XPF unit type with code 953. */
    public static final Sim0MQDisplayType MONEY_XPF =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 953, MoneyUnit.XPF, "XPF", "CFP franc (franc Pacifique)");

    /** Money.XPT unit type with code 962. */
    public static final Sim0MQDisplayType MONEY_XPT =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 962, MoneyUnit.XPT, "XPT", "Platinum (one troy ounce)");

    /** Money.XSU unit type with code 994. */
    public static final Sim0MQDisplayType MONEY_XSU =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 994, MoneyUnit.XSU, "XSU", "SUCRE");

    /** Money.XTS unit type with code 963. */
    public static final Sim0MQDisplayType MONEY_XTS =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 963, MoneyUnit.XTS, "XTS", "Code reserved for testing purposes");

    /** Money.XUA unit type with code 965. */
    public static final Sim0MQDisplayType MONEY_XUA =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 965, MoneyUnit.XUA, "XUA", "ADB Unit of Account");

    /** Money.XX unit type with code 999. */
    public static final Sim0MQDisplayType MONEY_XXX =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 999, MoneyUnit.XXX, "XXX", "No currency");

    /** Money.YER unit type with code 886. */
    public static final Sim0MQDisplayType MONEY_YER =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 886, MoneyUnit.YER, "YER", "Yemeni rial");

    /** Money.ZAR unit type with code 710. */
    public static final Sim0MQDisplayType MONEY_ZAR =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 710, MoneyUnit.ZAR, "ZAR", "South African rand");

    /** Money.ZMW unit type with code 967. */
    public static final Sim0MQDisplayType MONEY_ZMW =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 967, MoneyUnit.ZMW, "ZMW", "Zambian kwacha");

    /** Money.ZWL unit type with code 932. */
    public static final Sim0MQDisplayType MONEY_ZWL =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 932, MoneyUnit.ZWL, "ZWL", "Zimbabwean dollar A/10");

    /** Money.XBT unit type with code 1000. */
    public static final Sim0MQDisplayType MONEY_XBT =
            new Sim0MQDisplayType(Sim0MQUnitType.MONEY, 1000, MoneyUnit.XBT, "XBT", "Bitcoin");

    /* =================================================== END TYPES ===================================================== */

    /** the unit types from number to type. */
    private static Map<Sim0MQUnitType, Map<Byte, Sim0MQDisplayType>> byteDisplayTypeMap = new HashMap<>();

    /** the unit types from class to type. */
    private static Map<Unit<?>, Sim0MQDisplayType> djunitsDisplayTypeMap = new HashMap<>();

    /** the code of the unit as a byte. */
    private final byte code;

    /** the corresponding unit data type. */
    private final Sim0MQUnitType unitType;

    /** the djunits data type. */
    private final Unit<?> djunitsType;

    /** the unit name. */
    private final String name;

    /** the unit description. */
    private final String abbreviation;

    /**
     * @param unitType the corresponding 0MQ unit type
     * @param code the byte code of the unit provided as an int
     * @param djunitsType the djunits data type
     * @param name the unit name
     * @param abbreviation the unit abbreviation
     */
    public <U extends Unit<U>> Sim0MQDisplayType(final Sim0MQUnitType unitType, final int code, final U djunitsType,
            final String name, final String abbreviation)
    {
        super();
        this.unitType = unitType;
        this.code = (byte) code;
        this.djunitsType = djunitsType;
        this.name = name;
        this.abbreviation = abbreviation;

        Map<Byte, Sim0MQDisplayType> byteMap = byteDisplayTypeMap.get(this.unitType);
        if (byteMap == null)
        {
            byteMap = new HashMap<>();
            byteDisplayTypeMap.put(this.unitType, byteMap);
        }
        byteMap.put(this.code, this);
        djunitsDisplayTypeMap.put(this.djunitsType, this);
    }

    /**
     * Return the display type belonging to the byte code.
     * @param unitType the unit type to search for
     * @param code the code to search for.
     * @return the unit type, or null if not found.
     */
    public static Sim0MQDisplayType getDisplayType(final Sim0MQUnitType unitType, final byte code)
    {
        Map<Byte, Sim0MQDisplayType> byteMap = byteDisplayTypeMap.get(unitType);
        return byteMap == null ? null : byteMap.get(code);
    }

    /**
     * Return the display type belonging to the byte code.
     * @param unitTypeCode the unit type to search for
     * @param code the code to search for.
     * @return the unit type, or null if not found.
     */
    public static Sim0MQDisplayType getDisplayType(final byte unitTypeCode, final byte code)
    {
        Sim0MQUnitType unitType = Sim0MQUnitType.getUnitType(unitTypeCode);
        Map<Byte, Sim0MQDisplayType> byteMap = byteDisplayTypeMap.get(unitType);
        return byteMap == null ? null : byteMap.get(code);
    }

    /**
     * Return the unit belonging to the byte code.
     * @param unitTypeCode the unit type to search for
     * @param code the code to search for.
     * @return the unit type, or null if not found.
     */
    public static Unit<?> getUnit(final byte unitTypeCode, final byte code)
    {
        Sim0MQUnitType unitType = Sim0MQUnitType.getUnitType(unitTypeCode);
        Map<Byte, Sim0MQDisplayType> byteMap = byteDisplayTypeMap.get(unitType);
        return byteMap == null ? null : byteMap.get(code) == null ? null : byteMap.get(code).djunitsType;
    }

    /**
     * Return the unit belonging to the byte code.
     * @param unitType the unit type to search for
     * @param code the code to search for.
     * @return the unit type, or null if not found.
     */
    public static Unit<?> getUnit(final Sim0MQUnitType unitType, final byte code)
    {
        Map<Byte, Sim0MQDisplayType> byteMap = byteDisplayTypeMap.get(unitType);
        return byteMap == null ? null : byteMap.get(code) == null ? null : byteMap.get(code).djunitsType;
    }

    /**
     * Return the unit type belonging to the unit class.
     * @param unit the unit to search for.
     * @return the unit type, or null if not found.
     */
    public static <U extends Unit<U>> Sim0MQDisplayType getDisplayType(final U unit)
    {
        return djunitsDisplayTypeMap.get(unit);
    }

    /**
     * Return the byte code belonging to the unit class.
     * @param unit the unit to search for.
     * @return the unit type, or null if not found.
     */
    public static <U extends Unit<U>> byte getUnitCode(final U unit)
    {
        Sim0MQUnitType type = Sim0MQUnitType.getUnitType(unit);
        Sim0MQDisplayType displayType = type == null ? null : getDisplayType(unit);
        return displayType == null ? null : displayType.code;
    }

    /**
     * @return code
     */
    public final byte getCode()
    {
        return this.code;
    }

    /**
     * @return djunitsType
     */
    public final Unit<?> getDjunitsType()
    {
        return this.djunitsType;
    }

    /**
     * @return name
     */
    public final String getName()
    {
        return this.name;
    }

    /**
     * @return abbreviation
     */
    public final String getAbbreviation()
    {
        return this.abbreviation;
    }

}
