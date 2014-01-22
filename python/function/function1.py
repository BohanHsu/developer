#default value on a mutable object
def f(a,L=[]):
	#what L look like before append operation
	print('L before append')
	print(L)
	L.append(a)
	return L
	
print(f(1))
print(f(2))


#default value on not mutable value evaluate only once
def f2(a,num = 0):
	num = num + a
	return num
	
print(f2(1))
print(f2(2))

#test arbitrary arguments
def concat(*args, sep='/'):
	return sep.join
	
#unpacking argument lists
args=[3,6]
resultList = list(range(*args))
print(resultList)
#do more tests
myargs = [2,8]
listFromMyArgs = list(range(*myargs))
print(listFromMyArgs)