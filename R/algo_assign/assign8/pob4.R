# this is for solving pob4

# the objective function
cvec <- c(4,6,1,7,1,1)
names(cvec) <- c("a","b","c","d","e","f")

bvec <- c(5,3,8,7)
names(bvec) <- c("c1","c2","c3","c4")

Amat <- rbind(	c(5,-1,0,-1,7,8),
     		c(0,3,1,6,6,-2),
		c(0,5,7,0,-2,7),
		c(-3,0,-3,5,6,-3))

res <- linprog::solveLP(cvec,bvec,Amat,TRUE)

print(res)