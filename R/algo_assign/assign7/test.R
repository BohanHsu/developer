cvec <- c(1800,600,600)
names(cvec) <- c("Milk","Bulls","Pigs")
bvec <- c(40,90,2500)
names(bvec) <- c("Land","Stable","Labor")
Amat <- rbind( c( 0.7, 0.35, 0 ),
     	       c( 1.5, 1, 3 ),
	       c( 50, 12.5, 20 ))

print(linprog::solveLP(cvec,bvec,Amat,maximum=TRUE))