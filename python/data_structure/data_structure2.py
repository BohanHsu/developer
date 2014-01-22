#in this code we study Tuples and Sequences

#a tuple contain a number of values separated by commas
t = 12345,54321,'hello'
print(t)

#access a element in a tuple
print(t[0])

#want to construct a tuple with only one element?
singlton = 'hello',
print(singlton)

#assign multi-value in a single line
x,y,z = t
print(x)
print(y)
print(z)

#set
#create a set by using set() function or {element1,...elementn}
#can't using {} to create a empty set, this will create a dictionary
basket = {'apple','orange','apple','pear'}
print(basket)
isOrangeIn = 'orange' in basket
print(isOrangeIn)

#这段真他妈毁三观啊
a = set('abracadabra')
b = set('alacazam')
print(a)
#set difference
print(a-b)
#set union
print(a|b)
#set intersection
print(a&b)
#letters in a or b not not both, set union diff set intersection
print(a^b)


#now we are going to study dictionary
#define a dictionary
tel = {'jack':4098,'sape':4139}
tel['guido'] = 4127
print(tel['jack'])
print(tel)

#using the dict() function to built a dictionary
tel1 = dict([('sape',4139),('guido',4127),('jack',4098)])
print(tel1)

#when the key are strings, it is easy to built a dictionary
tel2 = dict(sape=4139,guido=4127,jack=4098)
print(tel2)


#now looping the list
knights = {'gallahad': 'the pure', 'robin': 'the brave'}
for k,v in knights.items():
	print(k,v)
	
for i,v in enumerate(['tic','tac','toe']):
	print(i,v)
	
