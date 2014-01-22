#open a file
print('open the file \'myFile.txt\'')
f = open('myFile.txt','r')

#read all contents in file f
print('-all contents in this file')
contents = f.read()
print(contents)

#read size = 1
print('-size = 1')
f = open('myFile.txt','r')
contents1 = f.read(1)
print(contents1)

#if size = 1, then read nothing???

#let's try size = 5
#read size = 5
print('-size = 5')
f = open('myFile.txt','r')
contents5 = f.read(5)
print(contents5)

#that means read(size) specify how much characters read each time

#now let's try readline() function
print('-readline function')
f = open('myFile.txt','r')
s = f.readline()
print(repr(s))
s = f.readline()
print(repr(s))
s = f.readline()
print(repr(s))

#loop though the file 
print('loop the file')
lineNum = 1
f = open('myFile.txt','r')
for line in f:
	print('line', end = ' ')
	print(str(lineNum), end = ': ')
	print(line, end = '')
	lineNum += 1

#in the for loop of file, it encapsulate the end line, not to print it



