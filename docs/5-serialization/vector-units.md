# Vector types with unit (#27 - #28)

## 27. Float array with unit

After the byte with value 27, the array types have a 32-bit int indicating the number of values in the array that follows. This int is itself not preceded by a byte indicating it is an int. Then a one-byte unit type follows (see the table in [Coding of units](../coding-units) ) and a one-byte display type (see  [Display types for units](../display-types)). The internal storage of the values that are transmitted after that always use the SI (or standard) unit. As an example: when we send an array of two durations, 2.0 minutes and 2.5 minutes, this is coded as follows (assuming big-endian encoding):

<pre>
|27|0|0|0|2|25|7|
|0x40|0x00|0x00|0x00|
|0x40|0x20|0x00|0x00|
</pre>

In this illustration, the 11 bytes have been divided over three lines for readability. The serialized data may not contain such line breaks.


## 28. Double array with unit

After the byte with value 28, the array types have a 32-bit int indicating the number of values in the array that follows. This int is itself not preceded by a byte indicating it is an int. Then a one-byte unit type follows (see the table in [Coding of units](../coding-units) ) and a one-byte display type (see  [Display types for units](../display-types)). The internal storage of the values that are transmitted after that always use the SI (or standard) unit. As an example: when we send an array of two durations, 21.2 minutes and 21.5 minutes, this is coded as follows (assuming big-endian encoding):

<pre>
|28|0|0|0|2|25|7|
|0x40|0x35|0x33|0x33|0x3|0x33|0x33|0x33|
|0x40|0x35|0x80|0x00|0x00|0x00|0x00|0x00|
</pre>

In this illustration, the 23 bytes have been divided over three lines for readability. The serialized data may not contain such line breaks.


## Little-endian types

| code | name | description |
| ------ | ------- | -------------- |
| 155 (-101) | FLOAT_32_UNIT_ARRAY_LE | Dense float array, preceded by a little-endian 32-bit number indicating the number of floats, with unit type and display unit attached to the entire float array. Each float is stored in little-endian order. |
| 156 (-100) | DOUBLE_64_UNIT_ARRAY_LE | Dense double array, preceded by a little-endian 32-bit number indicating the number of doubles, little-endian order, with unit type and display unit attached to the entire double array. Each double is stored in little-endian order. |
