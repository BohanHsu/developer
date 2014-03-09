## This R program to solve assignment problem 5

### Maximize a + 3b + 2c + 4d - 6e + 5f such that all variables are nonnegative and satisfy the following requirements:
### 2a - 6b + 6c + 2d - e + 8f = 11
### -4a + 2b + c + 2d + 7e - 2f ≥ 6
### -a + 5b - c - 4d + e + 7f = 5
### 3a - 4b + 3c + 3d + 3e - f ≤ 9

## To format this problem:
# 2a + -6b + 6c + 2d + -e + 8f <= 11
# -2a + 6b + -6c + -2d + e + -8f <= -11
# 4a + -2b + -c + -2d + -7e + 2f <= -6
# -a + 5b + -c + -4d + e + 7f <= 5
# a + -5b + c + 4d + -e + -7f <= -5
# 3a + -4b + 3c + 3d + 3e + -f <= 9

# And the object function is:
# maximize: a + 3b + 2c + 4d + -6e + 5f

# the R code of this problem is:
cvec <- c(1,3,2,4,-6,5)
names(cvec) <- c("a","b","c","d","e","f")

bvec <- c(11,-11,-6,5,-5,9)
names(bvec) <- c("const1","const2","const3","const4","const5","const6")

Amat <- rbind( c(2,-6,6,2,-1,8),
     	       c(-2,6,-6,-2,1,-8),
	       c(4,-2,-1,-2,-7,2),
	       c(-1,5,-1,-4,1,7),
	       c(1,-5,1,4,-1,-7),
	       c(3,-4,3,3,3,-1))

res <- linprog::solveLP(cvec,bvec,Amat,TRUE)
print(res)