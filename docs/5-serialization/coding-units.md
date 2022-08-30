# Coding of units

Units are coded with one byte indicating the unit type, and one byte indicating the display type of the unit. The SI unit or standard unit always has display type 0. The display types for each unit type are provided on the [following page](../display-types). The unit types as defined currently are:

| code | unit name | unit description | default (SI) unit | Display types |
| ------- | ------------- | -------------------- | -------------------- | ------------------ |
| 0 | Dimensionless | Unit without a dimension | | [Display types](../display-types#0-dimensionless)  | 
| 1 | Acceleration | Acceleration | m/s<sup>2</sup>  | [Display types](../display-types#1-acceleration)  | 
| 2 | SolidAngle | Solid angle (steradian) | sr  | [Display types](../display-types#2-anglesolid)  | 
| 3 | Angle | Angle (relative) | rad  | [Display types](../display-types#3-angle)  | 
| 4 | Direction | Angle (absolute) | rad  | [Display types](../display-types#4-direction)  | 
| 5 | Area | Area | m<sup>2</sup>  | [Display types](../display-types#5-area)  | 
| 6 | Density | Density based on mass and length | kg/m<sup>3</sup>  | [Display types](../display-types#6-density)  | 
| 7 | ElectricalCharge | Electrical charge (Coulomb) | s.A  | [Display types](../display-types#7-electricalcharge)  | 
| 8 | ElectricalCurrent | Electrical current (Ampere) | A  | [Display types](../display-types#8-electricalcurrent)  | 
| 9 | ElectricalPotential | Electrical potential (Volt) | kg.m<sup>2</sup>/s<sup>3</sup>.A  | [Display types](../display-types#9-electricalpotential)  | 
| 10 | ElectricalResistance | Electrical resistance (Ohm) | kg.m<sup>2</sup>/s<sup>3</sup>.A<sup>2</sup>  | [Display types](../display-types#10-electricalresistance)  | 
| 11 | Energy | Energy (Joule) | kg.m<sup>2</sup>/s<sup>2</sup>  | [Display types](../display-types#11-energy)  | 
| 12 | FlowMass | Mass flow rate | kg/s  | [Display types](../display-types#12-flowmass)  | 
| 13 | FlowVolume | Volume flow rate | m<sup>3</sup>/s  | [Display types](../display-types#13-flowvolume)  | 
| 14 | Force | Force (Newton) | kg.m/s<sup>2</sup>  | [Display types](../display-types#14-force)  | 
| 15 | Frequency | Frequency (Hz) | 1/s  | [Display types](../display-types#15-frequency)  | 
| 16 | Length | Length (relative) | m  | [Display types](../display-types#16-length)  | 
| 17 | Position | Length (absolute) | m  | [Display types](../display-types#17-position)  | 
| 18 | LinearDensity | Linear density | 1/m  | [Display types](../display-types#18-lineardensity)  | 
| 19 | Mass | Mass | kg  | [Display types](../display-types#19-mass)  | 
| 20 | Power | Power (Watt) | kg.m<sup>2</sup>/s<sup>3</sup>  | [Display types](../display-types#20-power)  | 
| 21 | Pressure | Pressure (Pascal) | kg/m.s<sup>2</sup>  | [Display types](../display-types#21-pressure)  | 
| 22 | Speed | Speed | m/s  | [Display types](../display-types#22-speed)  | 
| 23 | Temperature | Temperature (relative) | K  | [Display types](../display-types#23-temperature)  | 
| 24 | AbsoluteTemperature | Temperature (absolute) | K  | [Display types](../display-types#24-absolutetemperature)  | 
| 25 | Duration | Time (relative) | s  | [Display types](../display-types#25-duration)  | 
| 26 | Time | Time (absolute) | s  | [Display types](../display-types#26-time)  | 
| 27 | Torque | Torque (Newton-meter) | kg.m<sup>2</sup>/s<sup>2</sup>  | [Display types](../display-types#27-torque)  | 
| 28 | Volume | Volume | m<sup>3</sup>  | [Display types](../display-types#28-volume)  | 
| 29 | Absorbed dose | Absorbed dose (gray) | m<sup>2</sup>/s<sup>2</sup>  | [Display types](../display-types#29-absorbeddose)  | 
| 30 | Amount of substance | Amount of substance (mole) | mol  | [Display types](../display-types#30-amountofsubstance)  | 
| 31 | Catalytic activity | Catalytic activity (mole/s) | mol/s  | [Display types](../display-types#31-catalyticactivity)  | 
| 32 | Electrical capacitance | Electrical capacitance (farad) | s<sup>4</sup>.A<sup>2</sup>/kg.m<sup>2</sup>  | [Display types](../display-types#32-electricalcapacitance)  | 
| 33 | Electrical conductance | Electrical conductance (siemens) | s<sup>3</sup>.A<sup>2</sup>/kg.m<sup>2</sup>  | [Display types](../display-types#33-electricalconductance)  | 
| 34 | Electrical inductance | Electrical inductance (henry) | kg.m<sup>2</sup>/s<sup>2</sup>.A<sup>2</sup>  | [Display types](../display-types#34-electricalinductance)  | 
| 35 | Equivalent dose | Equivalent dose (sievert) | m<sup>2</sup>/s<sup>2</sup>  | [Display types](../display-types#35-equivalentdose)  | 
| 36 | Illuminance | Illuminance (lux) | sr.cd/m<sup>2</sup>  | [Display types](../display-types#36-illuminance)  | 
| 37 | Luminous flux | Luminous flux (lumen) | sr.cd  | [Display types](../display-types#37-luminousflux)  | 
| 38 | Luminous intensity | Luminous intensity (candela) | cd  | [Display types](../display-types#38-luminousintensity)  | 
| 39 | Magnetic flux density | Magnetic flux density (tesla) | kg/s<sup>2</sup>.A  | [Display types](../display-types#39-magneticfluxdensity)  | 
| 40 | Magnetic flux | Magnetic flux (weber) | kg.m<sup>2</sup>/s<sup>2</sup>.A  | [Display types](../display-types#40-magneticflux)  | 
| 41 | Radioactivity | Radioactivity (becquerel) | 1/s  | [Display types](../display-types#41-radioactivity)  | 
| 42 | Angular acceleration | Change in angular velocity per second | rad/s<sup>2</sup> | [Display types](../display-types#42-angularacceleration) |
| 43 | Angular velocity | Change in angular velocity per second | rad/s | [Display types](../display-types#43-angularvelocity) |
| 44 | Momentum | Linear momentum, translational momentum | kg.m/s | [Display types](../display-types#44-momentum) |

Some of the unit types have a relative and an absolute variant. Relative scalars can be added to or subtracted from relative and absolute scalars; absolute scalars cannot be added, but can be subtracted, resulting in a relative scalar. As an example, one cannot add two times (3-1-2017, 5 o'clock + 3-1-2017, 3 o'clock = ??), but these values can be subtracted (3-1-2017, 5 o'clock – 3-1-2017, 3 o'clock = 2 hours). Absolute plus relative yields e.g., 3-1-2017, 17:00 + 2 hours = 3-1-2017, 19:00. Relative values can of course be added/subtracted: 2 hours + 30 minutes = 2.5 hours. See [https://djunits.org](https://djunits.org) for more information.
