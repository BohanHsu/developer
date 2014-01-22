# in this file we study inheritance in python

#define the super class ClassA
class ClassA:
	def doing(self):
		print('I\'m class A')
		
class ClassB(ClassA):
	def doing(self):
		print('I\'m class B')
		
class ClassC:
	def __init__(self,a):
		self.a = a
	
	def getA(self):
		return self.a
		
class ClassD(ClassC):
	def __init__(self):
		pass

# method override in derived class
a1 = ClassA()
b1 = ClassB()
a1.doing()
b1.doing()
# calling super class's method in derived class
ClassA.doing(b1)

# if super class only provide a initialize method with one arguments.
# and derived class not calling this method explicitly, what will happen?
d1 = ClassD()
print(d1.getA())
# AttributeError: 'ClassD' object has no attribute 'a'
# 'a' is dynamic declared!!!!!

