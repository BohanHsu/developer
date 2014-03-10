import sys

read_path = sys.argv[1]
write_path = sys.argv[2]

f = open(read_path)
lines = f.readlines()
f.close()

pos = ''

nl = []

for l in lines:
    if cmp(l[:1],'(') == 0 and  cmp(l[-1:],')'):
        pos = l[1:-2]
        print pos
    else:
        parts = l.split('\t')
        if len(parts) >= 2 and cmp(parts[1],'Misc') == 0:
            print l
            print pos
            parts[1] = pos
            l = '\t'.join(parts)
            print l
    
        nl.append(l)
        

f = open(write_path,'w')
f.writelines(nl)
f.close()
