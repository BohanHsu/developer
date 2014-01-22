#this code is for study data structure in python

myList = [1,2,3,4,5]
print(myList)

#append at the last of a list
myList.append(6)
print(myList)

#extend is for append a list at the end of a list
extendList = [7,9]
myList.extend(extendList)
print(myList)

#insert the new element at position of index
myList.insert(7,8)
print(myList)

#remove: remove the first element in the list with the same value.
#	give a Error if there are no element with the same value
myList.remove(9)
print(myList)

#advance in list

#using list as Stacks
print('using list as a Stack')
stack = [3,4,5]
print
#push
stack.append(6)
print(stack)
#pop
print(stack.pop())
print(stack.pop())
print(stack)

#using list as a Queue
print('using list as a Queue')
from collections import deque
queue = deque([3,4,5])
print(queue)
print(queue.popleft())
print(queue)

#list comprehensions
#using for loop to init a list 
squares = []
for x in range(10):
	squares.append(x**2)

print(squares)

#another way of init a list
squares = [x**2 for x in range(10)]
print(squares)
