javastuff
=============

Various code in core java


Sum comparison
-------
We add numbers and compare doubles, longs, BigDecimals with and without decimals. Same thing for streams and parallel streams of doubles and longs, with and without decimals.

For big decimal value is 19999999.8
Total time = 741 ms
For big decimal value without decimals is 199999998
Total time = 680 ms
For object Double value is 1.9999999762258902E7
Total time = 333 ms
For object Long value is 199999998
Total time = 275 ms
For primitive double value is 1.9999999762258902E7
Total time = 270 ms
For double primitive value without decimals is 1.99999998E8
Total time = 270 ms
For double stream the value is 1.9999999762258902E7
Total time = 179 ms
For parallel long stream without decimals the value is 199999998
Total time = 58 ms
For parallel double stream without decimals the value is 1.99999998E8
Total time = 54 ms
For long primitive value without decimals is 199999998
Total time = 37 ms
For long stream without decimals the value is 199999998
Total time = 16 ms



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