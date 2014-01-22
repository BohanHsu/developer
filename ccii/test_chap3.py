from prac_chap3 import *

s = Stack()

print('test the stack')

top = s.peek()
print(top)

top = s.pop()
print(top)

s.push(1)
s.push(2)
s.push(3)
s.push(4)
s.push(5)

s.showStack()
top = s.pop()
print(top)

top = s.peek()
print(top)

top = s.peek()
print(top)

print('\ntest the Queue')

q = Queue()
q.enqueue(1)
q.enqueue(2)
q.enqueue(3)
q.enqueue(4)
q.enqueue(5)
q.enqueue(6)
q.showQueue()
q.dequeue()
q.showQueue()
q.enqueue(7)
q.showQueue()
q.dequeue()
q.showQueue()

print('\ncheck fn43v1')
s1 = Stack()
s2 = Stack()
s3 = Stack()

#s1.push(5)
#s1.push(4)
#s1.push(3)
#s1.push(2)
s1.push(1)

s = [s1,s2,s3]

fn34v1(s)

s[0].showStack()
s[1].showStack()
s[2].showStack()





