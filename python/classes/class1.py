# in this file we start to study class

class MyClass: 
	"""A simple example class"""
	i = 12345
	def f(self):
		return 'hello world'
	
# access values of a class outside the class
i = MyClass.i
f = MyClass.f

print(i) 
print(f)

#initiating a new object
x = MyClass()
print(x)


# x.f is a method object 
# MyClass.f is a function object
print('MyClass.f: ', MyClass.f)
print('x.f: ', x.f)

class Complex:
	def __init__(self, realpart, imagpart):
		self.r = realpart
		self.i = imagpart
		
y = Complex(3.0, -4.5)
print(y.r, y.i)

# calling x.f() is equals to calling MyClass.f(x)
class TestClass1:
	def __init__(self, name):
		self.name = name
		
	def getName(self):
		return 'my name is: ' + self.name

t1 = TestClass1('the first test class')

print(t1.getName())
print(TestClass1.getName(t1))
