javastuff
=============

Various code in core java


Serialization
-------
We compare serialization, externalization (using objects or handling arrays manually with indexes) and ByteBuffers with channels to write an object to a file.

|| Serialization | ''Manual'' Externalization | Externalization | ByteBuffer and Channel |
|--- | --- | --- | --- | --- |
| file size in bytes | 800154 | 800021 | 804110 | 199999998 |
| serialization time | 607 ms | 567 ms | 554 ms | 223 ms |
| de-serialization time | 214 ms | 357 ms | 144 ms | 87 ms |

Sum comparison
-------
We add numbers and compare doubles, longs, BigDecimals with and without decimals. Same thing for streams and parallel streams of doubles and longs, with and without decimals.

|| BigDecimal | BigDecimal w/o decimals | Double | Long | double | DoubleStream | Parallel LongStream w/o decimals | Parallel DoubleStream w/o decimals | long w/o decimals | LongStream w/o decimals |
|--- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
|value | 19999999.8 | 199999998 | 1.9999999762258902E7 | 199999998 | 1.9999999762258902E7 | 1.9999999762258902E7 | 199999998 | 1.99999998E8 | 199999998 | 199999998 |
|time | 741 ms | 680 ms | 333 ms | 275 ms | 270 ms | 179 ms | 58 ms | 54 ms | 37 ms | 16 ms |


Matrices multiplication time
-------
We multiply matrices of sizes 2^n using a single threaded method and a concurrent method.

The single threaded method uses this simple algorithm:
```
Input: matrices A and B, of sizes n and p with n == p here
C is a new matrix of the same size as A and B
For i from 1 to n:
  For j from 1 to p:
    sum = 0
    For k from 1 to m:
      sum = sum + Aik * Bkj
    Cij = sum
Return C
```

The concurrent method uses ForkJoinPool, Recursive Action and this algorithm to split matrices by dividing into quadrants:
```
Multiply matrices AxB by dividing into quadrants, using algorithm:
A      x      B     =     C                          

A11 | A12     B11 | B12     A11*B11 | A11*B12     A12*B21 | A12*B22 
|----+----| x |----+----| = |--------+--------| + |---------+-------|
A21 | A22     B21 | B21     A21*B11 | A21*B21     A22*B21 | A22*B22 
```

![Alt Text](https://github.com/benoitantelme/javastuff/raw/master/resources/matrixMultiplicationTime.png)

We can see that while the single threaded method is faster here for n < 128. Then it becomes way slower than the concurrent one.