alphabat = []
f = open('full_downcase.txt')
lines = f.readlines()
for line in lines:
	if len(line) >= 5:
		letter = line.split(' ')[2] 
		alphabat.append(letter)

f.close()

#print(alphabat)

vowel = ['A','E','I','O','U','a','e','i','o','u']

def isVowel(str):
	for v in vowel:
		if cmp(v,str)==0:
			return True

	return False

#test code
#for l in ['a','b','c','d','e','A','B','C','D','E']:
#	print(l)
#	print(isVowel(l))

result = []

for a in vowel:
	for v in vowel:
		if cmp(v,a) == 0:
			value = '0'
		else: 
			value = '1'
		
		s = '0 0 ' + v + ' ' + a + ' ' + value + '\n'
		print(s)
		result.append(s)

epsilon = '<epsilon>'

for a in alphabat:
	s = '0 0 ' + epsilon+ ' ' + a + ' ' + '1' + '\n'
	print(s)
	result.append(s)

for a in alphabat:
	s = '0 0 ' + a + ' ' + epsilon + ' ' + '1' + '\n'
	print(s)
	result.append(s)

f = open('test.txt','w')
f.writelines(result)






