from math import pow
from math import log

# fn fn -> integer
# given: two function
# returns: the ceiling of the crossover point 
# of the given two function
# 
def crossover(fn1,fn2,n):
	if fn1(n) == fn2(n):
		return n

	last1 = fn1(n) <= fn2(n)
	last2 = fn1(n) <= fn2(n)
	while last1 == last2:
		n = n+1
		last2 = last1
		last1 = fn1(n) <= fn2(n)


	return n

def fn1(n):
	return 10 * log(n,2)

def fn2(n):
	return pow(n,2) + n + 3

def fn3(n):
	return pow(2,n)

c12 = crossover(fn1,fn2,1)
c13 = crossover(fn1,fn3,1)
c23 = crossover(fn2,fn3,1)

print('c12=',c12)
print('c13=',c13)
print('c23=',c23)

c13 = crossover(fn1,fn2,2)
print(c13)
