import sys
# String -> String[]
# find all the string inside a parenthese
#
def getAllParentheses(str):
    strLen = len(str)
    stack = []
    strings = []
    for i in range(0,strLen):
        if (cmp(str[i],'(') == 0):
            stack.append(i)
        elif (cmp(str[i],')') == 0):
            start = stack.pop()
            strings.append(str[start+1:i])
            
    return strings

# test code
str = '(ROOT (SQ (VBP do) (NP (NNS coconuts)) (VP (VB speak)) (. ?)))'
#strs = getAllParentheses(str)

# String -> boolean
# determine if a rule is leaf at tree structure
#
def isLeaf(str):
    strLen = len(str)
    for i in range(0,strLen):
        if (cmp('(',str[i]) == 0):
            return False
    
    return True

# String -> String
# convert a rule from it parenthese expression to grammar expression
#
def getRule(str):
    rule = '1\t' + str.split(' ')[0] + '\t'
    level = 0
    strLen = len(str)
    cur = ''
    appending = False
    for i in range(0,strLen):
        if appending:
            cur = cur + str[i]

        if (cmp(str[i],'(') == 0):
            if level == 0:
                # this is a child
                appending = True

            level = level + 1
        elif (cmp(str[i],' ') == 0):
            # this child is end
            appending = False
        elif (cmp(str[i],')') == 0):
            level = level-1

    return rule + cur

# test code
#str = 'SQ (VBP do) (NP (NNS coconuts)) (VP (VB speak)) (. ?)'
#print getRule(str)

def getAllRules(str):
    rules = []
    for s in getAllParentheses(str):
        if not isLeaf(s):
            rules.append(getRule(s))

    return rules

# test code
#for s in getAllRules(str):
#    print s

path = sys.argv[1]
f = open(path)
lines = f.readlines()
f.close()

rs = []
for line in lines:
    if cmp(line[0],'(') == 0:
        rs = rs + getAllRules(line)

rs = set(rs)

for r in rs:
    print r
