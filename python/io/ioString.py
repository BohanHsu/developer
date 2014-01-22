#in this file we learn string in python

s = 'Hello, world.'
#what str() deal with this string
print(str(s))
#what repr() deal with this string
print(repr(s))

#repr() make a object to a string for the interpreter

print(str(1/7))
print(repr(1/7))

#deal with some transforming characters
hello = 'hello, world\n'
helloStr = str(hello)
helloRepr = repr(hello)
print(helloStr)
print(helloRepr)
#str() translate the '\n' to a new line.
#repr() ignore it

#how those two function deal with object
#this is a tuple!!!!
tuple = (32.5, 4000, ('spam', 'eggs'))
print(str(tuple))
print(repr(tuple))
#ok expect string str() is just same as repr()...


#using print and formatting string
for x in range(1, 11):
	print(repr(x).rjust(2), repr(x*x).rjust(3),end = '...')
	print(repr(x*x*x).rjust(4))
	
for x in range(1, 11):
	print('{0:2d} {1:3d} {2:4d}'.format(x,x*x,x*x*x))
	
#the rjust() function is use for right justifies a string
#similarly, there are function ljust() and center()
#those functions not truncate the string
#if you want justifies a string and truncate if the string is too long,
#user str.ljust(n)[:n] or str.rjust(n)[n:]

#hello I'm draft...
#str = '12345678'
#print(str[:5])
#print(str[-5:])

#formal way of using format()
print('{} and {}'.format('first string','second string'))
print('{0} and {1}'.format('first string','second string'))
print('{1} and {0}'.format('first string','second string'))
print('{firstPosition} and {secondPosition}'.format(firstPosition = 'first string',secondPosition = 'second string'))

#use a dictionary to format a string
table = {'Sjoerd': 4127, 'Jack': 4098, 'Dcab': 8637678}
print('Jack: {Jack:d}; Sjoerd: {Sjoerd:d}; Dcab: {Dcab:d}'.format(**table))

#vars() get all the local variables
varsResult = vars()
print(varsResult)

