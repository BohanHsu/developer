# this is for solving pob5

# the objective function 
cvec <- c(1,22,6,25)
names(cvec) <- c("a","b","c","d")

bvec <- c(240,230,110)
names(bvec) <- c("c1","c2","c3")

Amat <- rbind( c(21,6,10,7),
     	       c(11,17,13,23),
	       c(9,25,14,0))

res <- linprog::solveLP(cvec,bvec,Amat,TRUE)

print(res)
