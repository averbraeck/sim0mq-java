# String types (#9 - #10)

## 9. UTF-8 String

After the code "9" that indicates that a UTF-8 string follows, the **number of bytes needed to code the string** is provided with a 32-bit int (using the endianness). This int is itself **not** preceded by a byte indicating it is an int. An ASCII string "Hello" is therefore coded as follows:

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

* &copy; copyright sign: `| 0xC2 | 0xA9 |`
* &xi; greek letter xi: `| 0xCE | 0xBE |`
* &#x2030; per mille sign: `| 0xE2 | 0x80 | 0xB0 |`
* &#x1F60A; smiley: `| 0xF0 | 0x9F | 0x98 | 0x80 |`
<br/>


## 10. UTF-16 String

After the code "10" that indicates that a UTF-16BE or a UTF-16LE string follows, the **number of UTF-16 characters** is provided with a 32-bit int (using the chosen endianness). This int is itself **not** preceded by a byte indicating it is an int. An ASCII string "abc" is therefore coded as follows:

<pre>
| 0x0A | 0x00 | 0x00 | 0x00 | 0x03 | 0x00 | 0x61 | 0x00 | 0x62 | 0x00 | 0x63 |
</pre><br/>

!!! Note 
    Note that the number of UTF-16 characters is indicated in the 32-bit integer and not the number of bytes.

!!! Note
    Note  that it can take two UTF-16 sequences to code one resulting character. Depending on the character, two or four bytes are used to code one character. When four bytes are needed, this is indicated as two UTF-16 characters in the count. Therefore, the number of bytes to code the string is exactly equal to two times the the number of UTF-16 characters. The number of resulting characters when deserializing can be less.

Examples: 

* &copy; copyright sign: `| 0x00 | 0xA9 |`
* &xi; greek letter xi: `| 0x03 | 0xBE |`
* &#x2030; per mille sign: `| 0x20 | 0x30 |`
* &#x1F60A; smiley: `| 0xD8 | 0x3D | 0xDE | 0x00 |`


!!! Warning
    For a discussion on little and  big endianness for UTF-8 and UTF-16 strings, see the following discussion at StackExchange: [https://stackoverflow.com/questions/3833693/isn-t-on-big-endian-machines-utf-8s-byte-order-different-than-on-little-endian](https://stackoverflow.com/questions/3833693/isn-t-on-big-endian-machines-utf-8s-byte-order-different-than-on-little-endian), as well as [https://unicode.org/faq/utf_bom.html#utf8-2](https://unicode.org/faq/utf_bom.html#utf8-2) and [https://unicode.org/faq/utf_bom.html#gen6](https://unicode.org/faq/utf_bom.html#gen6)