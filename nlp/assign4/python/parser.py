import sys
# this is a BTG parse
# for natural language processing homework 4

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

    # declare variable
    T = len(sent1)
    V = len(sent2)
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
            delta[t-1][t][v-1][v] = lexical.b(sent1[t-1],sent2[v-1]) #b(et/cv
            
    for t in range(1,T+1):
        for v in range(0,V+1):
            delta[t-1][t][v][v] = grammar.wordEpsilon #b(et/epsilon)    

    for t in range(0,T+1):
        for v in range(1,V+1):
            delta[t][t][v-1][v] = grammar.epsilonWord #b(epsilon/cv)

    # recursion
    for s in range(0,T):
        for t in range(s+1,T+1):
            for u in range(0,V):
                for v in range(u+1,V+1):
                    if (t-s+v-u) > 2:
                        setDelta(s,t,u,v,grammar)
                        
    # resonstruction
    queue = []
    q1 = (0,T,0,V)
    queue.append(q1)
    aligns = []
    while queue.len() > 0:
        q = deque(queue)
        if q[1]-q[0] <= 1 and q[3] - q[2] <= 1:
            aligns.append(q)
    
        lq = left(q[0],q[1],q[2],q[3])
        rq = right(q[0],q[1],q[2],q[3])
        if not lq is None:
            queue.append(lq)
            
        if not rq is None:
            queue.append(rq)

    
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

    if delta[s][t][u][v] is None: 
        dsbab = deltaSbAb(s,t,u,v,grammar)
        dsb = dsbab[0]
        dab = dsbab[1]
        if dsb <= dab:
            delta[s][t][u][v] = dsb
            theta[s][t][u][v] = 's'
        else:
            delta[s][t][u][v] = dab
            theta[s][t][u][v] = 'a'
    
    return delta[s][t][u][v]

def deltaSbAb(s,u,v,t,grammar):
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

    maxPosSb = float('-inf')
    maxSSb = float('-inf')
    maxUSb = float('-inf')
    maxPosAb = float('-inf')
    maxSAb = float('-inf')
    maxUAb = float('-inf')
    for S in range(s,t+1):
        for U in range(u,v+1):
            if not (S-s)*(t-S) + (U-u)*(v-U) == 0:
#               tmpSb = grammar.aToSb * setDelta(s,S,u,U) * setDelta(S,t,U,v)
                tmpSb = grammar.aToSb + setDelta(s,S,u,U,grammar) + setDelta(S,t,U,v,grammar)
                if tmpSb >= maxPosSb:
                    maxPosSb = tmpSb
                    maxSSb = S
                    maxUSb = U
#               tmpAb = grammar.aToAb * setDelta(s,S,U,v) * setDelta(S,t,u,U)
                tmpAb = grammar.aToAb + setDelta(s,S,U,v,grammar) + setDelta(S,t,u,U,grammar)
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
    if t-s+v-u<=2:
        return None
    if theta[s][t][u][v] == 's' and t-s+v-u > 3:
        return (s,sigmasb[s][t][u][v],u,upsilonsb[s][t][u][v])
    if theta[s][t][u][v] == 'a' and t-s+v-u > 2:
        return (s,sigmaab[s][t][u][v],upsilonab[s][t][u][v],v)

def right():
    """right of a node"""
    if t-s+v-u<=2:
        return None
    if theta[s][t][u][v] == 's' and t-s+v-u > 2:
        return (sigmasb[s][t][u][v],t,upsilonsb[s][t][u][v],v)
    if theta[s][t][u][v] == 'a' and t-s+v-u > 2:
        return (sigmaab[s][t][u][v],t,u,upsilonab[s][t][u][v])


# interface
lines = None
sentence1 = None
sentence2 = None
if __name__ == "__main__":
    dicpath = sys.argv[1]
    f = open(dicpath)
    lines =  f.readlines()
    f.close()
    ep = sys.argv[2]
    dp = sys.argv[3]
    f = open(ep)
    sentence1 = f.readline().split(' ')
    f.close()
    f = open(dp)
    sentence2 = f.readline().split(' ')
    f.close()
    
lexical = LecxicalDictionary(lines)
grammar = BTGGrammar(-1,-2,-20,-21)
#def __init__(self,sb,ab,epw,wep):


parseing(sentence1,sentence2,grammar,lexical)
