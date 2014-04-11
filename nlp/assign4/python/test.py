a = None

def setA():
    global a
    a = [1,2,3,4,5]

def readA():
    global a
    print a[2]

setA()
readA()
