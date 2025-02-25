# Coding types

## Strong typing

With strong typing, the receiving program can deserialize the message without a template or recipe how to do that. It also forms an extra check on the correctness, and correspondence between the expected fields and the received fields. Several approaches are possible: defining message types using  separate messages, preceding each message with its structure, or preceding each field with its structure. The current version of DJUTILS-SERIALIZATION implements the last case: every field is preceded by a byte that indicates the type of the byte(s) that follow. 


## Endianness

When coding multi-byte values, knowing endianness is of the utmost importance. Endianness indicates whether the most-significant byte comes first or whether the least-significant byte comes first. The Internet and languages like Java use big endian, aka network byte order; the most significant byte comes first. Microsoft products and Intel processors internally use little endian; the least significant byte comes first. As an example, when we code an int (4 bytes) with the value 824, it is coded as follows using decimal notation (824 = 0 \* 256<sup>3</sup> + 0 \* 256<sup>2</sup> + 3 \* 256 + 56 \* 1):

<pre>
|  0 |  0 |  3 | 56 | Byte ordering in Big endian
| 56 |  3 |  0 |  0 | Byte ordering in Little endian
</pre>


## Implemented types

The following types have been implemented in the v1-version of the standard:

| code | name | description |
| ------ | ------- | -------------- |
| 0 | BYTE_8 | Byte, 8 bit signed two's complement integer |
| 1 | SHORT_16 | Short, 16 bit signed two's complement integer |
| 2 | INT_32 | Integer, 32 bit signed two's complement integer |
| 3 | LONG_64 | Long, 64 bit signed two's complement integer |
| 4 | FLOAT_32 | Float, single-precision 32-bit IEEE 754 floating point |
| 5 | DOUBLE_64 | Float, double-precision 64-bit IEEE 754 floating point |
| 6 | BOOLEAN_8 | Boolean, sent / received as a byte; 0 = false, 1 = true |
| 7 | CHAR_8 | Char, 8-bit ASCII character |
| 8 | CHAR_16 | Char, 16-bit Unicode character, the 2 bytes in the order of endianness |
| 9 | STRING_UTF8 | String, represented as a UTF-8 byte array, preceded by a 32-bit number indicating the number of bytes (NOT the number of characters) |
| 10 | STRING_UTF16 | String, represented as a UTF-16 short array, preceded by a 32-bit number indicating the number of shorts / chars (NOT the number of characters in the string, nor the number of bytes in the encoding); the 2 bytes of each character are coded using endianness |
| 11 | BYTE_8_ARRAY | Byte array, preceded by a 32-bit number indicating the number of bytes |
| 12 | SHORT_16_ARRAY | Short array, preceded by a 32-bit number indicating the number of shorts |
| 13 | INT_32_ARRAY | Integer array, preceded by a 32-bit number indicating the number of integers |
| 14 | LONG_64_ARRAY | Long array, preceded by a 32-bit number indicating the number of longs |
| 15 | FLOAT_32_ARRAY | Float array, preceded by a 32-bit number indicating the number of floats |
| 16 | DOUBLE_64_ARRAY | Double array, preceded by a 32-bit number indicating the number of doubles |
| 17 | BOOLEAN_8_ARRAY | Boolean array, preceded by a 32-bit number indicating the number of booleans |
| 18 | BYTE_8_MATRIX | Byte matrix, preceded by a 32-bit number row count and a 32-bit number column count |
| 19 | SHORT_16_MATRIX | Short matrix, preceded by a 32-bit number row count and a 32-bit number column count |
| 20 | INT_32_MATRIX | Integer matrix, preceded by a 32-bit number row count and a 32-bit number column count |
| 21 | LONG_64_MATRIX | Long matrix, preceded by a 32-bit number row count and a 32-bit number column count |
| 22 | FLOAT_32_MATRIX | Float matrix, preceded by a 32-bit number row count and a 32-bit number column count |
| 23 | DOUBLE_64_MATRIX | Double matrix, preceded by a 32-bit number row count and a 32-bit number column count |
| 24 | BOOLEAN_8_MATRIX | Boolean matrix, preceded by a 32-bit number row count and a 32-bit number column count |
| 25 | FLOAT_32_UNIT | Float stored internally as a float in the corresponding SI unit, with unit type and display unit attached. The total size of the object is 7 bytes |
| 26 | DOUBLE_64_UNIT | Double stored internally as a double in the corresponding SI unit, with unit type and display unit attached. The total size of the object is 11 bytes |
| 27 | FLOAT_32_UNIT_ARRAY | Dense float array, preceded by a 32-bit number indicating the number of floats, with unit type and display unit attached to the entire float array |
| 28 | DOUBLE_64_UNIT_ARRAY | Dense double array, preceded by a 32-bit number indicating the number of doubles, order, with unit type and display unit attached to the entire double array |
| 29 | FLOAT_32_UNIT_MATRIX | Dense float matrix, preceded by a 32-bit row count int and a 32-bit column count int, with unit type and display unit attached to the entire float matrix |
| 30 | DOUBLE_64_UNIT_MATRIX | Dense double matrix, preceded by a 32-bit row count int and a 32-bit column count int, with unit type and display unit attached to the entire double matrix |
| 31 | FLOAT_32_UNIT_COLUMN_MATRIX | Dense float matrix, preceded by a 32-bit row count int and a 32-bit column count int, with a unique unit type and display unit per column of the float matrix. |
| 32 | DOUBLE_64_UNIT_COLUMN_MATRIX | Dense double matrix, preceded by a 32-bit row count int and a 32-bit column count int, with a unique unit type and display unit per column of the double matrix. |
| 33 | STRING_UTF8_ARRAY | String array where each string is encoded as a UTF-8 byte array. |
| 34 | STRING_UTF16_ARRAY | String array where each string is encoded as a UTF-16 byte array. |
| 35 | STRING_UTF8_MATRIX | String matrix where each string is encoded as a UTF-8 byte array. |
| 36 | STRING_UTF16_MATRIX | String matrix where each string is encoded as a UTF-16 byte array. |

<br>

## Unicode characters

Unicode characters can be of different formats: UTF-8, UTF-16 with a byte-order marker (BOM), UTF-16BE (big-endian), UTF-16LE (little-endian), UTF-32 with a byte-order marker (BOM), UTF-32BE (big-endian), and UTF-32LE (little-endian). To code all Unicode characters in UTF-8, one to four UTF-8 bytes are needed through the use of escape characters. For UTF-16, one or two two-byte combinations are needed. In UTF-32, all Unicode characters can be directly coded. Because of the escape characters, characters and strings really look different in UTF-8, UTF-16, and UTF-32. The current version of DJUTILS-SERIALIZATION (and Sim0MQ) supports UTF-8, UTF-16BE (big-endian), and UTF-16LE (little-endian). More about the differences between the encodings is explained in the Unicode FAQ list: [https://unicode.org/faq/utf_bom.html#gen6](https://unicode.org/faq/utf_bom.html#gen6).

For a discussion on little and big endianness for UTF-8 and UTF-16 strings, see the following discussion at StackExchange: [https://stackoverflow.com/questions/3833693/isn-t-on-big-endian-machines-utf-8s-byte-order-different-than-on-little-endian](https://stackoverflow.com/questions/3833693/isn-t-on-big-endian-machines-utf-8s-byte-order-different-than-on-little-endian), as well as [https://unicode.org/faq/utf_bom.html#utf8-2](https://unicode.org/faq/utf_bom.html#utf8-2).

!!! Note 
    Note that because of escape characters (or surrogates), the String length is not equal to the number of characters in UTF-8, nor to the number of characters/shorts times two in UTF-16. The numbers in STRING_UTF8 and STRING_UTF8_LE represent the number of bytes in the representation, and **not** the number of 'visible' characters in the resulting String. The numbers in STRING_UTF16 and STRING_UTF16_LE represent the number of shorts (2 bytes) in the representation, and **neither** the number of 'visible' characters in the resulting String, **nor** the number of bytes in the representation.

