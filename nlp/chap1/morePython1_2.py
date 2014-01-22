# this file we review some expressions in python

# operations on python's list

# appending two lists
list = ['a','b'] + ['c','d','e']
print(list)

# call the list object's append list
list.append('f')
print(list)

# getting a element in a list by index
element = list[5]
print(element)

# getting the index of a element by values
index = list.index('e')
print(index)

# slicing in python's list
sub_list = list[0:5]
print(sub_list)
# so the sub_list will start from index at 0, and end before index 5.
# that a common in programming language

print('======')

# operations on python string
str = "abcdefgh"
letter = str[0]
print(letter)
sub_str = str[0:4]
print(sub_str)

# appending strings
appened_str = str + 'ijk'
print(appened_str)

# that seems strange....
times_str = str * 2
print(times_str)

# we almost done!!!
