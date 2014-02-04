import sys

# the first of sys argument is the original file path
# the second argument is the comparing file path
path1 = sys.argv[1]
path2 = sys.argv[2]

#read a txt file of fst
#return the word in a list
def tokenize(path):
	words = []
	f = open(path)
	lines = f.readlines()
	f.close()
	letter = []
	for line in lines:
		l = line.split('\t')
		if len(l) >= 3:
			letter.append(l[2])

	tmp = ''
	for s in letter:
		if len(s) == 1:
			tmp = tmp + s
		else:
			words.append(tmp)
			tmp = ''
	
	return words


def contained(s1,s2):
	if len(s2) == 0:
		return True
	if len(s1) < 1:
		return False
	s = s2[0]
	index = s1.find(s) 
	if index == -1:
		return False
	else:
		
		return contained(s1[index + 1:],s2[1:])

def letter_compare(w1,w2):
	l1 = len(w2)
	count = 0
	for i in range(0,l1):
		if cmp(w1[i],w2[i]) == 0:
			count = count + 1
		else:
			while cmp(w1[i],w2[i]) != 0:
				w1 = w1[1:]

			count = count +1


def compare(l1,l2):
	count = 0
	len1 = len(l1)
	len2 = len(l2)
	for i in range(0,len1):
		if cmp(l1[i],l2[i]) == 0:
			count = count + 1

	return count

words1 = tokenize(path1)
words2 = tokenize(path2)

print('---words in file: ', path1, ':')
print(words1)
print('---length of words: ', len(words1))
print('---words in file: ', path2, ':')
print(words2)
print('---length of words: ', len(words2))

count = compare(words1,words2)
print('how much words remain the same: ', count)

