# for alg hw9 pob3

# declare the vector
v <- c (0,0,0,1)

# declare the matrix

# what the matrix looks like

# 0.1	0.2	0.4	0.3
# 0	0.2	0.7	0.1
# 0.4	0	0.4	0.2
# 0.5	0.3	0.1	0.1

m <- rbind(   c(0.1,	0.2,	0.4,	0.3),
     	      c(0,	0.2,	0.7,	0.1),
     	      c(0.4,	0,	0.4,	0.2),
     	      c(0.5,	0.3,	0.1,	0.1))
     
#v <- v %*% m	     
#print(v)

#v <- v %*% m	     
#print(v)

print(v)

i <- 0

repeat{
	v1 <- v %*% m
	print(i)
	i <- i+1
	print(v1)
	if(identical(v,v1)){
		break
	}
	v <- v1
}