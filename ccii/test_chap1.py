from prac_chap1 import *

print('check contain fn')
print(contain([1,2,3,4,5],5),'=true')
print(contain([1,2,3,4],5),'=false')

print('check fn11v1')
print(fn11v1([1,1,2,3,4,5]),'=false')
print(fn11v1([1,2,3,4]),'=true')

print(fn11v2([1,1,2,3,4,5]),'=false')
print(fn11v2([1,2,3,4]),'=true')
print()

print('check fn12v1')
print(fn12v1([1,2,3,4,5]),'=[5,4,3,2,1]')
print(fn12v1([1,2,3,4]),'=[4,3,2,1]')
print()

print('check fn13v1')
print(fn13v1([1,2,3,1,4]),'=[1,2,3,4]')
print(fn13v1([1,2,3,1,1,4]),'=[1,2,3,4]')
print(fn13v1([1,2,3,1,1,4,3,3]),'=[1,2,3,4]')
print()

print('check fn14v1')
print('[1,2,3,4,5],[2,5,4,1,3]')
print(fn14v1([1,2,3,4,5],[2,5,4,1,3]))
print('[1,2,3,4,5],[2,5,4,1,6]')
print(fn14v1([1,2,3,4,5],[2,5,4,1,6]))
print('[1,2,3,4,5],[2,5,4,1,3]')
print(fn14v1([1,2,3,4,5],[2,5,4,1,3,8]))

print()
print('[1,2,3,4,5],[2,5,4,1,3]')
print(fn14v1([1,2,3,4,5],[2,5,4,1,3]))


print('check fn16v1')

def vendor_matrix(m):
	for v in m:
		for e in v:
			print(e,end='')

		print('')

m1 = [[1,2],[3,4]]
m2 = [[1,2,3,'a'],[4,5,6,'b'],[7,8,9,'c'],[0,0,0,0]]

vendor_matrix(m1)
vendor_matrix(m2)

m1 = fn16v1(m1)
m2 = fn16v1(m2)

print(m1)
print(m2)

vendor_matrix(m1)
vendor_matrix(m2)

m0 = [[1,2,3],[4,5,6],[7,8,9]]
m0 = fn16v1(m0)
vendor_matrix(m0)
