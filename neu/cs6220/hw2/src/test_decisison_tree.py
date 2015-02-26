import unittest
from collections import deque

from selector import information_gain
#import decision_tree as dt
from decision_tree import DecisionTree
from decision_tree import Node

class TestDecisisonClass(unittest.TestCase):
    def setUp(self):
        #data = [
        #    ['youth',          'high',   'no',  'fair'],
        #    ['youth',          'high',   'no',  'excellent'],
        #    ['middle aged',    'high',   'no',  'fair'],
        #    ['senior',         'medium', 'no',  'fair'],
        #    ['senior',         'low',    'yes', 'fair'],
        #    ['senior',         'low',    'yes', 'excellent'],
        #    ['middle aged',    'low',    'yes', 'excellent'], 
        #    ['youth',          'medium', 'no',  'fair'],
        #    ['youth',          'low',    'yes', 'fair'],
        #    ['senior',         'medium', 'yes', 'fair'], 
        #    ['youth',          'medium', 'yes', 'excellent'],
        #    ['middle aged',    'medium', 'no',  'excellent'],
        #    ['middle aged',    'high',   'yes', 'fair'],
        #    ['senior',         'medium', 'no',  'excellent']
        #]
        #yes = 'yes'
        #no = 'no'
        #cls = [no,no,yes,yes,yes,no,yes,no,yes,yes,yes,yes,yes,no]


        data = [
            ['High School','Management','Less than 3'],
            ['High School','Management','3 to 10'],
            ['College','Management','Less than 3'],
            ['College','Service','More than 10'],
            ['High School','Service','3 to 10'],
            ['College','Service','3 to 10'],
            ['College','Management','More than 10'],
            ['College','Service','Less than 3'],
            ['High School','Management','More than 10'],
            ['High School','Service','More than 10']]

        attribute_names = ['Education Level', 'Career', 'Years of Experience']

        cls = ['Low','Low','High','Low','Low','High','High','Low','High','Low']

        self.trains = []
        for i in range(0, len(data)):
            self.trains.append(data[i] + [cls[i]])

        self.d = DecisionTree(self.trains, attribute_names, information_gain)

    def test_decision_tree(self):
        r = self.d.root
        q = deque([])
        q.append(r)

        print "======= hua li de fen ge xian ======="

        while q:
            n = q.popleft()
            show_node(n, self.trains)
            for k in n.posterities:
                q.append(n.posterities[k])

        print "======= hua li de fen ge xian ======="

        test_prune_data = [
                ['High School', 'Management', 'More than 10'],
                ['College', 'Management', 'Less than 3'],
                ['College', 'Service', '3 to 10']
                ]

        test_prune_class = ['High','Low','Low']

        self.d.prune_tree(test_prune_data, test_prune_class)

        r = self.d.root
        q = deque([])
        q.append(r)

        while q:
            n = q.popleft()
            show_node(n, self.trains)
            for k in n.posterities:
                q.append(n.posterities[k])

def show_node(n, trains):
    print '======================='
    print 'node', n
    print 'id', n.id
    if n.parent_node:
        print 'parent', n.parent_node.id
    if n.selected_attribute:
        print 'selected attribute', n.attribute_names[n.selected_attribute]
    print 'selection criteria', n.selection_criteria
    #print 'attributes lefted', n.attributes_left
    attrs = []
    if n.attribute_names:
        for i in n.attributes_left:
            attrs.append(n.attribute_names[i])

    print 'attributes lefted', attrs
    print 'posterites', [(x, n.posterities[x].id) for x in n.posterities]
    #print 'posterites', n.posterities
    print 'data left', n.left_data_index

    print '~~~~~~~~~~'
    for i in n.left_data_index:
        print trains[i]
    print '~~~~~~~~~~'

    print 'majority vote', n.majority_vote
    print 'right count', n.right_count
    print 'wrong count', n.wrong_count
    print 'class', n.cls

#def show_node1(n):
#    if n.cls == 'unknown1':
#        print '======================='
#        n = n.parent_node
#        print 'selected attribute', n.selected_attribute
#        print 'attributes lefted', n.attributes_left
#        print 'posterites', [x for x in n.posterities]
#        print 'data left', n.left_data_index
#        print 'class', n.cls

if __name__ == '__main__':
    unittest.main()
