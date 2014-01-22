#in this code we still study functions

#lambda function
def make_incrementor(n):
	return lambda x: x+n
	
f = make_incrementor(10)
print(f(1))

#in this way call a lambda directly
print(make_incrementor(20)(1))

#==========
#lambda as a argument:
#what is 
pairs = [(1,'one'),(2,'two'),(3,'three'),(4,'four')]
pairs.sort(key=lambda pair:pair[1])
print(pairs)
pairs.sort(key=lambda pair:pair[0])
print(pairs)

#still questions

#this part is for documentation

def my_fn2():
	"""Do nothing, but document it.
	
	No, really, it dosen't do anything.
	"""
	pass
	
print(my_fn2.__doc__)

