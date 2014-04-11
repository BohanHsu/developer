from nltk import load_parser

str = 'feed Cyril and give a cappucino to Angus'

parser = load_parser('grammars/book_grammars/simple-sem.fcfg', trace=0)
sentence = str
tokens = sentence.split()
trees = parser.nbest_parse(tokens)
for tree in trees:
    print tree.node['SEM']
