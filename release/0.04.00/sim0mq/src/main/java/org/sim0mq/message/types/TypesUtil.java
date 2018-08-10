package org.sim0mq.message.types;

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
import org.djunits.unit.MoneyPerAreaUnit;
import org.djunits.unit.MoneyPerDurationUnit;
import org.djunits.unit.MoneyPerEnergyUnit;
import org.djunits.unit.MoneyPerLengthUnit;
import org.djunits.unit.MoneyPerMassUnit;
import org.djunits.unit.MoneyPerVolumeUnit;
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
import org.djunits.value.vdouble.scalar.AbsoluteTemperature;
import org.djunits.value.vdouble.scalar.AbstractDoubleScalar;
import org.djunits.value.vdouble.scalar.Acceleration;
import org.djunits.value.vdouble.scalar.Angle;
import org.djunits.value.vdouble.scalar.AngleSolid;
import org.djunits.value.vdouble.scalar.Area;
import org.djunits.value.vdouble.scalar.Density;
import org.djunits.value.vdouble.scalar.Dimensionless;
import org.djunits.value.vdouble.scalar.Direction;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.ElectricalCharge;
import org.djunits.value.vdouble.scalar.ElectricalCurrent;
import org.djunits.value.vdouble.scalar.ElectricalPotential;
import org.djunits.value.vdouble.scalar.ElectricalResistance;
import org.djunits.value.vdouble.scalar.Energy;
import org.djunits.value.vdouble.scalar.FlowMass;
import org.djunits.value.vdouble.scalar.FlowVolume;
import org.djunits.value.vdouble.scalar.Force;
import org.djunits.value.vdouble.scalar.Frequency;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.LinearDensity;
import org.djunits.value.vdouble.scalar.Mass;
import org.djunits.value.vdouble.scalar.Money;
import org.djunits.value.vdouble.scalar.MoneyPerArea;
import org.djunits.value.vdouble.scalar.MoneyPerDuration;
import org.djunits.value.vdouble.scalar.MoneyPerEnergy;
import org.djunits.value.vdouble.scalar.MoneyPerLength;
import org.djunits.value.vdouble.scalar.MoneyPerMass;
import org.djunits.value.vdouble.scalar.MoneyPerVolume;
import org.djunits.value.vdouble.scalar.Position;
import org.djunits.value.vdouble.scalar.Power;
import org.djunits.value.vdouble.scalar.Pressure;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.scalar.Temperature;
import org.djunits.value.vdouble.scalar.Time;
import org.djunits.value.vdouble.scalar.Torque;
import org.djunits.value.vdouble.scalar.Volume;
import org.djunits.value.vfloat.scalar.AbstractFloatScalar;
import org.djunits.value.vfloat.scalar.FloatAbsoluteTemperature;
import org.djunits.value.vfloat.scalar.FloatAcceleration;
import org.djunits.value.vfloat.scalar.FloatAngle;
import org.djunits.value.vfloat.scalar.FloatAngleSolid;
import org.djunits.value.vfloat.scalar.FloatArea;
import org.djunits.value.vfloat.scalar.FloatDensity;
import org.djunits.value.vfloat.scalar.FloatDimensionless;
import org.djunits.value.vfloat.scalar.FloatDirection;
import org.djunits.value.vfloat.scalar.FloatDuration;
import org.djunits.value.vfloat.scalar.FloatElectricalCharge;
import org.djunits.value.vfloat.scalar.FloatElectricalCurrent;
import org.djunits.value.vfloat.scalar.FloatElectricalPotential;
import org.djunits.value.vfloat.scalar.FloatElectricalResistance;
import org.djunits.value.vfloat.scalar.FloatEnergy;
import org.djunits.value.vfloat.scalar.FloatFlowMass;
import org.djunits.value.vfloat.scalar.FloatFlowVolume;
import org.djunits.value.vfloat.scalar.FloatForce;
import org.djunits.value.vfloat.scalar.FloatFrequency;
import org.djunits.value.vfloat.scalar.FloatLength;
import org.djunits.value.vfloat.scalar.FloatLinearDensity;
import org.djunits.value.vfloat.scalar.FloatMass;
import org.djunits.value.vfloat.scalar.FloatMoney;
import org.djunits.value.vfloat.scalar.FloatMoneyPerArea;
import org.djunits.value.vfloat.scalar.FloatMoneyPerDuration;
import org.djunits.value.vfloat.scalar.FloatMoneyPerEnergy;
import org.djunits.value.vfloat.scalar.FloatMoneyPerLength;
import org.djunits.value.vfloat.scalar.FloatMoneyPerMass;
import org.djunits.value.vfloat.scalar.FloatMoneyPerVolume;
import org.djunits.value.vfloat.scalar.FloatPosition;
import org.djunits.value.vfloat.scalar.FloatPower;
import org.djunits.value.vfloat.scalar.FloatPressure;
import org.djunits.value.vfloat.scalar.FloatSpeed;
import org.djunits.value.vfloat.scalar.FloatTemperature;
import org.djunits.value.vfloat.scalar.FloatTime;
import org.djunits.value.vfloat.scalar.FloatTorque;
import org.djunits.value.vfloat.scalar.FloatVolume;
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

    /**
     * Instantiate a FloatScalar of the right type.
     * @param unit the unit to use
     * @param si the value in SI units
     * @return the instantiated FloatScalar
     * @param <U> the unit
     * @param <S> the scalar type
     */
    @SuppressWarnings({ "unchecked", "checkstyle:needbraces" })
    public static <U extends Unit<U>, S extends AbstractFloatScalar<U, S>> S instantiateFloatScalar(final U unit,
            final float si)
    {
        if (unit instanceof DimensionlessUnit)
            return (S) new FloatDimensionless(FloatDimensionless.createSI(si).getInUnit((DimensionlessUnit) unit),
                    (DimensionlessUnit) unit);
        else if (unit instanceof AccelerationUnit)
            return (S) new FloatAcceleration(FloatAcceleration.createSI(si).getInUnit((AccelerationUnit) unit),
                    (AccelerationUnit) unit);
        else if (unit instanceof AngleSolidUnit)
            return (S) new FloatAngleSolid(FloatAngleSolid.createSI(si).getInUnit((AngleSolidUnit) unit),
                    (AngleSolidUnit) unit);
        else if (unit instanceof AngleUnit)
            return (S) new FloatAngle(FloatAngle.createSI(si).getInUnit((AngleUnit) unit), (AngleUnit) unit);
        else if (unit instanceof DirectionUnit)
            return (S) new FloatDirection(FloatDirection.createSI(si).getInUnit((DirectionUnit) unit), (DirectionUnit) unit);
        else if (unit instanceof AreaUnit)
            return (S) new FloatArea(FloatArea.createSI(si).getInUnit((AreaUnit) unit), (AreaUnit) unit);
        else if (unit instanceof DensityUnit)
            return (S) new FloatDensity(FloatDensity.createSI(si).getInUnit((DensityUnit) unit), (DensityUnit) unit);
        else if (unit instanceof ElectricalChargeUnit)
            return (S) new FloatElectricalCharge(FloatElectricalCharge.createSI(si).getInUnit((ElectricalChargeUnit) unit),
                    (ElectricalChargeUnit) unit);
        else if (unit instanceof ElectricalCurrentUnit)
            return (S) new FloatElectricalCurrent(FloatElectricalCurrent.createSI(si).getInUnit((ElectricalCurrentUnit) unit),
                    (ElectricalCurrentUnit) unit);
        else if (unit instanceof ElectricalPotentialUnit)
            return (S) new FloatElectricalPotential(
                    FloatElectricalPotential.createSI(si).getInUnit((ElectricalPotentialUnit) unit),
                    (ElectricalPotentialUnit) unit);
        else if (unit instanceof ElectricalResistanceUnit)
            return (S) new FloatElectricalResistance(
                    FloatElectricalResistance.createSI(si).getInUnit((ElectricalResistanceUnit) unit),
                    (ElectricalResistanceUnit) unit);
        else if (unit instanceof EnergyUnit)
            return (S) new FloatEnergy(FloatEnergy.createSI(si).getInUnit((EnergyUnit) unit), (EnergyUnit) unit);
        else if (unit instanceof FlowMassUnit)
            return (S) new FloatFlowMass(FloatFlowMass.createSI(si).getInUnit((FlowMassUnit) unit), (FlowMassUnit) unit);
        else if (unit instanceof FlowVolumeUnit)
            return (S) new FloatFlowVolume(FloatFlowVolume.createSI(si).getInUnit((FlowVolumeUnit) unit),
                    (FlowVolumeUnit) unit);
        else if (unit instanceof ForceUnit)
            return (S) new FloatForce(FloatForce.createSI(si).getInUnit((ForceUnit) unit), (ForceUnit) unit);
        else if (unit instanceof FrequencyUnit)
            return (S) new FloatFrequency(FloatFrequency.createSI(si).getInUnit((FrequencyUnit) unit), (FrequencyUnit) unit);
        else if (unit instanceof LengthUnit)
            return (S) new FloatLength(FloatLength.createSI(si).getInUnit((LengthUnit) unit), (LengthUnit) unit);
        else if (unit instanceof PositionUnit)
            return (S) new FloatPosition(FloatPosition.createSI(si).getInUnit((PositionUnit) unit), (PositionUnit) unit);
        else if (unit instanceof LinearDensityUnit)
            return (S) new FloatLinearDensity(FloatLinearDensity.createSI(si).getInUnit((LinearDensityUnit) unit),
                    (LinearDensityUnit) unit);
        else if (unit instanceof MassUnit)
            return (S) new FloatMass(FloatMass.createSI(si).getInUnit((MassUnit) unit), (MassUnit) unit);
        else if (unit instanceof PowerUnit)
            return (S) new FloatPower(FloatPower.createSI(si).getInUnit((PowerUnit) unit), (PowerUnit) unit);
        else if (unit instanceof PressureUnit)
            return (S) new FloatPressure(FloatPressure.createSI(si).getInUnit((PressureUnit) unit), (PressureUnit) unit);
        else if (unit instanceof SpeedUnit)
            return (S) new FloatSpeed(FloatSpeed.createSI(si).getInUnit((SpeedUnit) unit), (SpeedUnit) unit);
        else if (unit instanceof TemperatureUnit)
            return (S) new FloatTemperature(FloatTemperature.createSI(si).getInUnit((TemperatureUnit) unit),
                    (TemperatureUnit) unit);
        else if (unit instanceof AbsoluteTemperatureUnit)
            return (S) new FloatAbsoluteTemperature(
                    FloatAbsoluteTemperature.createSI(si).getInUnit((AbsoluteTemperatureUnit) unit),
                    (AbsoluteTemperatureUnit) unit);
        else if (unit instanceof DurationUnit)
            return (S) new FloatDuration(FloatDuration.createSI(si).getInUnit((DurationUnit) unit), (DurationUnit) unit);
        else if (unit instanceof TimeUnit)
            return (S) new FloatTime(FloatTime.createSI(si).getInUnit((TimeUnit) unit), (TimeUnit) unit);
        else if (unit instanceof TorqueUnit)
            return (S) new FloatTorque(FloatTorque.createSI(si).getInUnit((TorqueUnit) unit), (TorqueUnit) unit);
        else if (unit instanceof VolumeUnit)
            return (S) new FloatVolume(FloatVolume.createSI(si).getInUnit((VolumeUnit) unit), (VolumeUnit) unit);
        else if (unit instanceof MoneyUnit)
            return (S) new FloatMoney(si, (MoneyUnit) unit);
        else if (unit instanceof MoneyPerAreaUnit)
            return (S) new FloatMoneyPerArea(si / ((MoneyPerAreaUnit) unit).getScaleFactor(), (MoneyPerAreaUnit) unit);
        else if (unit instanceof MoneyPerEnergyUnit)
            return (S) new FloatMoneyPerEnergy(si / ((MoneyPerEnergyUnit) unit).getScaleFactor(), (MoneyPerEnergyUnit) unit);
        else if (unit instanceof MoneyPerLengthUnit)
            return (S) new FloatMoneyPerLength(si / ((MoneyPerLengthUnit) unit).getScaleFactor(), (MoneyPerLengthUnit) unit);
        else if (unit instanceof MoneyPerMassUnit)
            return (S) new FloatMoneyPerMass(si / ((MoneyPerMassUnit) unit).getScaleFactor(), (MoneyPerMassUnit) unit);
        else if (unit instanceof MoneyPerDurationUnit)
            return (S) new FloatMoneyPerDuration(si / ((MoneyPerDurationUnit) unit).getScaleFactor(),
                    (MoneyPerDurationUnit) unit);
        else if (unit instanceof MoneyPerVolumeUnit)
            return (S) new FloatMoneyPerVolume(si / ((MoneyPerVolumeUnit) unit).getScaleFactor(), (MoneyPerVolumeUnit) unit);
        else
            throw new RuntimeException(
                    new Sim0MQException("Cannot instantiate AbstractFloatScalar of unit " + unit.toString()));
    }

    /**
     * Instantiate a DoubleScalar of the right type.
     * @param unit the unit to use
     * @param si the value in SI units
     * @return the instantiated FloatScalar
     * @param <U> the unit
     * @param <S> the scalar type
     */
    @SuppressWarnings({ "unchecked", "checkstyle:needbraces" })
    public static <U extends Unit<U>, S extends AbstractDoubleScalar<U, S>> S instantiateDoubleScalar(final U unit,
            final double si)
    {
        if (unit instanceof DimensionlessUnit)
            return (S) new Dimensionless(Dimensionless.createSI(si).getInUnit((DimensionlessUnit) unit),
                    (DimensionlessUnit) unit);
        else if (unit instanceof AccelerationUnit)
            return (S) new Acceleration(Acceleration.createSI(si).getInUnit((AccelerationUnit) unit), (AccelerationUnit) unit);
        else if (unit instanceof AngleSolidUnit)
            return (S) new AngleSolid(AngleSolid.createSI(si).getInUnit((AngleSolidUnit) unit), (AngleSolidUnit) unit);
        else if (unit instanceof AngleUnit)
            return (S) new Angle(Angle.createSI(si).getInUnit((AngleUnit) unit), (AngleUnit) unit);
        else if (unit instanceof DirectionUnit)
            return (S) new Direction(Direction.createSI(si).getInUnit((DirectionUnit) unit), (DirectionUnit) unit);
        else if (unit instanceof AreaUnit)
            return (S) new Area(Area.createSI(si).getInUnit((AreaUnit) unit), (AreaUnit) unit);
        else if (unit instanceof DensityUnit)
            return (S) new Density(Density.createSI(si).getInUnit((DensityUnit) unit), (DensityUnit) unit);
        else if (unit instanceof ElectricalChargeUnit)
            return (S) new ElectricalCharge(ElectricalCharge.createSI(si).getInUnit((ElectricalChargeUnit) unit),
                    (ElectricalChargeUnit) unit);
        else if (unit instanceof ElectricalCurrentUnit)
            return (S) new ElectricalCurrent(ElectricalCurrent.createSI(si).getInUnit((ElectricalCurrentUnit) unit),
                    (ElectricalCurrentUnit) unit);
        else if (unit instanceof ElectricalPotentialUnit)
            return (S) new ElectricalPotential(ElectricalPotential.createSI(si).getInUnit((ElectricalPotentialUnit) unit),
                    (ElectricalPotentialUnit) unit);
        else if (unit instanceof ElectricalResistanceUnit)
            return (S) new ElectricalResistance(ElectricalResistance.createSI(si).getInUnit((ElectricalResistanceUnit) unit),
                    (ElectricalResistanceUnit) unit);
        else if (unit instanceof EnergyUnit)
            return (S) new Energy(Energy.createSI(si).getInUnit((EnergyUnit) unit), (EnergyUnit) unit);
        else if (unit instanceof FlowMassUnit)
            return (S) new FlowMass(FlowMass.createSI(si).getInUnit((FlowMassUnit) unit), (FlowMassUnit) unit);
        else if (unit instanceof FlowVolumeUnit)
            return (S) new FlowVolume(FlowVolume.createSI(si).getInUnit((FlowVolumeUnit) unit), (FlowVolumeUnit) unit);
        else if (unit instanceof ForceUnit)
            return (S) new Force(Force.createSI(si).getInUnit((ForceUnit) unit), (ForceUnit) unit);
        else if (unit instanceof FrequencyUnit)
            return (S) new Frequency(Frequency.createSI(si).getInUnit((FrequencyUnit) unit), (FrequencyUnit) unit);
        else if (unit instanceof LengthUnit)
            return (S) new Length(Length.createSI(si).getInUnit((LengthUnit) unit), (LengthUnit) unit);
        else if (unit instanceof PositionUnit)
            return (S) new Position(Position.createSI(si).getInUnit((PositionUnit) unit), (PositionUnit) unit);
        else if (unit instanceof LinearDensityUnit)
            return (S) new LinearDensity(LinearDensity.createSI(si).getInUnit((LinearDensityUnit) unit),
                    (LinearDensityUnit) unit);
        else if (unit instanceof MassUnit)
            return (S) new Mass(Mass.createSI(si).getInUnit((MassUnit) unit), (MassUnit) unit);
        else if (unit instanceof PowerUnit)
            return (S) new Power(Power.createSI(si).getInUnit((PowerUnit) unit), (PowerUnit) unit);
        else if (unit instanceof PressureUnit)
            return (S) new Pressure(Pressure.createSI(si).getInUnit((PressureUnit) unit), (PressureUnit) unit);
        else if (unit instanceof SpeedUnit)
            return (S) new Speed(Speed.createSI(si).getInUnit((SpeedUnit) unit), (SpeedUnit) unit);
        else if (unit instanceof TemperatureUnit)
            return (S) new Temperature(Temperature.createSI(si).getInUnit((TemperatureUnit) unit), (TemperatureUnit) unit);
        else if (unit instanceof AbsoluteTemperatureUnit)
            return (S) new AbsoluteTemperature(AbsoluteTemperature.createSI(si).getInUnit((AbsoluteTemperatureUnit) unit),
                    (AbsoluteTemperatureUnit) unit);
        else if (unit instanceof DurationUnit)
            return (S) new Duration(Duration.createSI(si).getInUnit((DurationUnit) unit), (DurationUnit) unit);
        else if (unit instanceof TimeUnit)
            return (S) new Time(Time.createSI(si).getInUnit((TimeUnit) unit), (TimeUnit) unit);
        else if (unit instanceof TorqueUnit)
            return (S) new Torque(Torque.createSI(si).getInUnit((TorqueUnit) unit), (TorqueUnit) unit);
        else if (unit instanceof VolumeUnit)
            return (S) new Volume(Volume.createSI(si).getInUnit((VolumeUnit) unit), (VolumeUnit) unit);
        else if (unit instanceof MoneyUnit)
            return (S) new Money(si, (MoneyUnit) unit);
        else if (unit instanceof MoneyPerAreaUnit)
            return (S) new MoneyPerArea(si / ((MoneyPerAreaUnit) unit).getScaleFactor(), (MoneyPerAreaUnit) unit);
        else if (unit instanceof MoneyPerEnergyUnit)
            return (S) new MoneyPerEnergy(si / ((MoneyPerEnergyUnit) unit).getScaleFactor(), (MoneyPerEnergyUnit) unit);
        else if (unit instanceof MoneyPerLengthUnit)
            return (S) new MoneyPerLength(si / ((MoneyPerLengthUnit) unit).getScaleFactor(), (MoneyPerLengthUnit) unit);
        else if (unit instanceof MoneyPerMassUnit)
            return (S) new MoneyPerMass(si / ((MoneyPerMassUnit) unit).getScaleFactor(), (MoneyPerMassUnit) unit);
        else if (unit instanceof MoneyPerDurationUnit)
            return (S) new MoneyPerDuration(si / ((MoneyPerDurationUnit) unit).getScaleFactor(), (MoneyPerDurationUnit) unit);
        else if (unit instanceof MoneyPerVolumeUnit)
            return (S) new MoneyPerVolume(si / ((MoneyPerVolumeUnit) unit).getScaleFactor(), (MoneyPerVolumeUnit) unit);
        else
            throw new RuntimeException(
                    new Sim0MQException("Cannot instantiate AbstractFloatScalar of unit " + unit.toString()));

    }

}
