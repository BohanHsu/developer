class Node:
	"""a class as link list's node"""
	next = None
	data = None
	def __init__(self, value):
		self.data = value

	def appendToTail(self,newNode):	
		node = self
		while not node.next is None:
			node = node.next

		node.next = newNode


	def showLink(self):
		node = self
		while not node is None:
			print(node.data,end='')
			node = node.next

		print()


# linkList -> linkList
# given: a node 
# returns: the same node with all the duplicated node removed
#
def f21v1(node):
	ni = node
	while not ni is None:
		nj = ni.next
		np = ni
		while not nj is None:
			if ni.data == nj.data:
				np.next = nj.next
			else:
				np = np.next

			nj = nj.next

		ni = ni.next

	return node


# linkList -> linkList
# given: a linkList
# returns: the nth to last elements in the list
# 
def f22v1(node,n):
	if n == 1:
		return node
	
	p = node
	for i in range(0,(n-1)):
		p = p.next

	return p


# linkList -> Void
# given: a linkList
# effect: remove the middle element in the list
#
def fn23v1(node):
	n = node
	length = 0
	while not n is None:
		length+=1
		n = n.next

	n = node
	if not length%2 == 0:
		for i in range(0, int((length-1)/2-1)):
			n = n.next

		n.next = n.next.next


# linkList linkList -> LinkList
# given: two link lists, each represents a number
# return: a link list represent the sum
#
def fn24v1(l1,l2):
	r = None
	carrier = 0
	while (not l1 is None) or (not l2 is None):
		if l1 is None:
			a1 = 0
			l1 = None
		else: 
			a1 = l1.data
			l1 = l1.next

		if l2 is None:
			a2 = 0
			l2 = None
		else:
			a2 = l2.data
			l2 = l2.next

		d = a1 + a2 + carrier
		carrier = int(d/10)
		d = int(d%10)
		
		newNode = Node(d)
		if r is None:
			r = newNode
		else:
			r.appendToTail(newNode)

	return r


# linkList -> Node
# given: a link list
# returns: ....
#
	







