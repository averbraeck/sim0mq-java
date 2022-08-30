# Matrix types (#18 - #24)

The matrix types are preceded by a 32-bit int indicating the number of rows, followed by a 32-bit int indicating the number of columns. These integers are not preceded by a byte indicating it is an int. The number of values in the matrix that follows is rows * columns. The data is stored row by row, without a separator between the rows. A matrix with 2 rows and 3 columns of integers 1-2-4 6-7-8 is therefore coded as follows:

<pre>
|20|0|0|0|2|0|0|0|3|
|0|0|0|1|0|0|0|2|0|0|0|4|
|0|0|0|6|0|0|0|7|0|0|0|8|
</pre>

In this illustration, the 33 bytes have been divided over three lines for readability. The serialized data may not contain such line breaks.