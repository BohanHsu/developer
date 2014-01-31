import sys

#this file is to delete all the weight in a fst definition file

filename = sys.argv[1]
f = open(filename)

lines = f.readlines()

f.close()

new_lines = []

for line in lines:
	words = line.split('\t')
	#print(words)
	if len(words) >= 4:
		s = ' '.join(words[:4])
	else:
		s = str(words[0])

	#print(s)
	s = s + '\n'
	new_lines.append(s)

#for line in new_lines:
#	print(line)

new_name = 'trimed_' + filename
print('writing to file: ',new_name)

f = open(new_name,'w')

f.writelines(new_lines)

f.close()


