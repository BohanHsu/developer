# in this file we start using nltk.py to learning npl
# this script should be parse by the mac built-in python, 2.7 version

# import division from __future__ package
# that is to make sure python doing floating point division
from __future__ import division
# importing from nltk
from nltk.book import *

# calling concordance() method on text display the searched context in the text
text1.concordance('monstrous')

## search another word in other text
#text2.concordance('affection')

# the concordance() run fast.

# what other word appear in the similar range in the context?
text1.similar('monstrous')

# common_texts the context shared by two or more world 
text2.concordance('monstrous')
text2.concordance('very')

# is that means this line will give the senario that both 'monstrous' and 'very' happen in text2???
text2.common_contexts(['monstrous','very'])

# so if word W1 occur in the same pattern with W0 in T1, the T1.similar('W0') will give W1, 
# and T1.common_contexts(['W0','W1']) will give the pattern????

# this function give a plot diagram of given word's occurrence in a text.
# Lexical Dispersion plot
text4.dispersion_plot(['citizen','democracy'])

# random generating some context in the various styles of some given texts
text3.generate()
# currently I don't know where to use this function

##
# counting vocabulary
len(text3)
# I tried to count a string it success but count a int it raise a Error.
# the len calls the object's len() method, and int object don't have this method

#: CONCEPT:
# a TOKEN is the technical name for a sequence of characters that we want to treat as a group

# eliminate duplicate token in text3 
set(text3)
sorted(set(text3))
# then we can count how much distinct word in this text

len(sorted(set(text3)))
# this line return 2789

#: CONCEPT
# word type: is the form or spelling of the word independently of it's specific occurrences in a text.
# the word considered as a unique item of vocabulary
# because we considered punctuation as a 'word', we call this set types

# on average, how much times each word used in text3
# lexical richness of the text
len(text3) / len(set(text3))

# how often a word occur in a text, and the percentage of the occurrence of this word
text3.count('smote')
text4.count('a')

text4.count('a') / len(text4) * 100



