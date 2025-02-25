# Scalar types with unit (#25 - #26)

## 25. Float with unit

The internal storage of the value that is transmitted is always in the SI (or standard) unit. The value is preceded by a one-byte unit type (see the table in [Coding of units](../coding-units) ) and a one-byte display type (see  [Display types for units](../display-types)). As an example: suppose the unit indicates that the type is a length, whereas the display type indicates that the internally stored value 60000.0 should be displayed as 60.0 km, this is coded as follows (assuming big-endian encoding):

<pre>
|25|16|11|0x47|0x6A|0x60|0x00|
</pre>


## 26. Double with unit

The internal storage of the value that is transmitted is always in the SI (or standard) unit. The value is preceded by a one-byte unit type (see the table in [Coding of units](../coding-units) ) and a one-byte display type (see  [Display types for units](../display-types)). As an example: suppose the unit indicates that the type is a length, whereas the display type indicates that the internally stored value 60000.0 should be displayed as 60.0 km, this is coded as follows (assuming big-endian encoding):

<pre>
|26|16|11|0x47|0x6A|0x60|0x00|0x00|0x00|0x00|0x00|
</pre>


## Little-endian types

| code | name | description |
| ------ | ------- | -------------- |
| 153 (-103) | FLOAT_32_UNIT_LE | Float stored internally as a little-endian float in the corresponding SI unit, with unit type and display unit attached. The total size of the object is 7 bytes. |
| 154 (-102) | DOUBLE_64_UNIT_LE | Double stored internally as a little-endian double in the corresponding SI unit, with unit type and display unit attached. The total size of the object is 11 bytes |
