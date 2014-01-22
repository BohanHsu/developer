# quicksort.py 
# author: Bohan Xu
# date: 1/6/2013
# version 1.0

import random

# given: a list
# returns: a sorted list, using randomized quick sort
#
def quickSort(a):
	if len(a) <= 1:
		return a
	else:
		a = randomized(a)
		return partition(a)

# list -> list
# given: a list
# where: the list must have at least 1 element
# returns: using the first element as pivot,
# 	partition the given list, the left part sub list 
#	only contain element less or equal to the pivot,
#	the right sub list only contain element greater or
# 	equal to the pivot.
# 
def partition(a):
	pivot = a[0]
	left_array = []
	right_array = []
	for element in a[1:]:
		if element <= pivot:
			left_array.append(element)
		else:
			right_array.append(element)
	
	
	result_array = []

	left_array = quickSort(left_array)
	right_array = quickSort(right_array)	

	left_array.append(pivot)
	result_array  = result_array + left_array 
	result_array = result_array + right_array
	return result_array

# list -> list
# given: a list
# returns: a list randomly swap a element with the first 
# element in the list
#
def randomized(a):
	length = len(a)
	random_int = random.randint(1,length-1)
	temp = a[0]
	a[0] = a[random_int]	
	a[random_int] = temp
	return a

