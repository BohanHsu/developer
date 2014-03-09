import sys
import os

folderFrom = sys.argv[1]
folderIn = sys.argv[2]

fileListFrom = os.listdir(folderFrom)
fileListIn = os.listdir(folderIn)

count = 0
for f in fileListIn:
    if f in fileListFrom:
        print f
        count = count + 1

print count
