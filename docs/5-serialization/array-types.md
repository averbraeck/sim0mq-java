# Array types (#11 - #17)

The array types are preceded by a 32-bit int indicating the number of values in the array that follows. This int is itself not preceded by a byte indicating it is an int. An array of 8 shorts with numbers 100 through 107 is therefore coded as follows:

<pre>
|12|0|0|0|8|
|0|100|0|101|0|102|0|103|0|104|0|105|0|106|0|107|
</pre>

In this illustration, the 21 bytes have been divided over two lines for readability. The serialized data may not contain such line breaks.

## Big-Endian representation

| code | name | description |
| ------ | ------- | -------------- |
| 11 | BYTE_8_ARRAY | Byte array, preceded by a 32-bit number indicating the number of bytes |
| 12 | SHORT_16_ARRAY | Short array, preceded by a 32-bit number indicating the number of shorts |
| 13 | INT_32_ARRAY | Integer array, preceded by a 32-bit number indicating the number of integers |
| 14 | LONG_64_ARRAY | Long array, preceded by a 32-bit number indicating the number of longs |
| 15 | FLOAT_32_ARRAY | Float array, preceded by a 32-bit number indicating the number of floats |
| 16 | DOUBLE_64_ARRAY | Double array, preceded by a 32-bit number indicating the number of doubles |
| 17 | BOOLEAN_8_ARRAY | Boolean array, preceded by a 32-bit number indicating the number of booleans |


## Little-Endian representation

| code | name | description |
| ------ | ------- | -------------- |
| 139 (-117) | BYTE_8_ARRAY_LE | Byte array, preceded by a 32-bit little-endian number indicating the number of bytes |
| 140 (-116) | SHORT_16_ARRAY_LE | Short array, preceded by a 32-bit little-endian number indicating the number of shorts, little-endian coded shorts |
| 141 (-115) | INT_32_ARRAY_LE | Integer array, preceded by a 32-bit little-endian number indicating the number of integers, little-endian coded ints |
| 142 (-114) | LONG_64_ARRAY_LE | Long array, preceded by a 32-bit little-endian number indicating the number of longs, little-endian coded longs |
| 143 (-113) | FLOAT_32_ARRAY_LE | Float array, preceded by a 32-bit little-endian number indicating the number of floats, little-endian coded floats |
| 144 (-112) | DOUBLE_64_ARRAY_LE | Double array, preceded by a 32-bit little-endian number indicating the number of doubles, little-endian coded doubles |
| 145 (-111) | BOOLEAN_8_ARRAY_LE | Boolean array, preceded by a 32-bit little-endian number indicating the number of booleans |
