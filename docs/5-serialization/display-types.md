# Display types for units

## Display units

All units are internally stored and transmitted using the SI unit (for relative units) or the BASE unit (for absolute units). They can, however, be displayed in a certain unit that we want to save in the serialization. An example is when we store the value of 248 kilometer and we want the deserialized field to also display as 248 kilometer, and not as 248000 meter. The following display unit categoriess have been defined:

<table><tr><td><ul>
<li><a href="#0-dimensionless">0. Dimensionless</a></li>
<li><a href="#1-acceleration">1. Acceleration</a></li>
<li><a href="#2-solidangle">2. SolidAngle</a></li>
<li><a href="#3-angle">3. Angle</a></li>
<li><a href="#4-direction">4. Direction</a></li>
<li><a href="#5-area">5. Area</a></li>
<li><a href="#6-density">6. Density</a></li>
<li><a href="#7-electricalcharge">7. ElectricalCharge</a></li>
<li><a href="#8-electricalcurrent">8. ElectricalCurrent</a></li>
<li><a href="#9-electricalpotential">9. ElectricalPotential</a></li>
<li><a href="#10-electricalresistance">10. ElectricalResistance</a></li>
<li><a href="#11-energy">11. Energy</a></li>
<li><a href="#12-flowmass">12. FlowMass</a></li>
<li><a href="#13-flowvolume">13. FlowVolume</a></li>
<li><a href="#14-force">14. Force</a></li>
<li><a href="#15-frequency">15. Frequency</a></li>
<li><a href="#16-length">16. Length</a></li>
<li><a href="#17-position">17. Position</a></li>
<li><a href="#18-lineardensity">18. LinearDensity</a></li>
<li><a href="#19-mass">19. Mass</a></li>
<li><a href="#20-power">20. Power</a></li>
<li><a href="#21-pressure">21. Pressure</a></li>
<li><a href="#22-speed">22. Speed</a></li>
</ul></td><td><ul>
<li><a href="#23-temperature">23. Temperature</a></li>
<li><a href="#24-absolutetemperature">24. AbsoluteTemperature</a></li>
<li><a href="#25-duration">25. Duration</a></li>
<li><a href="#26-time">26. Time</a></li>
<li><a href="#27-torque">27. Torque</a></li>
<li><a href="#28-volume">28. Volume</a></li>
<li><a href="#29-absorbeddose">29. AbsorbedDose</a></li>
<li><a href="#30-amountofsubstance">30. AmountOfSubstance</a></li>
<li><a href="#31-catalyticactivity">31. CatalyticActivity</a></li>
<li><a href="#32-electricalcapacitance">32. ElectricalCapacitance</a></li>
<li><a href="#33-electricalconductance">33. ElectricalConductance</a></li>
<li><a href="#34-electricalinductance">34. ElectricalInductance</a></li>
<li><a href="#35-equivalentdose">35. EquivalentDose</a></li>
<li><a href="#36-illuminance">36. Illuminance</a></li>
<li><a href="#37-luminousflux">37. LuminousFlux</a></li>
<li><a href="#38-luminousintensity">38. LuminousIntensity</a></li>
<li><a href="#39-magneticfluxdensity">39. MagneticFluxDensity</a></li>
<li><a href="#40-magneticflux">40. MagneticFlux</a></li>
<li><a href="#41-radioactivity">41. RadioActivity</a></li>
<li><a href="#42-angularacceleration">42. AngularAcceleration</a></li>
<li><a href="#43-angularvelocity">43. AngularVelocity</a></li>
<li><a href="#44-momentum">44. Momentum</a></li>
    
</ul></td></tr></table>

## 0. Dimensionless <a id="0-dimensionless"></a>

The Dimensionless unit does not have any display codes, except the default one, indicated with code number 0.


## 1. Acceleration <a id="1-acceleration"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | METER_PER_SECOND_2 (SI) | m/s<sup>2</sup> | 
| 1 | KM_PER_HOUR_2 | km/h<sup>2</sup> | 
| 2 | INCH_PER_SECOND_2 | in/s<sup>2</sup> | 
| 3 | FOOT_PER_SECOND_2 | ft/s<sup>2</sup> | 
| 4 | MILE_PER_HOUR_2 | mi/h<sup>2</sup> | 
| 5 | MILE_PER_HOUR_PER_SECOND | mi/h/s | 
| 6 | KNOT_PER_SECOND | kt/s | 
| 7 | GAL | gal | 
| 8 | STANDARD_GRAVITY | g | 
| 9 | MILE_PER_SECOND_2 | mi/s<sup>2</sup> | 


## 2. SolidAngle <a id="2-solidangle"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | STERADIAN (SI) | sr |
| 1 | SQUARE_DEGREE | sq.deg |


## 3. Angle <a id="3-angle"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | RADIAN (SI) | rad |
| 1 | ARCMINUTE | arcmin / ' |
| 2 | ARCSECOND | arcsec / " |
| 3 | CENTESIMAL_ARCMINUTE | centesimal_arcmin |
| 4 | CENTESIMAL_ARCSECOND | centesimal_arcsec |
| 5 | DEGREE | deg |
| 6 | GRAD | grad |
| 7 | PERCENT | % |


## 4. Direction <a id="4-direction"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | NORTH_RADIAN (BASE) | rad(N) |
| 1 | NORTH_DEGREE | deg(N) |
| 2 | EAST_RADIAN | rad(E) |
| 3 | EAST_DEGREE | deg(E) |
 

## 5. Area <a id="5-area"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | SQUARE_METER (SI) | m<sup>2</sup> |
| 1 | SQUARE_ATTOMETER | am<sup>2</sup> |
| 2 | SQUARE_FEMTOMETER | fm<sup>2</sup> |
| 3 | SQUARE_PICOMETER | pm<sup>2</sup> |
| 4 | SQUARE_NANOMETER | nm<sup>2</sup> |
| 5 | SQUARE_MICROMETER | &mu;m<sup>2</sup> |
| 6 | SQUARE_MILLIMETER | mm<sup>2</sup> |
| 7 | SQUARE_CENTIMETER | cm<sup>2</sup> |
| 8 | SQUARE_DECIMETER | dm<sup>2</sup> |
| 9 | SQUARE_DEKAMETER | dam<sup>2</sup> |
| 10 | SQUARE_HECTOMETER | hm<sup>2</sup> |
| 11 | SQUARE_KILOMETER | km<sup>2</sup> |
| 12 | SQUARE_MEGAMETER | Mm<sup>2</sup> |
| 13 | SQUARE_INCH | in<sup>2</sup> |
| 14 | SQUARE_FOOT | ft<sup>2</sup> |
| 15 | SQUARE_YARD | yd<sup>2</sup> |
| 16 | SQUARE_MILE | mi<sup>2</sup> |
| 17 | SQUARE_NAUTICAL_MILE | NM<sup>2</sup> |
| 18 | ACRE | acre |
| 19 | ARE | a |
| 20 | CENTIARE | ca |
| 21 | HECTARE | ha |


## 6. Density <a id="6-density"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | KG_PER_METER_3 (SI) | kg/m<sup>3</sup> |
| 1 | GRAM_PER_CENTIMETER_3 | g/cm<sup>3</sup> |


## 7. ElectricalCharge <a id="7-electricalcharge"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | COULOMB | C |
| 1 | PICOCOULOMB | pC |
| 2 | NANOCOULOMB | nC |
| 3 | MICROCOULOMB | &mu;C |
| 4 | MILLICOULOMB | mC |
| 5 | ABCOULOMB | abC |
| 6 | ATOMIC_UNIT | au |
| 7 | EMU | emu |
| 8 | ESU | esu |
| 9 | FARADAY | F |
| 10 | FRANKLIN | Fr |
| 11 | STATCOULOMB | statC |
| 12 | MILLIAMPERE_HOUR | mAh |
| 13 | AMPERE_HOUR | Ah |
| 14 | KILOAMPERE_HOUR | kAh |
| 15 | MEGAAMPERE_HOUR | MAh |
| 16 | MILLIAMPERE_SECOND | mAs |


## 8. ElectricalCurrent <a id="8-electricalcurrent"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | AMPERE (SI) | A |
| 1 | NANOAMPERE | nA |
| 2 | MICROAMPERE | &mu;A |
| 3 | MILLIAMPERE | mA |
| 4 | KILOAMPERE | kA |
| 5 | MEGAAMPERE | MA |
| 6 | ABAMPERE | abA |
| 7 | STATAMPERE | statA |

## 9. ElectricalPotential <a id="9-electricalpotential"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | VOLT (SI) | V |
| 1 | NANOVOLT | nV |
| 2 | MICROVOLT | &mu;V |
| 3 | MILLIVOLT | mV |
| 4 | KILOVOLT | kV |
| 5 | MEGAVOLT | MV |
| 6 | GIGAVOLT | GV |
| 7 | ABVOLT | abV |
| 8 | STATVOLT | statV |


## 10. ElectricalResistance <a id="10-electricalresistance"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | OHM (SI) | &Omega; |
| 1 | NANOOHM | n&Omega; |
| 2 | MICROOHM | &mu;&Omega; |
| 3 | MILLIOHM | m&Omega; |
| 4 | KILOOHM | k&Omega; |
| 5 | MEGAOHM | M&Omega; |
| 6 | GIGAOHM | G&Omega; |
| 7 | ABOHM | ab&Omega; |
| 8 | STATOHM | stat&Omega; |


## 11. Energy <a id="11-energy"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | JOULE (SI) | J |
| 1 | PICOJOULE | pJ |
| 2 | NANOJOULE | mJ |
| 3 | MICROJOULE | &mu;J |
| 4 | MILLIJOULE | mJ |
| 5 | KILOJOULE | kJ |
| 6 | MEGAJOULE | MJ |
| 7 | GIGAJOULE | GJ |
| 8 | TERAJOULE | TJ |
| 9 | PETAJOULE | PJ |
| 10 | ELECTRONVOLT | eV |
| 11 | MICROELECTRONVOLT | &mu;eV |
| 12 | MILLIELECTRONVOLT | meV |
| 13 | KILOELECTRONVOLT | keV |
| 14 | MEGAELECTRONVOLT | MeV |
| 15 | GIGAELECTRONVOLT | GeV |
| 16 | TERAELECTRONVOLT | TeV |
| 17 | PETAELECTRONVOLT | PeV |
| 18 | EXAELECTRONVOLT | EeV |
| 19 | WATT_HOUR | Wh |
| 20 | FEMTOWATT_HOUR | fWh |
| 21 | PICOWATT_HOUR | pWh |
| 22 | NANOWATT_HOUR | mWh |
| 23 | MICROWATT_HOUR | &mu;Wh |
| 24 | MILLIWATT_HOUR | mWh |
| 25 | KILOWATT_HOUR | kWh |
| 26 | MEGAWATT_HOUR | MWh |
| 27 | GIGAWATT_HOUR | GWh |
| 28 | TERAWATT_HOUR | TWh |
| 29 | PETAWATT_HOUR | PWh |
| 30 | CALORIE | cal |
| 31 | KILOCALORIE | kcal |
| 32 | CALORIE_IT | cal(IT) |
| 33 | INCH_POUND_FORCE | in lbf |
| 34 | FOOT_POUND_FORCE | ft lbf |
| 35 | ERG | erg |
| 36 | BTU_ISO | BTU(ISO) |
| 37 | BTU_IT | BTU(IT) |
| 38 | STHENE_METER | sth.m |


## 12. FlowMass <a id="12-flowmass"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | KG_PER_SECOND (SI) | kg/s |
| 1 | POUND_PER_SECOND | lb/s |


## 13. FlowVolume <a id="13-flowvolume"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | CUBIC_METER_PER_SECOND (SI) | m<sup>3</sup>/s |
| 1 | CUBIC_METER_PER_MINUTE | m<sup>3</sup>/min |
| 2 | CUBIC_METER_PER_HOUR | m<sup>3</sup>/h |
| 3 | CUBIC_METER_PER_DAY | m<sup>3</sup>/day |
| 4 | CUBIC_INCH_PER_SECOND | in<sup>3</sup>/s |
| 5 | CUBIC_INCH_PER_MINUTE | in<sup>3</sup>/min |
| 6 | CUBIC_FEET_PER_SECOND | ft<sup>3</sup>/s |
| 7 | CUBIC_FEET_PER_MINUTE | ft<sup>3</sup>/min |
| 8 | GALLON_PER_SECOND | gal/s |
| 9 | GALLON_PER_MINUTE | gal/min |
| 10 | GALLON_PER_HOUR | gal/h |
| 11 | GALLON_PER_DAY | gal/day |
| 12 | LITER_PER_SECOND | l/s |
| 13 | LITER_PER_MINUTE | l/min |
| 14 | LITER_PER_HOUR | l/h |
| 15 | LITER_PER_DAY | l/day |


## 14. Force <a id="14-force"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | NEWTON (SI) | N |
| 1 | KILOGRAM_FORCE | kgf |
| 2 | OUNCE_FORCE | ozf |
| 3 | POUND_FORCE | lbf |
| 4 | TON_FORCE | tnf |
| 5 | DYNE | dyne |
| 6 | STHENE | sth |


## 15. Frequency <a id="15-frequency"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | HERTZ (SI) | Hz |
| 1 | KILOHERTZ | kHz |
| 2 | MEGAHERTZ | MHz |
| 3 | GIGAHERTZ | GHz |
| 4 | TERAHERTZ | THz |
| 5 | PER_SECOND | 1/s |
| 6 | PER_ATTOSECOND | 1/as |
| 7 | PER_FEMTOSECOND | 1/fs |
| 8 | PER_PICOSECOND | 1/ps |
| 9 | PER_NANOSECOND | 1/ns |
| 10 | PER_MICROSECOND | 1/&mu;s |
| 11 | PER_MILLISECOND | 1/ms |
| 12 | PER_MINUTE | 1/min |
| 13 | PER_HOUR | 1/hr |
| 14 | PER_DAY | 1/day |
| 15 | PER_WEEK | 1/wk |
| 16 | RPM | rpm |


## 16. Length <a id="16-length"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | METER (SI) | m |
| 1 | ATTOMETER | am |
| 2 | FEMTOMETER | fm |
| 3 | PICOMETER | pm |
| 4 | NANOMETER | nm |
| 5 | MICROMETER | &mu;m |
| 6 | MILLIMETER | mm |
| 7 | CENTIMETER | cm |
| 8 | DECIMETER | dm |
| 9 | DEKAMETER | dam |
| 10 | HECTOMETER | hm |
| 11 | KILOMETER | km |
| 12 | MEGAMETER | Mm |
| 13 | INCH | in |
| 14 | FOOT | ft |
| 15 | YARD | yd |
| 16 | MILE | mi |
| 17 | NAUTICAL_MILE | NM |
| 18 | ASTRONOMICAL_UNIT | au | 
| 19 | PARSEC | pc |
| 20 | LIGHTYEAR | ly |
| 21 | ANGSTROM | &angst; |


## 17. Position <a id="17-position"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | METER (BASE) | m |
| 1 | ATTOMETER | am |
| 2 | FEMTOMETER | fm |
| 3 | PICOMETER | pm |
| 4 | NANOMETER | nm |
| 5 | MICROMETER | &mu;m |
| 6 | MILLIMETER | mm |
| 7 | CENTIMETER | cm |
| 8 | DECIMETER | dm |
| 9 | DEKAMETER | dam |
| 10 | HECTOMETER | hm |
| 11 | KILOMETER | km |
| 12 | MEGAMETER | Mm |
| 13 | INCH | in |
| 14 | FOOT | ft |
| 15 | YARD | yd |
| 16 | MILE | mi |
| 17 | NAUTICAL_MILE | NM |
| 18 | ASTRONOMICAL_UNIT | au |
| 19 | PARSEC | pc |
| 20 | LIGHT_YEAR | ly |
| 21 | ANGSTROM | &angst; |


## 18. LinearDensity <a id="18-lineardensity"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | PER_METER (SI) | 1/m |
| 1 | PER_ATTOMETER | 1/am |
| 2 | PER_FEMTOMETER | 1/fm |
| 3 | PER_PICOMETER | 1/pm |
| 4 | PER_NANOMETER | 1/nm |
| 5 | PER_MICROMETER | 1/&mu;m |
| 6 | PER_MILLIMETER | 1/mm |
| 7 | PER_CENTIMETER | 1/cm |
| 8 | PER_DECIMETER | 1/dm |
| 9 | PER_DEKAMETER | 1/dam |
| 10 | PER_HECTOMETER | 1/hm |
| 11 | PER_KILOMETER | 1/km |
| 12 | PER_MEGAMETER | 1/Mm |
| 13 | PER_INCH | 1/in |
| 14 | PER_FOOT | 1/ft |
| 15 | PER_YARD | 1/yd |
| 16 | PER_MILE | 1/mi |
| 17 | PER_NAUTICAL_MILE | 1/NM |
| 18 | PER_ASTRONOMICAL_UNIT | 1/au |
| 19 | PER_PARSEC | 1/pc |
| 20 | PER_LIGHT_YEAR | 1/ly |
| 21 | PER_ANGSTROM | 1/&angst; |


## 19. Mass <a id="19-mass"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | KILOGRAM (SI) | kg |
| 1 | FEMTOGRAM | fg |
| 2 | PICOGRAM | pg |
| 3 | NANOGRAM | mg |
| 4 | MICROGRAM | &mu;g |
| 5 | MILLIGRAM | mg |
| 6 | GRAM | kg |
| 7 | MEGAGRAM | Mg |
| 8 | GIGAGRAM | Gg |
| 9 | TERAGRAM | Tg |
| 10 | PETAGRAM | Pg |
| 11 | MICROELECTRONVOLT | &mu;eV |
| 12 | MILLIELECTRONVOLT | meV |
| 13 | KILOELECTRONVOLT | keV |
| 14 | MEGAELECTRONVOLT | MeV |
| 15 | GIGAELECTRONVOLT | GeV |
| 16 | TERAELECTRONVOLT | TeV |
| 17 | PETAELECTRONVOLT | PeV |
| 18 | EXAELECTRONVOLT | EeV |
| 19 | OUNCE | oz |
| 20 | POUND | lb |
| 21 | DALTON | Da |
| 22 | TON_LONG | ton (long) |
| 23 | TON_SHORT | ton (short) |
| 24 | TONNE | tonne |


## 20. Power <a id="20-power"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | WATT (SI) | W |
| 1 | FEMTOWATT | fW |
| 2 | PICOWATT | pW |
| 3 | NANOWATT | mW |
| 4 | MICROWATT | &mu;W |
| 5 | MILLIWATT | mW |
| 6 | KILOWATT | kW |
| 7 | MEGAWATT | MW |
| 8 | GIGAWATT | GW |
| 9 | TERAWATT | TW |
| 10 | PETAWATT | PW |
| 11 | ERG_PER_SECOND | erg/s |
| 12 | FOOT_POUND_FORCE_PER_SECOND | ft.lbf/s |
| 13 | FOOT_POUND_FORCE_PER_MINUTE | ft.lbf/min |
| 14 | FOOT_POUND_FORCE_PER_HOUR | ft.lbf/h |
| 15 | HORSEPOWER_METRIC | hp / PS |
| 16 | STHENE_METER_PER_SECOND | sth/s |


## 21. Pressure <a id="21-pressure"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | PASCAL (SI) | Pa |
| 1 | HECTOPASCAL | hPa |
| 2 | KILOPASCAL | kPa |
| 3 | ATMOSPHERE_STANDARD | atm |
| 4 | ATMOSPHERE_TECHNICAL | at |
| 5 | MILLIBAR | mbar |
| 6 | BAR | bar |
| 7 | BARYE | Ba |
| 8 | MILLIMETER_MERCURY | mmHg |
| 9 | CENTIMETER_MERCURY | cmHg |
| 10 | INCH_MERCURY | inHg |
| 11 | FOOT_MERCURY | ftHg |
| 12 | KGF_PER_SQUARE_MM | kgf/mm<sup>2</sup> |
| 13 | PIEZE | pz |
| 14 | POUND_PER_SQUARE_INCH | lb/in<sup>2</sup> |
| 15 | POUND_PER_SQUARE_FOOT | lb/ft<sup>2</sup> |
| 16 | TORR | torr |


## 22. Speed <a id="22-speed"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | METER_PER_SECOND (SI) | m/s |
| 1 | METER_PER_HOUR | m/h |
| 2 | KM_PER_SECOND | km/s |
| 3 | KM_PER_HOUR | km/h |
| 4 | INCH_PER_SECOND | in/s |
| 5 | INCH_PER_MINUTE | in/min |
| 6 | INCH_PER_HOUR | in/h |
| 7 | FOOT_PER_SECOND | ft/s |
| 8 | FOOT_PER_MINUTE | ft/min |
| 9 | FOOT_PER_HOUR | ft/h |
| 10 | MILE_PER_SECOND | mi/s |
| 11 | MILE_PER_MINUTE | mi/min |
| 12 | MILE_PER_HOUR | mi/h |
| 13 | KNOT | kt |


## 23. Temperature <a id="23-temperature"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | KELVIN (SI) | K |
| 1 | DEGREE_CELSIUS | &deg;C |
| 2 | DEGREE_FAHRENHEIT | &deg;F |
| 3 | DEGREE_RANKINE | &deg;R |
| 4 | DEGREE_REAUMUR | &deg;R&eacute; |



## 24. AbsoluteTemperature <a id="24-absolutetemperature"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | KELVIN (BASE) | K |
| 1 | DEGREE_CELSIUS | &deg;C |
| 2 | DEGREE_FAHRENHEIT | &deg;F |
| 3 | DEGREE_RANKINE | &deg;R |
| 4 | DEGREE_REAUMUR | &deg;R&eacute; |


## 25. Duration <a id="25-duration"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | SECOND (SI) | s |
| 1 | ATTOSECOND | as |
| 2 | FEMTOSECOND | fs |
| 3 | PICOSECOND | ps |
| 4 | NANOSECOND | ns |
| 5 | MICROSECOND | &mu;s |
| 6 | MILLISECOND | ms |
| 7 | MINUTE | min |
| 8 | HOUR | hr |
| 9 | DAY | day |
| 10 | WEEK | wk |


## 26. Time <a id="26-time"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | BASE_SECOND | s |
| 1 | BASE_MICROSECOND | &mu;s |
| 2 | BASE_MILLISECOND | ms |
| 3 | BASE_MINUTE | min |
| 4 | BASE_HOUR | hr |
| 5 | BASE_DAY | day |
| 6 | BASE_WEEK | wk |
| 7 | EPOCH_SECOND (since 1-1-70 UTC) | s (POSIX) |
| 8 | EPOCH_MICROSECOND (since 1-1-1970 UTC) | &mu;s (POSIX) |
| 9 | EPOCH_MILLISECOND (since 1-1-1970 UTC) | ms (POSIX) |
| 10 | EPOCH_MINUTE (since 1-1-1970 UTC) | min (POSIX) |
| 11 | EPOCH_HOUR (since 1-1-1970 UTC) | hr (POSIX) |
| 12 | EPOCH_DAY (since 1-1-1970 UTC) | day (POSIX) |
| 13 | EPOCH_WEEK (since 1-1-1970 UTC) | wk (POSIX) |
| 14 | YEAR1_SECOND (since 1-1-0001 UTC) | s(1-1-0001) |
| 15 | J2000_SECOND (since 1-1-2000 UTC, 12:00) | s(1-1-2000) |


## 27. Torque <a id="27-torque"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | NEWTON_METER (SI) | Nm |
| 1 | POUND_FOOT | lb.ft |
| 2 | POUND_INCH | lb.in |
| 3 | METER_KILOGRAM_FORCE | m.kgf |


## 28. Volume <a id="28-volume"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | CUBIC_METER (SI) | m<sup>3</sup> |
| 1 | CUBIC_ATTOMETER | am<sup>3</sup> |
| 2 | CUBIC_FEMTOMETER | fm<sup>3</sup> |
| 3 | CUBIC_PICOMETER | pm<sup>3</sup> |
| 4 | CUBIC_NANOMETER | nm<sup>3</sup> |
| 5 | CUBIC_MICROMETER | &mu;m<sup>3</sup> |
| 6 | CUBIC_MILLIMETER | mm<sup>3</sup> |
| 7 | CUBIC_CENTIMETER | cm<sup>3</sup> |
| 8 | CUBIC_DECIMETER | dm<sup>3</sup> |
| 9 | CUBIC_DEKAMETER | dam<sup>3</sup> |
| 10 | CUBIC_HECTOMETER | hm<sup>3</sup> |
| 11 | CUBIC_KILOMETER | km<sup>3</sup> |
| 12 | CUBIC_MEGAMETER | Mm<sup>3</sup> |
| 13 | CUBIC_INCH | in<sup>3</sup> |
| 14 | CUBIC_FOOT | ft<sup>3</sup> |
| 15 | CUBIC_YARD | yd<sup>3</sup> |
| 16 | CUBIC_MILE | mi<sup>3</sup> |
| 17 | LITER | l |
| 18 | GALLON_IMP | gal (imp) |
| 19 | GALLON_US_FLUID | gal (US) |
| 20 | OUNCE_IMP_FLUID | oz (imp) |
| 21 | OUNCE_US_FLUID | oz (US) |
| 22 | PINT_IMP | pt (imp) |
| 23 | PINT_US_FLUID | pt (US) |
| 24 | QUART_IMP | qt (imp) |
| 25 | QUART_US_FLUID | qt (US) |
| 26 | CUBIC_PARSEC | pc3 |
| 27 | CUBIC_LIGHT_YEAR | ly3 |


## 29. AbsorbedDose <a id="#29-absorbeddose"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | GRAY (SI) | Gy |
| 1 | MILLIGRAY | mGy |
| 2 | MICROGRAY | muGy |
| 3 | ERG_PER_GRAM | erg/g |
| 4 | RAD | rad |


## 30. AmountOfSubstance <a href="#30-amountofsubstance"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | MOLE (SI) | mol |
| 1 | MILLIMOLE | mmol |
| 2 | MICROMOLE | mumol |
| 3 | NANOMOLE | nmol |


## 31. CatalyticActivity <a href="#31-catalyticactivity"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | KATAL (SI) | kat |
| 1 | MILLIKATAL | mkat |
| 2 | MICROKATAL | mukat |
| 3 | NANOKATAL | nkat |


## 32. ElectricalCapacitance <a href="#32-electricalcapacitance"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | FARAD (SI) | F |
| 1 | MILLIFARAD | mF |
| 2 | MICROFARAD | uF |
| 3 | NANOFARAD | nF |
| 4 | PICOFARAD | pF |


## 33. ElectricalConductance <a href="#33-electricalconductance"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | SIEMENS (SI) | S |
| 1 | MILLISIEMENS | mS |
| 2 | MICROSIEMENS | muS |
| 3 | NANOSIEMENS | nS |


## 34. ElectricalInductance <a href="#34-electricalinductance"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | HENRY (SI) | H |
| 1 | MILLIHENRY | mH |
| 2 | MICROHENRY | muH |
| 3 | NANOHENRY | nH |


## 35. EquivalentDose <a href="#35-equivalentdose"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | SIEVERT (SI) | Sv |
| 1 | MILLISIEVERT | mSv |
| 2 | MICROSIEVERT | muSv |
| 3 | REM | rem |


## 36. Illuminance <a href="#36-illuminance"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | LUX (SI) | lx |
| 1 | MILLILUX | mlx |
| 2 | MICROLUX | mulx |
| 3 | KILOLUX | klx |
| 4 | PHOT | ph |
| 5 | NOX | nx |


## 37. LuminousFlux <a href="#37-luminousflux"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | LUMEN (SI) | lm |


## 38. LuminousIntensity <a href="#38-luminousintensity"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | CANDELA (SI) | cd |


## 39. MagneticFluxDensity <a href="#39-magneticfluxdensity"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | TESLA (SI) | T |
| 1 | MILLITESLA | mT |
| 2 | MICROTESLA | muT |
| 3 | NANOTESLA | nT |
| 4 | GAUSS | G |


## 40. MagneticFlux <a href="#40-magneticflux"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | WEBER (SI) | Wb |
| 1 | MILLIWEBER | mWb |
| 2 | MICROWEBER | muWb |
| 3 | NANOWEBER | nWb |
| 4 | MAXWELL | Mx |


## 41. RadioActivity <a href="#41-radioactivity"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | BECQUEREL (SI) | Bq |
| 1 | KILOBECQUEREL | kBq |
| 2 | MEGABECQUEREL | MBq |
| 3 | GIGABECQUEREL | GBq |
| 4 | TERABECQUEREL | TBq |
| 5 | PETABECQUEREL | PBq |
| 6 | CURIE | Ci |
| 7 | MILLICURIE | mCi |
| 8 | MICROCURIE | muCi |
| 9 | NANOCURIE | nCi |
| 10 | RUTHERFORD | Rd |

## 42. AngularAcceleration <a href="#42-angularacceleration"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | RADIAN_PER_SECOND_2 (SI) | rad/s<sup>2</sup> |
| 1 | DEGREE_PER_SECOND_2 | deg/s<sup>2</sup> |
| 2 | ARCMINUTE_PER_SECOND_2 | arcmin/s<sup>2</sup> |
| 3 | ARCSECOND_PER_SECOND_2 | arcsec/s<sup>2</sup> |
| 4 | GRAD_PER_SECOND_2 | grad/s<sup>2</sup> |
| 5 |CENTECIMALARCMINUTE_PER_SECOND_2 | cdm/s<sup>2</sup> |
| 6 |CENTECIMALARCSECOND_PER_SECOND_2 | cds/s<sup>2</sup> |

## 43. AngularVelocity <a href="#43-angularvelocity"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | RADIAN_PER_SECOND (SI) | rad/s |
| 1 | DEGREE_PER_SECOND | deg/s |
| 2 | ARCMINUTE_PER_SECOND | arcmin/s |
| 3 | ARCSECOND_PER_SECOND | arcsec/s |
| 4 | GRAD_PER_SECOND | grad/s |
| 5 |CENTECIMALARCMINUTE_PER_SECOND | cdm/s |
| 6 |CENTECIMALARCSECOND_PER_SECOND | cds/s |

## 44. Momentum <a href="#44-momentum"></a>

| Code | Name of display unit | Unit |
| ------- | --------------------------- | ------ |
| 0 | SI | kgm/s |






