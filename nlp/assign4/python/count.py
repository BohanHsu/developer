import sys
path = sys.argv[1]
f = open(path)
lines = f.readlines()
f.close()
total = 0
for line in lines:
    words = line[:-1].split(' ')
    total = total + len(words)

print total
