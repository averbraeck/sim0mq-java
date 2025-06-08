# Scalar types with unit (#25 - #26)

## 25. Float with unit

The internal storage of the value that is transmitted is always in the SI (or standard) unit. The value is preceded by a one-byte unit type (see the table in [Coding of units](../coding-units) ) and a one-byte display type (see  [Display types for units](../display-types)). As an example: suppose the unit indicates that the type is a length, whereas the display type indicates that the internally stored value 60000.0 should be displayed as 60.0 km, this is coded as follows (assuming *big-endian* encoding):

<pre>
|25|16|11|0x47|0x6A|0x60|0x00|
</pre>

In *little-endian* encoding, it looks as follows:

<pre>
|25|16|11|0x00|0x60|0x6A|0x47|
</pre>



## 26. Double with unit

The internal storage of the value that is transmitted is always in the SI (or standard) unit. The value is preceded by a one-byte unit type (see the table in [Coding of units](../coding-units) ) and a one-byte display type (see  [Display types for units](../display-types)). As an example: suppose the unit indicates that the type is a length, whereas the display type indicates that the internally stored value 60000.0 should be displayed as 60.0 km, this is coded as follows (assuming *big-endian* encoding):

<pre>
|26|16|11|0x47|0x6A|0x60|0x00|0x00|0x00|0x00|0x00|
</pre>

In *little-endian* encoding, it looks as follows:

<pre>
|26|16|11|0x00|0x00|0x00|0x00|0x00|0x60|0x6A|0x47|
</pre>
