from prac_chap2 import *

node1 = Node(1)
print(node1.data)

n2 = Node(2)
n3 = Node(3)
n4 = Node(4)

node1.appendToTail(n2)
node1.showLink()
node1.appendToTail(n3)
node1.showLink()
node1.appendToTail(n4)
node1.showLink()

print('check fn21v1')

n1 = Node(1)
n2 = Node(1)
n3 = Node(2)
n4 = Node(3)
n5 = Node(4)
n6 = Node(4)

n1.appendToTail(n2)
n1.appendToTail(n3)
n1.appendToTail(n4)
n1.appendToTail(n5)
n1.appendToTail(n6)

n1.showLink()

n1r = f21v1(n1)
n1r.showLink()

print('check fn22v1')

n1 = Node(1)
n2 = Node(2)
n3 = Node(3)
n4 = Node(4)
n5 = Node(5)
n6 = Node(6)

n1.appendToTail(n2)
n1.appendToTail(n3)
n1.appendToTail(n4)
n1.appendToTail(n5)
n1.appendToTail(n6)

n1.showLink()

n1n = f22v1(n1,1)
n1n.showLink()


print('check fn23v1')

n1 = Node(1)
n2 = Node(2)
n3 = Node(3)
n4 = Node(4)
n5 = Node(5)
n6 = Node(6)
n7 = Node(7)

n1.appendToTail(n2)
n1.appendToTail(n3)
n1.appendToTail(n4)
n1.appendToTail(n5)
n1.appendToTail(n6)
n1.appendToTail(n7)

n1.showLink()

fn23v1(n1)
n1.showLink()

print('check fn24v1')

n1 = Node(3)
n1a = Node(1)
n1b = Node(5)

n2 = Node(5)
n2a = Node(9)
n2b = Node(2)

n1.appendToTail(n1a)
n1.appendToTail(n1b)

n2.appendToTail(n2a)
n2.appendToTail(n2b)

r = fn24v1(n1,n2)
r.showLink()




