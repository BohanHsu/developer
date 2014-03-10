import nltk

def drawTree(s):
    tree = nltk.Tree.parse(s, remove_empty_top_bracketing=True)
    tree.draw() 

#s = '(ROOT (SQ (VBP do) (NP (NNS coconuts)) (VP (VB speak)) (. ?)))'

#s = '(ROOT (SBARQ (WHNP (WP who)) (SQ (VBZ does) (NP (NNP Arthur)) (VP (VB suggest) (S (NP (PRP she)) (VP (VB carry))))) (. ?)))'

#s = '(ROOT (SQ (VBP are) (NP (PRP they)) (VP (VBG suggesting) (NP (NNP Arthur) (NN ride)) (PP (TO to) (NP (NNP Camelot)))) (. ?)))'

#s = '(ROOT (S (NP (DT the) (NNP Holy) (NNP Grail)) (VP (VBD was) (VP (VBN covered) (PP (IN by) (NP (DT a) (JJ yellow) (NN fruit))))) (. .)))'

#s = '(ROOT (S (VP (VB do) (RB not) (VP (VB speak))) (. !)))'

#s = '(ROOT (S (NP (NNP Arthur)) (VP (MD will) (VP (VB have) (VP (VBN been) (VP (VBG riding) (PP (IN for) (NP (CD eight) (NNS nights))))))) (. .)))'

#s = '(ROOT (S (NP (NNP Arthur) (CC and) (NNP Guinevere)) (VP (VB migrate) (ADVP (RB frequently))) (. .)))' 

#s = '(ROOT (S (NP (PRP he)) (VP (VBZ knows) (SBAR (WHNP (WP what)) (S (NP (PRP they)) (VP (VBP are) (VP (VBG covering) (PP (IN with) (NP (DT that) (NN story)))))))) (. .)))'

#s = '(ROOT (S (NP (DT the) (NN king)) (VP (VBD drank) (PP (TO to) (NP (NP (D the) (NN castle)) (SBAR (WHNP (WDT that)) (S (VP (VBD was) (NP (PRP$ his) (NN home)))))))) (. .)))'

drawTree(s)
