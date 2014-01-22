from prac_chap2 import Node

class Stack:
	"""implement a stack"""
	top = None
	def __init__(self):
		pass
	
	def push(self, element):
		n = Node(element)
		if self.top is None:
			self.top = n
		else:
			n.next = self.top
			self.top = n	
		
	def pop(self):
		if self.top is None:
			return None
		else:
			data = self.top.data
			self.top = self.top.next
			return data

	def peek(self):
		if self.top is None:
			return None
		else: 
			return self.top.data

	def isEmpty(self):
		if self.top is None:
			return True
		else:
			return False

	def showStack(self):
		if not self.top is None:
			self.top.showLink()


class Queue(Stack):
	"""implement a queue"""
	def __init__(self):
		pass

	def enqueue(self, element):
		if self.top is None:
			self.top = Node(element)
		else:
			self.top.appendToTail(Node(element))

	def dequeue(self):
		self.pop()

	def showQueue(self):
		self.showStack()



# [Stacks]
# given: stacks as a Hanoi Tower
# effect: move all the element to the last stack
#
def fn34v1(stacks):
	l = len(stacks)
	status = Stack()
	moves = Stack()
	status.push(stacks)
	moves.push((-1,-1))
	while status.isEmpty() == False:
		s = status.pop()
		m = moves.pop()
		for i in range(0,l):
			for j in range(0,l):
				if (i == j) or (i == m[1] and j == m[0]):
					pass
				else:
					s = move(s,i,j)
					if qual(s):
						return s
					elif not s is None:
						status.push(s)
						moves.push((i,j))
				

# [stacks] -> void
# given: two stack
# effect: move the top from the first to the last
#
def move(stacks,si,ti):
	l = len(stacks)
	s = stacks[si]
	t = stacks[ti]
	if t.isEmpty():
		t.push(s.pop())
		return None
	
	if s.isEmpty():
		return None

	if s.peek() < t.peek():
		t.push(s.pop())
	
	r = []
	for i in range(0,l):
		if i == si:
			r.append(s)
		elif i == ti:
			r.append(t)
		else:
			r.append(stacks[i])

	return r

# [Stacks] -> boolean
# given: a list of stacks
# returns: true iff all the stacks except the last are empty
#
def qual(stacks):
	if stacks is None:
		return False

	length = len(stacks)
	for i in range(0,length):
		if (i != (length-1)) and (not stacks[i].isEmpty()):
			return False
			
	return True



































