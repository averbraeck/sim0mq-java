# Matrix types with unit (#29 - #32)

## 29a. Float matrix with unit (big endian)

After the byte with value 29, the matrix types have a 32-bit big-endian int indicating the number of rows in 
the matrix that follows, followed by a 32-bit big-endian int indicating the number of columns. These integers 
are not preceded by a byte indicating it is an int. Then a one-byte unit type follows 
(see the table in [Coding of units](../coding-units) ) and a one-byte display type 
(see [Display types for units](../display-types)). The internal storage of the values that are 
transmitted after that always use the SI (or standard) unit. Summarized, the coding is as follows
(assuming *big-endian* encoding):

<pre>
|29|R|O|W|S|C|O|L|S|UT|DT|
|R|1|C|1|R|1|C|2| ... |R|1|C|n|
|R|2|C|1|R|2|C|2| ... |R|2|C|n|
...
|R|m|C|1|R|m|C|2| ... |R|m|C|n|
</pre>

In the language sending or receiving a matrix, the rows are denoted by the first index, and the
columns by the second index: matrix\[row\]\[col\].


## 29b. Float matrix with unit (little endian)

After the byte with value 29, the matrix types have a 32-bit little-endian int indicating the number of rows in 
the matrix that follows, followed by a 32-bit little-endian int indicating the number of columns. These integers 
are not preceded by a byte indicating it is an int. Then a one-byte unit type follows 
(see the table in [Coding of units](../coding-units) ) and a one-byte display type 
(see [Display types for units](../display-types)). The internal storage of the values that are 
transmitted after that always use the SI (or standard) unit. Summarized, the coding is as follows
(assuming *little-endian* encoding):

<pre>
|29|R|O|W|S|C|O|L|S|UT|DT|
|R|1|C|1|R|1|C|2| ... |R|1|C|n|
|R|2|C|1|R|2|C|2| ... |R|2|C|n|
...
|R|m|C|1|R|m|C|2| ... |R|m|C|n|
</pre>

In the language sending or receiving a matrix, the rows are denoted by the first index, and the
columns by the second index: matrix\[row\]\[col\].


## 30a. Double matrix with unit (big endian)

After the byte with value 30, the matrix types have a 32-bit big endian int indicating the number of rows in 
the matrix that follows, followed by a 32-bit big endian int indicating the number of columns. These integers 
are not preceded by a byte indicating it is an int. Then a one-byte unit type follows 
(see the table in [Coding of units](../coding-units) ) and a one-byte display type 
(see [Display types for units](../display-types)). The internal storage of the values that are 
transmitted after that always use the SI (or standard) unit. Summarized, the coding is as follows (assuming *big-endian* encoding):

<pre>
|30|R|O|W|S|C|O|L|S|UT|DT|
|R|1|C|1|.|.|.|.| |R|1|C|2|.|.|.|.| ... |R|1|C|n|.|.|.|.|
|R|2|C|1|.|.|.|.| |R|2|C|2|.|.|.|.| ... |R|2|C|n|.|.|.|.|
...
|R|m|C|1|.|.|.|.| |R|m|C|2|.|.|.|.| ... |R|m|C|n|.|.|.|.|
</pre>

In the language sending or receiving a matrix, the rows are denoted by the outer index, and the
columns by the inner index: matrix\[row\]\[col\].


## 30b. Double matrix with unit (little endian)

After the byte with value 30, the matrix types have a 32-bit little endian int indicating the number of rows in 
the matrix that follows, followed by a 32-bit little endian int indicating the number of columns. These integers 
are not preceded by a byte indicating it is an int. Then a one-byte unit type follows 
(see the table in [Coding of units](../coding-units) ) and a one-byte display type 
(see [Display types for units](../display-types)). The internal storage of the values that are 
transmitted after that always use the SI (or standard) unit. Summarized, the coding is as follows (assuming *little-endian* encoding):

<pre>
|30|R|O|W|S|C|O|L|S|UT|DT|
|R|1|C|1|.|.|.|.| |R|1|C|2|.|.|.|.| ... |R|1|C|n|.|.|.|.|
|R|2|C|1|.|.|.|.| |R|2|C|2|.|.|.|.| ... |R|2|C|n|.|.|.|.|
...
|R|m|C|1|.|.|.|.| |R|m|C|2|.|.|.|.| ... |R|m|C|n|.|.|.|.|
</pre>

In the language sending or receiving a matrix, the rows are denoted by the outer index, and the
columns by the inner index: matrix\[row\]\[col\].


## 31a. Float matrix with unique units per column (big endian)

After the byte with value 31, the matrix types have a 32-bit big endian int indicating the number of rows in 
the matrix that follows, followed by a 32-bit big endian int indicating the number of columns. These integers 
are not preceded by a byte indicating it is an int. Then a one-byte unit type for column 1 follows 
(see the table in [Coding of units](../coding-units) ) and a one-byte display type for column 1 
(see  [Display types for units](../display-types)). Then the unit type and display type for 
column 2, etc. The internal storage of the values that are transmitted after that always use the SI 
(or standard) unit. Summarized, the coding is as follows:

<pre>
|31|R|O|W|S|C|O|L|S|
|UT1|DT1|UT2|DT2| ... |UTn|DTn|
|R|1|C|1|R|1|C|2| ... |R|1|C|n|
|R|2|C|1|R|2|C|2| ... |R|2|C|n|
...
|R|m|C|1|R|m|C|2| ... |R|m|C|n|
</pre>

In the language sending or receiving a matrix, the rows are denoted by the first index, and the
columns by the second index: matrix\[row\]\[col\].

This data type is ideal for, for instance, sending a time series of values, where column1 indicates 
the time, and column 2 the value. Suppose that we have a time series of 4 values at t = {1, 2, 3, 
4} hours and dimensionless values v = {20.0, 40.0, 50.0, 60.0}, then the coding is as follows (assuming *big-endian* encoding):

<pre>
|31| |0|0|0|4| |0|0|0|2|
|26|8| |0|0|
|0x3F|0x80|0x00|0x00| |0x41|0xA0|0x00|0x00|
|0x40|0x00|0x00|0x00| |0x42|0x20|0x00|0x00|
|0x40|0x00|0x40|0x00| |0x42|0x48|0x00|0x00|
|0x40|0x80|0x00|0x00| |0x42|0x70|0x00|0x00|
</pre>
<br>


## 31b. Float matrix with unique units per column (little endian)

After the byte with value 31, the matrix types have a 32-bit little endian int indicating the number of rows in 
the matrix that follows, followed by a 32-bit little endian int indicating the number of columns. These integers 
are not preceded by a byte indicating it is an int. Then a one-byte unit type for column 1 follows 
(see the table in [Coding of units](../coding-units) ) and a one-byte display type for column 1 
(see  [Display types for units](../display-types)). Then the unit type and display type for 
column 2, etc. The internal storage of the values that are transmitted after that always use the SI 
(or standard) unit. Summarized, the coding is as follows:

<pre>
|31|R|O|W|S|C|O|L|S|
|UT1|DT1|UT2|DT2| ... |UTn|DTn|
|R|1|C|1|R|1|C|2| ... |R|1|C|n|
|R|2|C|1|R|2|C|2| ... |R|2|C|n|
...
|R|m|C|1|R|m|C|2| ... |R|m|C|n|
</pre>

In the language sending or receiving a matrix, the rows are denoted by the first index, and the
columns by the second index: matrix\[row\]\[col\].

This data type is ideal for, for instance, sending a time series of values, where column1 indicates 
the time, and column 2 the value. Suppose that we have a time series of 4 values at t = {1, 2, 3, 
4} hours and dimensionless values v = {20.0, 40.0, 50.0, 60.0}, then the coding is as follows (assuming *little-endian* encoding):

<pre>
|31| |4|0|0|0| |2|0|0|0|
|26|8| |0|0|
|0x00|0x00|0x80|0x3F| |0x00|0x00|0xA0|0x41|
|0x00|0x00|0x00|0x40| |0x00|0x00|0x20|0x42|
|0x00|0x40|0x00|0x40| |0x00|0x00|0x48|0x42|
|0x00|0x00|0x80|0x40| |0x00|0x00|0x70|0x42|
</pre>
<br>


## 32a. Double matrix with unique units per column (big endian)

After the byte with value 32, the matrix types have a 32-bit big endian int indicating the number of rows in 
the array that follows, followed by a 32-bit big endian int indicating the number of columns. These integers 
are not preceded by a byte indicating it is an int. Then a one-byte unit type for column 1 follows 
(see the table in [Coding of units](../coding-units) ) and a one-byte display type for column 1 
(see [Display types for units](../display-types)). Then the unit type and display type for 
column 2, etc. The internal storage of the values that are transmitted after that always use the SI 
(or standard) unit. Summarized, the coding is as follows:

<pre>
|32| |R|O|W|S| |C|O|L|S|
|UT1|DT1| |UT2|DT2| ... |UTn|DTn|
|R|1|C|1|.|.|.|.| |R|1|C|2|.|.|.|.| ... |R|1|C|n|.|.|.|.|
|R|2|C|1|.|.|.|.| |R|2|C|2|.|.|.|.| ... |R|2|C|n|.|.|.|.|
...
|R|m|C|1|.|.|.|.| |R|m|C|2|.|.|.|.| ... |R|m|C|n|.|.|.|.|
</pre>

In the language sending or receiving a matrix, the rows are denoted by the outer index, and the
columns by the inner index: matrix\[row\]\[col\].

This data type is ideal for, for instance, sending a time series of values, where column1 indicates 
the time, and column 2 the value. Suppose that we have a time series of 4 values at dimensionless 
years {2010, 2011, 2012, 2013} and costs of dollars per acre (#0x348) of {415.7, 423.4, 428.0, 435.1}, 
then the coding is as follows (assuming *big-endian* encoding):

<pre>
|32| |0|0|0|4| |0|0|0|2|
|0|0| |101|0x03|0x48|18|
|0x40|0x9F|0x68|0x00|0x00|0x00|0x00|0x00|
|0x40|0x79|0xFB|0x33|0x33|0x33|0x33|0x33|
|0x40|0x9F|0x6C|0x00|0x00|0x00|0x00|0x00|
|0x40|0x7A|0x76|0x66|0x66|0x66|0x66|0x66|
|0x40|0x9F|0x70|0x00|0x00|0x00|0x00|0x00|
|0x40|0x7A|0xC0|0x00|0x00|0x00|0x00|0x00|
|0x40|0x9F|0x74|0x00|0x00|0x00|0x00|0x00|
|0x40|0x7A|0x91|0x99|0x99|0x99|0x99|0x9A|
</pre>


## 32b. Double matrix with unique units per column (little endian)

After the byte with value 32, the matrix types have a 32-bit little endian int indicating the number of rows in 
the array that follows, followed by a 32-bit little endian int indicating the number of columns. These integers 
are not preceded by a byte indicating it is an int. Then a one-byte unit type for column 1 follows 
(see the table in [Coding of units](../coding-units) ) and a one-byte display type for column 1 
(see [Display types for units](../display-types)). Then the unit type and display type for 
column 2, etc. The internal storage of the values that are transmitted after that always use the SI 
(or standard) unit. Summarized, the coding is as follows:

<pre>
|32| |R|O|W|S| |C|O|L|S|
|UT1|DT1| |UT2|DT2| ... |UTn|DTn|
|R|1|C|1|.|.|.|.| |R|1|C|2|.|.|.|.| ... |R|1|C|n|.|.|.|.|
|R|2|C|1|.|.|.|.| |R|2|C|2|.|.|.|.| ... |R|2|C|n|.|.|.|.|
...
|R|m|C|1|.|.|.|.| |R|m|C|2|.|.|.|.| ... |R|m|C|n|.|.|.|.|
</pre>

In the language sending or receiving a matrix, the rows are denoted by the outer index, and the
columns by the inner index: matrix\[row\]\[col\].

This data type is ideal for, for instance, sending a time series of values, where column1 indicates 
the time, and column 2 the value. Suppose that we have a time series of 4 values at dimensionless 
years {2010, 2011, 2012, 2013} and costs of dollars per acre (#0x348) of {415.7, 423.4, 428.0, 435.1}, 
then the coding is as follows (assuming *little-endian* encoding):

<pre>
|32| |4|0|0|0| |2|0|0|0|
|0|0| |101|0x03|0x48|18|
|0x00|0x00|0x00|0x00|0x00|0x68|0x9F|0x40|
|0x33|0x33|0x33|0x33|0x33|0xFB|0x79|0x40|
|0x00|0x00|0x00|0x00|0x00|0x6C|0x9F|0x40|
|0x66|0x66|0x66|0x66|0x66|0x76|0x7A|0x40|
|0x00|0x00|0x00|0x00|0x00|0x70|0x9F|0x40|
|0x00|0x00|0x00|0x00|0x00|0xC0|0x7A|0x40|
|0x00|0x00|0x00|0x00|0x00|0x74|0x9F|0x40|
|0x9A|0x99|0x99|0x99|0x99|0x91|0x7A|0x40|
</pre>

