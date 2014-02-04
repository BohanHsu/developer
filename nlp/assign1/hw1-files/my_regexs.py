import re
import sys

# the first argument is a regular expression
# from the second argument are file path
pattern = sys.argv[1]
files = sys.argv[2:]

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

	words = set(re.findall(pattern,s))
	print words
	print len(words)
	return words

reg_find(pattern,files)


def suffix(suff,count):
	pass
