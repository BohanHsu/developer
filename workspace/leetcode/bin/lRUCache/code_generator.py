import sys
# the first argument is text file path

path = sys.argv[1]

f = open(path)
strs = f.readlines()
f.close()

str = strs[0]
clauses = str.split('),');
str = ''

for line in clauses:
	str = str + line + ');\n'

strs = [str]

f = open('./generated.txt','w')
f.writelines(strs)
f.close()
