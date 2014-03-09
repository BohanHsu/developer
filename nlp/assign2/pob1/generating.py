

# this code is for nlp  assignment 2, question 1.
# author: Bohan Xu
# read rules from grammar file, and generate a sentence by given length

# define data structure

#
class CFL:
    """a context free language"""
    terminal_symbol = []
    non_terminal_symbol = []
    start_symbol = []
    rules = []
    def __init__(self,grammar_path):
        f = open(grammar_path)
        lines = f.readlines()
        f.close()
        for line in lines:
            if cmp(line[-1],'\n') == 0:
                # trim the las return
                line = line[:-1]
            
            if cmp(line,'') == 0:
                # continue
                continue

            if cmp(line[0],'#') == 0:
                # continue
                continue
                
            parts = line.split('\t')
            
            
        




#
class Rule:
    """a rewrite rule"""
    fr = ''
    to = []
    def __init__(self,f,t):
        self.fr = f
        self.to = t
    
