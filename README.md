javastuff
=============

Various code in core java




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

![Alt Text](https://github.com/benoitantelme/javastuff/raw/master/javastuff/resources/matrixMultiplicationTime.png)
We can see that while the single threaded method is faster here for n < 128. Then it becomes way slower than the concurrent one.