## this R program is to solve dual problem of problem 5

# according to the generic of dual problem in textbook page 222:
#
# when primal LP is:
# max cTx
# under the constraint:
# Ax <= b
# x >= 0
# while the dual LP is:
# min yTb
# under the constraint:
# yTA >= cT
# y >= 0

# in the dual LP, we are going to minimize that:
# 11 * y1 + -11 * y2 + -6 * y3 + 5 * y4 + -5 * y5 + 9 * y6

# under the constraints of:
# y1 >= 0  
# y2 >= 0
# y3 >= 0
# y4 >= 0
# y5 >= 0
# y6 >= 0
# 2 * y1 + -2 * y2 + 4 * y3 + -1 * y4 + 1 * y5 + 3 * y6 >= 1
# -6 * y1 + 6 * y2 + -2 * y3 + 5 * y4 + -5 * y5 + -4 * y6 >= 3
# 6 * y1 + -6 * y2 + -1 * y3 + -1 * y4 + 1 * y5 + 3 * y6 >= 2
# 2 * y1 + -2 * y2 + -2 * y3 + -4 * y4 + 4 * y5 + 3 * y6 >= 4
# -1 * y1 + 1 * y2 + -7 * y3 + 1 * y4 + -1 * y5 + 3 * y6 >= -6 
# 8 * y1 + -8 * y2 + 2 * y3 + 7 * y4 + -7 * y5 + -1 * y6 >= 5

# express this problem in standard form:

#constraints:
# -2 * y1 + 2 * y2 + -4 * y3 + 1 * y4 + -1 * y5 + -3 * y6 <= -1
# 6 * y1 + -6 * y2 + 2 * y3 + -5 * y4 + 5 * y5 + 4 * y6 <= -3
# -6 * y1 + 6 * y2 + 1 * y3 + 1 * y4 + -1 * y5 + -3 * y6 <= -2
# -2 * y1 + 2 * y2 + 2 * y3 + 4 * y4 + -4 * y5 + -3 * y6 <= -4
# 1 * y1 + -1 * y2 + 7 * y3 + -1 * y4 + 1 * y5 + -3 * y6 <= 6 
# -8 * y1 + 8 * y2 + -2 * y3 + -7 * y4 + 7 * y5 + 1 * y6 <= -5

# object function:
# minimize: 11 * y1 + -11 * y2 + -6 * y3 + 5 * y4 + -5 * y5 + 9 * y6

# to solve this problem using linprog:

cvec <- c(11,-11,-6,5,-5,9)
names(cvec) <- c("y1","y2","y3","y4","y5","y6")

bvec <- c(-1,-3,-2,-4,6,-5)
names(bvec) <- c("b1","b2","b3","b4","b5","b6") 

Amat <- rbind( c(-2,2,-4,1,-1,-3),
     	       c(6,-6,2,-5,5,4),
	       c(-6,6,1,1,-1,-3),
	       c(-2,2,2,4,-4,-3),
	       c(1,-1,7,-1,1,-3),
	       c(-8,8,-2,-7,7,1))

res <- linprog::solveLP(cvec,bvec,Amat,FALSE)
print(res)