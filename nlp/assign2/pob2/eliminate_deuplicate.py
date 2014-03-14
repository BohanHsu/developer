import sys

read_path = sys.argv[1]

f = open(read_path)
lines = f.readlines()
f.close()

nl = []

for l in lines:
    if len(l) >= 1:
        parts = l.split('\t')
        parts = parts[1:]
        l = '\t'.join(parts)
        nl.append(l)

ns = set(nl)

print len(nl)
print len(ns)

for se in ns:
    nl.remove(se)

print(nl)
