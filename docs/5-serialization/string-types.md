# String types (#9 - #10 and #33 - #36)

## 9. UTF-8 Big-endian String

After the code "9" that indicates that a UTF-8 string follows, the **number of bytes needed to code the string** is provided with a 32-bit int (using big-endian encoding). This int is itself **not** preceded by a byte indicating it is an int. An ASCII string "Hello" is therefore coded as follows:

<pre>
| 9 | 0 | 0 | 0 | 5 | H | e | l | l | o |
</pre>

or

<pre>
| 0x09 | 0x00 | 0x00 | 0x00 | 0x05 | 0x48 | 0x65 | 0x6C | 0x6C | 0x6F |
</pre>

in hex.

!!! Note 
    Note that it can take multiple bytes to code one character in UTF-8. Depending on the character, one to four bytes are used to code one character. Therefore, the number of bytes to code the string is NOT equal to the number of characters that will be deserialized.

Examples: 

* &copy; copyright sign: `| 0xC2 | 0xA9 |` (2 bytes, one character)
* &xi; greek letter xi: `| 0xCE | 0xBE |` (2 bytes, one character)
* &#x2030; per mille sign: `| 0xE2 | 0x80 | 0xB0 |` (3 bytes, one character)
* &#x1F60A; smiley: `| 0xF0 | 0x9F | 0x98 | 0x80 |` (4 bytes, one character)
<br/>


## 10. UTF-16 Big-endian String

After the code "10" that indicates that a UTF-16BE string follows, the **number of UTF-16 characters (2-byte shorts)** is provided with a 32-bit int (using big-endian encoding). This int is itself **not** preceded by a byte indicating it is an int. An ASCII string "abc" is therefore coded as follows:

<pre>
| 0x0A | 0x00 | 0x00 | 0x00 | 0x03 | 0x00 | 0x61 | 0x00 | 0x62 | 0x00 | 0x63 |
</pre><br/>

!!! Note 
    Note that the number of UTF-16 characters (2-byte shorts) is indicated in the 32-bit integer and not the number of bytes, nor the number of characters in the string.

!!! Note
    Note that it can take two UTF-16 sequences to code one resulting character. Depending on the character, two or four bytes are used to code one character. When four bytes are needed, this is indicated as two UTF-16 characters in the count. Therefore, the number of bytes to code the string is exactly equal to two times the the number of UTF-16 characters. The number of resulting characters when deserializing can be less than the number of decoded UTF-16 characters.

Examples: 

* &copy; copyright sign: `| 0x00 | 0xA9 |` (1 short, one character)
* &xi; greek letter xi: `| 0x03 | 0xBE |` (1 short, one character)
* &#x2030; per mille sign: `| 0x20 | 0x30 |` (1 short, one character)
* &#x1F60A; smiley: `| 0xD8 | 0x3D | 0xDE | 0x00 |` (2 shorts, one character)


!!! Warning
    For a discussion on little and  big endianness for UTF-8 and UTF-16 strings, see the following discussion at StackExchange: [https://stackoverflow.com/questions/3833693/isn-t-on-big-endian-machines-utf-8s-byte-order-different-than-on-little-endian](https://stackoverflow.com/questions/3833693/isn-t-on-big-endian-machines-utf-8s-byte-order-different-than-on-little-endian), as well as [https://unicode.org/faq/utf_bom.html#utf8-2](https://unicode.org/faq/utf_bom.html#utf8-2) and [https://unicode.org/faq/utf_bom.html#gen6](https://unicode.org/faq/utf_bom.html#gen6)


## 33. UTF-8 Big-endian string array

Array of UTF-8 Strings. The number of strings is provided in a 32-bit big-endian integer. Each string is preceded by a
32-bit int indicating the number of bytes in the array that follows. This int is itself not preceded by a byte indicating
it is an int. As an example, coding two series for a graph is done as follows:

<pre>
| 33 | 0 | 0 | 0 | 2 | 
| 0 | 0 | 0 | 7 | S | e | r | i | e | s | 1 | 
| 0 | 0 | 0 | 7 | S | e | r | i | e | s | 2 |
</pre>

!!! Note
    Note that the int to code the length for each string indicates the number of bytes, not the number of characters.


## 34. UTF-16 Big-endian string array

Array of UTF-16 Strings. The number of strings is provided in a 32-bit big-endian integer. Each string is preceded by a
32-bit int indicating the number of shorts (2-byte UTF-16 encoding) in the array that follows. This int is itself not
preceded by a byte indicating it is an int. As an example, coding two series
for a graph is done as follows:

<pre>
|0x22|0x00|0x00|0x00|0x02|
|0x00|0x00|0x00|0x07|0x00|0x53|0x00|0x65|0x00|0x72|0x00|0x69|0x00|0x65|0x00|0x73|0x00|0x31|
|0x00|0x00|0x00|0x07|0x00|0x53|0x00|0x65|0x00|0x72|0x00|0x69|0x00|0x65|0x00|0x73|0x00|0x32|
</pre>

!!! Note
    Note that the int to code the length for each string indicates the number of shorts, not the number of characters in the original string, nor the number of bytes.


## 35. UTF-8 Big-endian string matrix

Matrix of UTF-8 Strings. First, the number of rows is provided in a 32-bit big-endian integer, followed by the number of
columns encoded in a 32-bit big-endian integer. Each string is preceded by a 32-bit int indicating the number of bytes in
the array that follows. This int is itself not preceded by a byte indicating it is an int. The strings are provided
row-by-row. In general, the coding is as follows:

<pre>
| 35 | R | O | W | S | C | O | L | S | 
|  0 | 0 | 0 | 4 | R | 1 | C | 1 | 
|  0 | 0 | 0 | 4 | R | 1 | C | 2 |
...
|  0 | 0 | 0 | 4 | R | 1 | C | n | 
|  0 | 0 | 0 | 4 | R | 2 | C | 1 |
|  0 | 0 | 0 | 4 | R | 2 | C | 2 |
...
|  0 | 0 | 0 | 4 | R | m | C | n |
</pre>

!!! Note
    Note that the int to code the length for each string indicates the number of bytes, not the number of characters. 


## 36. UTF-16 Big-endian string matrix

Matrix of UTF-16 Strings. First, the number of rows is provided in a 32-bit big-endian integer, followed by the number of
columns encoded in a 32-bit big-endian integer. Each string is preceded by a 32-bit int indicating the number of bytes in
the array that follows. This int is itself not preceded by a byte indicating it is an int. The strings are provided
row-by-row. In general, the coding is as follows:

<pre>
| 36 | R | O | W | S | C | O | L | S | 
|  0 | 0 | 0 | 4 | . | R | . | 1 | . | C | . | 1 | 
|  0 | 0 | 0 | 4 | . | R | . | 1 | . | C | . | 2 |
...
|  0 | 0 | 0 | 4 | . | R | . | 1 | . | C | . | n | 
|  0 | 0 | 0 | 4 | . | R | . | 2 | . | C | . | 1 | 
|  0 | 0 | 0 | 4 | . | R | . | 2 | . | C | . | 2 | 
...
|  0 | 0 | 0 | 4 | . | R | . | m | . | C | . | n |
</pre>

!!! Note
    Note that the int to code the length for each string indicates the number of shorts (2-bytes), not the number of characters in the original string, nor the number of bytes in the encoding.


## 137. UTF-8 Little-endian String

After the code 137 (-119) that indicates that a UTF-8 string follows using little-endian encoding, the **number of bytes needed to code the string** is provided with a 32-bit int (using little endian encoding). This int is itself **not** preceded by a byte indicating it is an int. An ASCII string "Hello" is therefore coded as follows:

<pre>
| 137 | 5 | 0 | 0 | 0 | H | e | l | l | o |
</pre>

or

<pre>
| 0x89 | 0x05 | 0x00 | 0x00 | 0x00 | 0x48 | 0x65 | 0x6C | 0x6C | 0x6F |
</pre>

in hex.

!!! Note 
    Note that it can take multiple bytes to code one character in UTF-8. Depending on the character, one to four bytes are used to code one character. Therefore, the number of bytes to code the string is NOT equal to the number of characters that will be deserialized.

Examples: 

* &copy; copyright sign: `| 0xC2 | 0xA9 |` (2 bytes, one character)
* &xi; greek letter xi: `| 0xCE | 0xBE |` (2 bytes, one character)
* &#x2030; per mille sign: `| 0xE2 | 0x80 | 0xB0 |` (3 bytes, one character)
* &#x1F60A; smiley: `| 0xF0 | 0x9F | 0x98 | 0x80 |` (4 bytes, one character)
<br/>


## 138. UTF-16 Little-endian String

After the code 138 (-118) that indicates that a UTF-16LE string follows, the **number of UTF-16 characters (2-byte shorts)** is provided with a 32-bit int (using little-endian encoding). This int is itself **not** preceded by a byte indicating it is an int. An ASCII string "abc" is therefore coded as follows:

<pre>
| 0x8A | 0x03 | 0x00 | 0x00 | 0x00 | 0x61 | 0x00 | 0x62 | 0x00 | 0x63 | 0x00 |
</pre><br/>

!!! Note 
    Note that the number of UTF-16 characters (2-byte shorts) is indicated in the 32-bit integer and not the number of bytes, nor the number of characters in the string.

!!! Note
    Note that it can take two UTF-16 sequences to code one resulting character. Depending on the character, two or four bytes are used to code one character. When four bytes are needed, this is indicated as two UTF-16 characters in the count. Therefore, the number of bytes to code the string is exactly equal to two times the the number of UTF-16 characters. The number of resulting characters when deserializing can be less than the number of decoded UTF-16 characters.

Examples: 

* &copy; copyright sign: `| 0xA9 | 0x00 |` (1 short, one character)
* &xi; greek letter xi: `| 0xBE | 0x03 |` (1 short, one character)
* &#x2030; per mille sign: `| 0x30 | 0x20 |` (1 short, one character)
* &#x1F60A; smiley: `| 0x3D | 0xD8 | 0x00 | 0xDE |` (2 shorts, one character)


## 161. UTF-8 Little-endian String array

The code 161 (-95) indicates a little-endian array of UTF-8 Strings. The number of strings is provided in a 32-bit little-endian integer. Each
string is preceded by a 32-bit int indicating the number of bytes in the array that follows. This int is itself not
preceded by a byte indicating it is an int. As an example, coding two series for a graph is done as follows:

<pre>
| 33 | 2 | 0 | 0 | 0 | 
| 7 | 0 | 0 | 0 | S | e | r | i | e | s | 1 | 
| 7 | 0 | 0 | 0 | S | e | r | i | e | s | 2 |
</pre>

!!! Note
    Note that the int to code the length for each string indicates the number of bytes, not the number of characters. 


## 162. UTF-16 Little-endian String array

The code 162 (-94) indicates a little-endian array of UTF-16 Strings. The number of strings is provided in a 32-bit little-endian integer.
Each string is preceded by a 32-bit int indicating the number of shorts (2-byte UTF-16 encoding) in the array that
follows. This int is itself not preceded by a byte indicating it is an int. As an example, coding two series for a graph is done as follows:

<pre>
|0x22|0x02|0x00|0x00|0x00|
|0x07|0x00|0x00|0x00|0x53|0x00|0x65|0x00|0x72|0x00|0x69|0x00|0x65|0x00|0x73|0x00|0x31|0x00|
|0x07|0x00|0x00|0x00|0x53|0x00|0x65|0x00|0x72|0x00|0x69|0x00|0x65|0x00|0x73|0x00|0x32|0x00|
</pre>

!!! Note
    Note that the int to code the length for each string indicates the number of shorts, not the number of characters in the original string, nor the number of bytes. 


## 163. UTF-8 Little-endian String matrix

The code 163 (-93) indicates a little-endian matrix of UTF-8 Strings. First, the number of rows is provided in a 32-bit little-endian integer,
followed by the number of columns encoded in a 32-bit little-endian integer. Each string is preceded by a 32-bit int
indicating the number of bytes in the array that follows. This int is itself not preceded by a byte indicating it is an
int. The strings are provided row-by-row. In general, the coding is as follows:

<pre>
| 35 | R | O | W | S | C | O | L | S |
|  4 | 0 | 0 | 0 | R | 1 | C | 1 | 
|  4 | 0 | 0 | 0 | R | 1 | C | 2 |
...
|  4 | 0 | 0 | 0 | R | 1 | C | n | 
|  4 | 0 | 0 | 0 | R | 2 | C | 1 |
|  4 | 0 | 0 | 0 | R | 2 | C | 2 |
...
|  4 | 0 | 0 | 0 | R | m | C | n |
</pre>

!!! Note
    Note that the int to code the length for each string indicates the number of bytes, not the number of characters. 


## 164. UTF-16 Little-endian String matrix

The code 164 (-92) indicates a little-endian matrix of UTF-16 Strings. First, the number of rows is provided in a 32-bit little-endian
integer, followed by the number of columns encoded in a 32-bit little-endian integer. Each string is preceded by a 32-bit
int indicating the number of bytes in the array that follows. This int is itself not preceded by a byte indicating it is
an int. The strings are provided row-by-row. In general, the coding is as follows:

<pre>
| 36 | R | O | W | S | C | O | L | S | 
|  4 | 0 | 0 | 0 | R | . | 1 | . | C | . | 1 | . | 
|  4 | 0 | 0 | 0 | R | . | 1 | . | C | . | 2 | . |
...
|  4 | 0 | 0 | 0 | R | . | 1 | . | C | . | n | . |
|  4 | 0 | 0 | 0 | R | . | 2 | . | C | . | 1 | . |
|  4 | 0 | 0 | 0 | R | . | 2 | . | C | . | 2 | . |
...
|  4 | 0 | 0 | 0 | R | . | m | . | C | . | n | . |
</pre>

!!! Note
    Note that the int to code the length for each string indicates the number of shorts (2-bytes), not the number of characters in the original string, nor the number of bytes in the encoding. 
