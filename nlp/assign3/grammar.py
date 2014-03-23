# this code define the data structure of nlp grammar parser


class Rule:
    """a context free grammar rule"""
    alpha = ''
    beta = []
    
    # construct function
    def __init__(self, a, b):
        self.alpha = a
        beta = [z for z in b]



#
#
#
class Grammar:
    """a context free grammar"""
    start = []
    
