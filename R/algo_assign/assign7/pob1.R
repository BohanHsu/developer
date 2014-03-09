## Formulation the problem:
# Xi represent unit of fertilizer-i

# constraints
# 0.1 *  x1 + 0.2 * x2 + 0.15 * x3 <= 160
# 0.1 *  x1 + 0.05 * x2 + 0.15 * x3 <= 40
# 0.1 *  x1 + 0.15 * x2 + 0.05 * x3 <= 80
# 0.05 *  x1 + 0.1 * x2 + 0.05 * x3 <= 20

# object function
# maximum of:
# 100 * x1 + 150 * x2 + 50 * x3

# using linprog to solve this problem:

# object function
cvec <- c(100, 150, 50)
names(cvec) <- c("fertilizer1","fertilizer2","fertilizer3")

# constraints
bvec <- c(160,40,80,20)
names(bvec) <- c("Nitrogen","Phosphorus","Potassium","Sullfur")

# matrix A
Amat <- rbind(	c(0.1, 0.2, 0.15),
     		c(0.1, 0.05, 0.15),
		c(0.1, 0.15, 0.05),
		c(0.05, 0.1, 0.05))

# maximize the object function
res <- linprog::solveLP(cvec,bvec,Amat,TRUE)
print(res)