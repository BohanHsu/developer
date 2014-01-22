# list -> boolean 
# given: a array
# returns: true iff all elements in the array are 
# distinct
#
def fn11v1(array):
	checked = [];
	for e in array:
		if contain(checked,e):
			return False
		else:
			checked.append(e)
	
	return True

# list number -> boolean
# given: a list which contain numbers and 
# a number to check
# returns: true iff the given number is contained in the 
# given list
#
def contain(a,e):
	for element in a:
		if element == e:
			return True

	return False


# list -> boolean
# given: a list
# returns: a true iff all the elements in this list are distince
# not using additional memory
#
def fn11v2(array):
	length  = len(array)
	for i in range(0,length):
		for j in range((i + 1),length):
			if array[i] == array[j]:
				return False

	return True


# list -> list
# given: a list 
# returns: the inverse list
#
def fn12v1(array):
	length = len(array)
	if length%2 == 0:
		mid = length/2 - 1
	else:
		mid = (length - 1)/2 - 1

	for i in range(0,int(mid)):
		temp = array[i]
		array[i] = array[length - 1 - i]
		array[length - 1 - i] = temp

	return array


# list -> list
# given: a array
# returns: the array eliminate duplicate elements
#
def fn13v1(array):
	length = len(array)
	for i in range(0,length):
		for j in range((i+1),length):	
			while array[i] == array[j] and j<length:
				length = length - 1
				for k in range(j,length):
					array[k] = array[k+1]	
	
	return array[:length]


# list list -> boolean
# given: two list 
# returns : true iff those two arrays are anagrams
#
def fn14v1(a1,a2):
	len1 = len(a1)
	len2 = len(a2)
	if len1 != len2:
		return False
	
	anagrams = True
	marks = []
	for i in range(0,len1):
		marks.append(0)

	for i in range(0,len1):
		if anagrams == False:
			return False

		anagrams = False
		for j in range(0,len2):
			if a1[i] == a2[j]:
				if marks[j] == 0:
					marks[j] = 1
					anagrams = True

	return True			


# listOflist -> ListOfList
# given: a matrix
# returns: rotate the matrix 90 degree clockwise
# 
def fn16v1(m):
	length = len(m)
	n = length
	if length%2 == 0:
		h = int(length / 2)
		w = int(length / 2)
	else:
		h = int(length / 2) 
		w = int(length / 2) + 1

	for i in range(0,h):
		for j in range(0,w):
			temp = m[i][j]
			m[i][j] = m[j][(n-1-i)]
			m[j][(n-1-i)] = m[(n-1-i)][(n-1-j)]
			m[(n-1-i)][(n-1-j)] = m[(n-1-j)][i]
			m[(n-1-j)][i] = temp
	
	return m


