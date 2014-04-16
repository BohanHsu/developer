import sys
from collections import deque
# this is a BTG parse
# for natural language processing homework 4

# how to run this file:
# the first argument is the dictionary file
# the second argument is the first language file
# the third argument is the second language file
# the forth argument is optional: it can be 'nomotonic' or 'swap': 
#    if it is 'monotonic', it will prohibit the swap rule in grammar
#    the default value is 'swap'

# abbrivation: 
#  [] square bracket: sb
#  <> angle bracket: ab

# data structure:
#  delta: matrix store the probability
#  theta: matrix store the forward or inverse
#  iota: store the argmax for j
#  kappa: store the argmax for k
#  sigma: store the argmax of s
#  upsilon: store the argmax of u

# global variable:
sbCount = 0 # the number of square bracket []
abCount = 0 # the number of angel bracket <>
epWord = 0 # the number of <e>-word align
wordEp = 0 # the number of word-<e> align
totAlign = 0 # the number of word-word align

# define the data structure for lexical dictionary
class LecxicalDictionary:
    """the data structure of the lexical"""
    entries = []
    def __init__(self,lines):
        for line in lines:
            entry = line.split('\t')
            self.entries.append(entry)

    def b(self,lang1word,lang2word):
        """return the possiblity of a alignment of two word"""
        for entry in self.entries:
            if entry[0] == lang1word and entry[1] == lang2word:
                return float(entry[2])

        return float('-inf')

# define the data structure for BTG grammar
class BTGGrammar:
    def __init__(self,sb,ab,epw,wep):
        self.aToSb = sb
        self.aToAb = ab
        self.epsilonWord = epw
        self.wordEpsilon = wep

delta = None
theta = None
itoasb = None
kappasb = None
sigmasb = None
upsilonsb = None
itoaab = None
kappaab = None
sigmaab = None
upsilonab = None
nodes = []

def parseing(sent1,sent2,grammar,lexical):
    """parsing 2 sentences in two language"""
    # declaring global 
    global delta
    global theta
    global itoasb
    global kappasb
    global sigmasb
    global upsilonsb
    global itoaab
    global kappaab 
    global sigmaab
    global upsilonab
    global nodes
    global sbCount
    global abCount
    global epWord
    global wordEp
    global totAlign

    # declare variable
    T = len(sent1)
    V = len(sent2)
    nodes = []
    delta = [[[[None
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    theta =  [[[[''
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    iotasb =  [[[['A'
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    kappasb =  [[[['A'
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    sigmasb =  [[[[0
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    upsilonsb =  [[[[0
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    iotaab =  [[[['A'
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    kappaab =  [[[['A'
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    sigmaab =  [[[[0
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    upsilonab =  [[[[0
                for x1 in range(0,V+1)] 
               for x2 in range(0,V+1)] 
              for x3 in range(0,T+1)] 
             for x4 in range(0,T+1)]
    # initialization:
    for t in range(1,T+1):
        for v in range(1,V+1):
            delta[t-1][t][v-1][v] = lexical.b(sent1[t-1],sent2[v-1])
             
    for t in range(1,T+1):
        for v in range(0,V+1):
            delta[t-1][t][v][v] = grammar.wordEpsilon

    for t in range(0,T+1):
        for v in range(1,V+1):
            delta[t][t][v-1][v] = grammar.epsilonWord

    # recursion
    for s in range(0,T):
        for t in range(s+1,T+1):
            for u in range(0,V):
                for v in range(u+1,V+1):
                    if (t-s+v-u) > 2:
                        deltaFunction(s,t,u,v,grammar)
                        
    # resonstruction
    q1 = (0,T,0,V)
    stack1 = [] # the node stack
    stack2 = [] # the status stack
    stackc = [] # the closing parenthesis
    parStr = '' 
    stack1.append(q1)
    stack2.append(0)
    
    while len(stack1) > 0: # this stack is not empty
        q = stack1[-1] # peek the top of stack1
        stat = stack2[-1] # peek the status of the top node

        lq = left(q[0],q[1],q[2],q[3])
        rq = right(q[0],q[1],q[2],q[3])

        if stat == 0: # left child has not been visit
            # put the left child if exist
            # change status to 1
            stack2.pop()
            stack2.append(1)
            if not lq is None:
                stack1.append(lq)
                stack2.append(0)
                
            if (not lq is None) and (not rq is None):
                # this is a imtermediate node 
                # add code to add open parenthesis
                if theta[q[0]][q[1]][q[2]][q[3]] == 's':
                    parStr = parStr + '['
                    stackc.append(']')
                    sbCount = sbCount + 1
                else:
                    parStr = parStr + '<'
                    stackc.append('>')
                    abCount = abCount + 1
                
        elif stat == 1: # left child has visit but right has not
            # put the right
            stack2.pop()
            stack2.append(2)
            if not rq is None:
                stack1.append(rq)
                stack2.append(0)

        elif stat == 2: # both child has visited
            # if this is a leaf node, then append the alignment
            # if this is a intermediate node, append the closing parenthesis
            if (not lq is None) and (not rq is None):
                # this is the intermediate node 
                parStr = parStr + stackc.pop()
            else:
                # this is the leaf
                if q[1] - q[0] == 1:
                    w1 = sent1[q[0]]
                else:
                    w1 = '<e>'
                    epWord = epWord + 1

                if q[3] - q[2] == 1:
                    w2 = sent2[q[2]]
                else:
                    w2 = '<e>'
                    wordEp = wordEp + 1
                    
                parStr = parStr + w1 + '/' + w2 + ' '
                totAlign = totAlign + 1
        
            stack1.pop()
            stack2.pop()

    print parStr

def checkExist(ns,n):
    for en in ns:
        eql = True
        for i in range(0,len(en)):
            if n[i] != en[i]:
                eql = False
                break
        
        if eql:
            return True

deltaStack = []
    
def deltaFunction(s,t,u,v,grammar):
    # declaring global 
    global delta
    global theta
    global itoasb
    global kappasb
    global sigmasb
    global upsilonsb
    global itoaab
    global kappaab 
    global sigmaab
    global upsilonab
    global deltaStack
    if delta[s][t][u][v] is None:
        # push to stack
        deltaStack.append([s,t,u,v])

    while len(deltaStack) > 0:
        q = deltaStack[-1]
        if setDelta(q[0],q[1],q[2],q[3],grammar) == True:
            deltaStack.pop()
    
def setDelta(s,t,u,v,grammar):
    # declaring global 
    global delta
    global theta
    global itoasb
    global kappasb
    global sigmasb
    global upsilonsb
    global itoaab
    global kappaab 
    global sigmaab
    global upsilonab
    global deltaStack
    
    dsbab = deltaSbAb(s,t,u,v,grammar)
    if dsbab is None:
        return False

    dsb = dsbab[0]
    dab = dsbab[1]
    if dsb >= dab:
        delta[s][t][u][v] = dsb
        theta[s][t][u][v] = 's'
    else:
        delta[s][t][u][v] = dab
        theta[s][t][u][v] = 'a'
    
    return True

def deltaSbAb(s,t,u,v,grammar):
    """the possiblity of align in suqare bracket"""
    # declaring global 
    global delta
    global theta
    global itoasb
    global kappasb
    global sigmasb
    global upsilonsb
    global itoaab
    global kappaab 
    global sigmaab
    global upsilonab
    global deltaStack

    maxPosSb = float('-inf')
    maxSSb = float('-inf')
    maxUSb = float('-inf')
    maxPosAb = float('-inf')
    maxSAb = float('-inf')
    maxUAb = float('-inf')
    for S in range(s,t+1):
        for U in range(u,v+1):
            if not (S-s)*(t-S) + (U-u)*(v-U) == 0:
                dv1 = delta[s][S][u][U] 
                dv2 = delta[S][t][U][v]
                dv3 = delta[s][S][U][v]
                dv4 = delta[S][t][u][U]
                
                if dv1 is None and not checkExist(deltaStack,[s,S,u,U]):
                    deltaStack.append([s,S,u,U])
                
                if dv2 is None and not checkExist(deltaStack,[S,t,U,v]):
                    deltaStack.append([S,t,U,v])

                if dv3 is None and not checkExist(deltaStack,[s,S,U,v]):
                    deltaStack.append([s,S,U,v])

                if dv4 is None and not checkExist(deltaStack,[S,t,u,U]):
                    deltaStack.append([S,t,u,U])
                
                if dv1 is None or dv2 is None or dv3 is None or dv4 is None:
                    return None

                tmpSb = grammar.aToSb + dv1 + dv2
                if tmpSb >= maxPosSb:
                    maxPosSb = tmpSb
                    maxSSb = S
                    maxUSb = U
                tmpAb = grammar.aToAb + dv3 + dv4
                if tmpAb >= maxPosAb:
                    maxPosAb = tmpAb
                    maxSAb = S
                    maxUAb = U
            
    sigmasb[s][t][u][v] = maxSSb
    upsilonsb[s][t][u][v] = maxUSb
    sigmaab[s][t][u][v] = maxSAb
    upsilonab[s][t][u][v] = maxUAb
    return (maxPosSb,maxPosAb)

def left(s,t,u,v):
    """left of a node"""
    if theta[s][t][u][v] == 's':
        return (s,sigmasb[s][t][u][v],u,upsilonsb[s][t][u][v])
    if theta[s][t][u][v] == 'a':
        return (s,sigmaab[s][t][u][v],upsilonab[s][t][u][v],v)
    return None

def right(s,t,u,v):
    """right of a node"""
    if theta[s][t][u][v] == 's':
        return (sigmasb[s][t][u][v],t,upsilonsb[s][t][u][v],v)
    if theta[s][t][u][v] == 'a':
        return (sigmaab[s][t][u][v],t,u,upsilonab[s][t][u][v])
    return None

# interface
lines = None
sentence1 = None
sentence2 = None

if __name__ == "__main__":
    dicpath = sys.argv[1]
    f = open(dicpath)
    lines =  f.readlines()
    f.close()
    lexical = LecxicalDictionary(lines)
    grammar1 = BTGGrammar(-1,-2,-20,-21)
    grammar2 = BTGGrammar(-1,float('-inf'),-20,-21)
    ep = sys.argv[2]
    dp = sys.argv[3]
    if len(sys.argv) == 5:
        graAgv = sys.argv[4]
        if graAgv == 'monotonic':
            grammar = grammar2
        elif graAgv == 'swap':
            grammar = grammar1
        else:
            grammar = grammar1
    else:
        grammar = grammar1

    f = open(ep)
    sents1 = f.readlines()
    f.close()
    f = open(dp)
    sents2 = f.readlines()
    f.close()
    for i in range(0,len(sents1)):
        sentence1 = sents1[i][:-1].split(' ')
        sentence2 = sents2[i][:-1].split(' ')
        parseing(sentence1,sentence2,grammar,lexical)
    
    print 'total number of []:', sbCount
    print 'total number of <>:', abCount
    print 'total number of word-<e> align:', epWord
    print 'total number of <e>-word align:', wordEp
    print 'precentage of word-<e>:', (float(epWord)/float(totAlign)) * 100, '%'
    print 'precentage of <e>-word>:', (float(wordEp)/float(totAlign)) * 100, '%'
