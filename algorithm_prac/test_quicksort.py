from quicksort import quickSort, partition, randomized

#test import 
print('*****')
print('test import')
a=[1,2,3]
quickSort(a)
partition(a)

#test partition function 
print('*****')
print('test partition function')
res = partition([5,2,6,7,7,8,3,2,1,2,3])
print(res)
res = partition([5,2,6,5,7,8,3,2,1,2,3])
print(res)

#test the random function 
print('*****')
print('test random function')
res = randomized([1,2,3,4,5,6])
print(res)
res = randomized([1,2,3,4,5,6])
print(res)
res = randomized([1,2,3,4,5,6])
print(res)
res = randomized([1,2,3,4,5,6])
print(res)

#final tests on quickSort()
print('*****')
print('test quick sort')

print(quickSort([]))
print(quickSort([1]))
print(quickSort([10,9,8,7,6,5,4,3,2,1]))
print(quickSort([3,5,7,2,56,3,56,23,4,6]))
print(quickSort([1,2,3,4,5,6,7]))








