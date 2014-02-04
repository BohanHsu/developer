# this code deal with suffix of word, it take at least three arguments to run from console.
# the first argument is the old suffix of a word
# the second argument is the new suffix of a word
# from the third arguments, are file path which contain the corpus

import re
import sys
import re

# OLDSUFF: is the old suffix of a word
# for examples: company -> companies, then the old suffix is 'y'
OLDSUFF = sys.argv[1]
# NEWSUFF: is the new suffix of a word
# for examples: company -> companies, then the new suffix is 'ies'
NEWSUFF = sys.argv[2]
files = sys.argv[3:]

reg1 = r'\b(\w+)' + OLDSUFF + r'\b.*\b\1' + NEWSUFF + r'\b|\b(\w+)'
reg1 = reg1 + NEWSUFF+r'\b.*\b\2' + OLDSUFF+r'\b'
reg2 = r'\b(\w+)' + OLDSUFF + r'\b.*\b\1' + OLDSUFF + r'\b.*\b\1' + NEWSUFF + r'\b|\b(\w+)'
reg2 = reg2 + NEWSUFF + r'\b.*\b\1' + OLDSUFF + r'\b.*\b\1' + OLDSUFF + r'\b|\b(\w+)'
reg2 = reg2 + OLDSUFF + r'\b.*\b\1' + NEWSUFF + r'\b.*\b\1' + OLDSUFF + r'\b'

print OLDSUFF
print NEWSUFF
print reg1
print reg2

# reg_find : regular_expression, list_of_file_path -> list of words
# return a list of words which match the regular expression
# this method will concatenate all the lines in those given file and find patterns
#
def reg_find(pattern,files):
	s = ''
	for filename in files:
		file = open(filename)
		for line in file.xreadlines():
			s = s + line
	
		file.close()

	s = s.lower()
	#print s
	print pattern	
	words = re.findall(pattern,s)
	r = []
	for w in words:
		if cmp(w[0],'') == 0:
			r.append(w[1] + OLDSUFF)
		elif cmp(w[1],'') == 0:	
			r.append(w[0] + OLDSUFF)

	return r

def iterating(reg,files):
	w1 = reg_find(reg,files)
	sorted(w1)
	w1 = list(set(w1))
	print(w1)
	print(len(w1))


print 'world occur at least once'
#w1 = reg_find(reg1,files)
#w1 = set(w1)
#print(w1)
#print(len(w1))
iterating(reg1,files)

#print 'word occur at least twice'
#w2 = reg_find(reg2,files)
#w2 = set(w2)
#print(w2)
#print(len(w2))
iterating(reg2,files)

