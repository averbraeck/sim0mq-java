# Array types (#11 - #17)

The array types are preceded by a 32-bit int indicating the number of values in the array that follows. This int is itself not preceded by a byte indicating it is an int. An array of 8 shorts with numbers 100 through 107 is therefore coded as follows:

<pre>
|12|0|0|0|8|
|0|100|0|101|0|102|0|103|0|104|0|105|0|106|0|107|
</pre>

In this illustration, the 21 bytes have been divided over two lines for readability. The serialized data may not contain such line breaks.