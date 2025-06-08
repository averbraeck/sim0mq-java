# Primitive types (#0 - #8)

## 0. Byte

A byte is coded as one byte (8 bits), preceded by a byte with code 0. The byte is coded as an 8 bit signed two's complement value. As an example, suppose we want to code the byte with the decimal value 55, it is coded as follows in the stream:

<pre>
| 0 | 55 |
</pre>

The big-endian encoding and little-endian encoding for bytes is the same.
<br/>


## 1. Short

A short is coded as two bytes (16 bits), preceded by a byte with code 1. The short is coded as a 16 bit signed two's complement value. As an example, suppose we want to code the short with the decimal value 517 (2 \* 256 + 5 \* 1), it is coded as follows in the stream for *big-endian* encoding:

<pre>
| 1 | 2 | 5 |
</pre>

For *little-endian* encoding, the short would be encoded as:

<pre>
| 1 | 5 | 2 |
</pre>
<br/>


## 2. Integer

An integer is coded as four bytes (32 bits), preceded by a byte with code 2. The int is coded as a 32 bit signed two's complement value. As an example, suppose we want to code the int with the decimal value -4, it is coded as follows in the stream (hex values used; *big-endian* encoding):

<pre>
| 0x02 | 0xFF | 0xFF | 0xFF | 0xFC |
</pre>

For *little-endian* encoding, the integer would be encoded as:

<pre>
| 0x02 | 0xFC | 0xFF | 0xFF | 0xFF |
</pre>
<br/>


## 3. Long

A long value is coded as eight bytes (64 bits), preceded by a byte with code 3. The long is coded as a 64 bit signed two's complement value. As an example, suppose we want to code the long with the decimal value 9223372036854775807 (this is the largest possible positive value), it is coded as follows in the stream (hex values used; *big-endian* encoding):

<pre>
| 0x03 | 0x7F | 0xFF | 0xFF | 0xFF | 0xFF | 0xFF | 0xFF | 0xFF |
</pre>

In *little-endian* encoding, this would be encoded as:

<pre>
| 0x03 | 0xFF | 0xFF | 0xFF | 0xFF | 0xFF | 0xFF | 0xFF | 0x7F |
</pre>

<br/>


## 4a. Float, big-endian encoding

A float value is coded as four bytes (32 bits), preceded by a byte with code 4. The float is coded as a single-precision 32-bit [IEEE 754](https://en.wikipedia.org/wiki/Single-precision_floating-point_format) floating point value. In *big endian*, the floating point is coded as follows (where S = sign, E = exponent, M = mantissa): 

~~~text
| SEEEEEEE | EMMMMMMM | MMMMMMMM | MMMMMMMM | 
~~~

The exponent is calculated by subtracting 127 from the number formed by the 8 E-bits. So , e.g., binary value 10000000 for the exponent means that the value is 128-127 = 1. The mantissa is calculated by taking 1.0 + (bit 1 \* 1/2) + (bit 2 \* 1/4) + (bit 3 \* 1/8) + ... + (bit 23 \* 2^-23). When e.g., the exponent is 1 and the mantissa is (bit 1 = 1), the value that is represented is: 2^1 \* (1.0 + 0.5) = 3.0. Special cases are:

<pre>
| S0000000 | 00000000 | 00000000 | 00000000 | number 0, or -0 when sign = 1
| S1111111 | 10000000 | 00000000 | 00000000 | plus or minus infinity
| S1111111 | 1xxxxxxx | xxxxxxxx | xxxxxxxx | NaN when xxxx is not all equal to 0
| S0000000 | 0xxxxxxx | xxxxxxxx | xxxxxxxx | so-called denormalized number, 0.Mantissa, without the +1.0
</pre>

As an example, suppose we want to code the float with the value 2.5. It is coded as follows in the stream (binary + hex values):

<pre>
| 01000000 | 00100000 | 00000000 | 00000000 | Sign = 0, Exponent = 1 (128-127), Mantissa = (1.0) + 1/4
|   0x40   |   0x20   |   0x00   |   0x00   | This results in +2 * 1.25 = 2.5
</pre>
<br/>


## 4b. Float, little-endian encoding

A float value is coded as four bytes (32 bits), preceded by a byte with code 4. The float is coded as a single-precision 32-bit [IEEE 754](https://en.wikipedia.org/wiki/Single-precision_floating-point_format) floating point value. In *little endian*, the floating point is coded as follows (where S = sign, E = exponent, M = mantissa): 

~~~text
| MMMMMMMM | MMMMMMMM | EMMMMMMM | SEEEEEEE | 
~~~

The exponent is calculated by subtracting 127 from the number formed by the 8 E-bits. So , e.g., binary value 10000000 for the exponent means that the value is 128-127 = 1. The mantissa is calculated by taking 1.0 + (bit 1 \* 1/2) + (bit 2 \* 1/4) + (bit 3 \* 1/8) + ... + (bit 23 \* 2^-23). When e.g., the exponent is 1 and the mantissa is (bit 1 = 1), the value that is represented is: 2^1 \* (1.0 + 0.5) = 3.0. Special cases are:

<pre>
| 00000000 | 00000000 | 00000000 | S0000000 | number 0, or -0 when sign = 1
| S1111111 | 10000000 | 00000000 | 00000000 | plus or minus infinity
| xxxxxxxx | xxxxxxxx | 1xxxxxxx | S1111111 | NaN when xxxx is not all equal to 0
| xxxxxxxx | xxxxxxxx | 0xxxxxxx | S0000000 | so-called denormalized number, 0.Mantissa, without the +1.0
</pre>

As an example, suppose we want to code the float with the value 2.5. It is coded as follows in the stream (binary + hex values):

<pre>
| 00000000 | 00000000 | 00100000 | 01000000 | Sign = 0, Exponent = 1 (128-127), Mantissa = (1.0) + 1/4
|   0x00   |   0x00   |   0x20   |   0x40   | This results in +2 * 1.25 = 2.5
</pre>
<br/>


## 5a. Double, big-endian encoding

A double value is coded as eight bytes (64 bits), preceded by a byte with code 5. The double is coded as a double-precision 64-bit [IEEE 754](https://en.wikipedia.org/wiki/Double-precision_floating-point_format) floating point value. In *big endian*, the floating point is coded as follows (where S = sign, E = exponent, M = mantissa): 

<pre>
| SEEEEEEE | EEEEMMMM | MMMMMMMM | MMMMMMMM | MMMMMMMM | MMMMMMMM | MMMMMMMM | MMMMMMMM | 
</pre>

The exponent is calculated by subtracting 1023 from the number formed by the 11 E-bits. So when we code 10000000000 for the exponent, the value is 1024-1023 = 1. The mantissa is calculated by taking 1.0 + (bit 1 \* 1/2) + (bit 2 \* 1/4) + (bit 3 \* 1/8) + ... + (bit 53 \* 2^-53). When e.g., the exponent is 1 and the mantissa is (bit 1 = 1), the value that is represented is: 2^1 \* (1.0 + 0.5) = 3.0. Special cases are:

<pre>
| S0000000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 
number 0, or -0 when sign = 1
 
| S1111111 | 11110000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 |
plus or minus infinity
 
| S1111111 | 1111xxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | 
NaN when xxxx is not all equal to 0

| S0000000 | 0000xxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx |
so-called denormalized number, 0.Mantissa, without the +1.0
</pre>

As an example, suppose we want to code the double with the value -8.25. It is coded as follows in the stream (binary + hex values):

<pre>
| 11000000 | 00100000 | 10000000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 
|   0xC0   |   0x20   |   0x80   |   0x00   |   0x00   |   0x00   |   0x00   |   0x00   |
Sign = 1, Exponent = 3 (1026-1023), Mantissa = (1.0) + 1/32. 
This results in -2^3 * (1+1/32) = -8 * 33/32 = -8.25.
</pre>
<br/>

## 5b. Double, little-endian encoding

A double value is coded as eight bytes (64 bits), preceded by a byte with code 5. The double is coded as a double-precision 64-bit [IEEE 754](https://en.wikipedia.org/wiki/Double-precision_floating-point_format) floating point value. In *little endian*, the floating point is coded as follows (where S = sign, E = exponent, M = mantissa): 

<pre>
| MMMMMMMM | MMMMMMMM | MMMMMMMM | MMMMMMMM | MMMMMMMM | MMMMMMMM | EEEEMMMM | SEEEEEEE |
</pre>

The exponent is calculated by subtracting 1023 from the number formed by the 11 E-bits. So when we code 10000000000 for the exponent, the value is 1024-1023 = 1. The mantissa is calculated by taking 1.0 + (bit 1 \* 1/2) + (bit 2 \* 1/4) + (bit 3 \* 1/8) + ... + (bit 53 \* 2^-53). When e.g., the exponent is 1 and the mantissa is (bit 1 = 1), the value that is represented is: 2^1 \* (1.0 + 0.5) = 3.0. Special cases are:

<pre>
| 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | S0000000 | 
number 0, or -0 when sign = 1
 
| 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 11110000 | S1111111 | 
plus or minus infinity
 
| xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | 1111xxxx | S1111111 | 
NaN when xxxx is not all equal to 0

| xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | xxxxxxxx | 0000xxxx | S0000000 | 
so-called denormalized number, 0.Mantissa, without the +1.0
</pre>

As an example, suppose we want to code the double with the value -8.25. It is coded as follows in the stream (binary + hex values):

<pre>
| 00000000 | 00000000 | 00000000 | 00000000 | 00000000 | 10000000 | 00100000 | 11000000 | 
|   0x00   |   0x00   |   0x00   |   0x00   |   0x00   |   0x80   |   0x20   |   0xC0   | 
Sign = 1, Exponent = 3 (1026-1023), Mantissa = (1.0) + 1/32. 
This results in -2^3 * (1+1/32) = -8 * 33/32 = -8.25.
</pre>
<br/>


## 6. Boolean

A boolean is coded as one byte (8 bits), preceded by a byte with code 6.The boolean is coded as 0 for false, and as 1 (or any non-zero value) for true. So the value "true" is typically coded as (decimal notation used):

<pre>
| 6 | 1 |
</pre>

This encoding is the same for big-endian and little-endian.
<br/>


## 7. Character (UTF-8)

Code "7" indicates that a single UTF-8 character coded by a single byte follows. Note that only a very limited number of characters can be coded as a single UTF-8 byte. Therefore only the Unicode characters with code point U+0000 to U+007F can be coded. This is equivalent to the 7-bit US-ASCII table, as the characters 0x80 to 0xFF are used as continuation characters for multi-byte UTF-8 encodings. As an example, suppose we want to code the "less than" character (0x3C):

<pre>
| 0x07 | 0x3C |
</pre>

This encoding is the same for big-endian and little-endian.
<br/>


## 8. Character (UTF-16)

Code "8" indicates that a single UTF-16 character coded by two bytes follows, using the endianness for the order of the two bytes. Note that not all characters can be coded as a single UTF-16 character. Therefore only the Unicode characters with code point U+0000 to U+07FF can be coded. This includes most of the Latin script characters and diacritical marks, Greek, Cyrillic, Hebrew and Arabic, amongst others. As an example, suppose we want to code the "cent" character (U+00A2, 0xC2 0xA2) using *big-endian* encoding:

<pre>
| 0x08 | 0xC2 | 0xA2 |
</pre>

In *little-endian* encoding, this would be:

<pre>
| 0x08 | 0xA2 | 0xC2 |
</pre>
<br/>


!!! Note 
    Note that the UTF-8 and UTF-16 characters offer only limited possiblities. a single UTF-16 character can, for instance, not code a Euro symbol, as it needs three bytes (U+20AC). When  such characters are expected, using a UTF-8 or UTF-16 String (code #9 or code #10) is a much better solution.

!!! Warning
    For a discussion on little and  big endianness for UTF-8 and UTF-16 strings, see the following discussion at StackExchange: [https://stackoverflow.com/questions/3833693/isn-t-on-big-endian-machines-utf-8s-byte-order-different-than-on-little-endian](https://stackoverflow.com/questions/3833693/isn-t-on-big-endian-machines-utf-8s-byte-order-different-than-on-little-endian), as well as [https://unicode.org/faq/utf_bom.html#utf8-2](https://unicode.org/faq/utf_bom.html#utf8-2) and [https://unicode.org/faq/utf_bom.html#gen6](https://unicode.org/faq/utf_bom.html#gen6)

