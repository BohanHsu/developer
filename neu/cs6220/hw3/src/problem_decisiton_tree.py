from collections import deque
from selector import information_gain
from decision_tree import DecisionTree
from decision_tree import Node


def main():
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
    
    trains = []
    for i in range(0, len(data)):
        trains.append(data[i] + [cls[i]])
    
    d = DecisionTree(trains, attribute_names, information_gain)
    
    r = d.root
    q = deque([])
    q.append(r)
    
    while q:
        n = q.popleft()
        show_node(n, trains)
        for k in n.posterities:
            q.append(n.posterities[k])
    
    print "======= hua li de fen ge xian ======="
    
    test_prune_data = [
            ['High School', 'Management', 'More than 10'],
            ['College', 'Management', 'Less than 3'],
            ['College', 'Service', '3 to 10']
            ]
    
    test_prune_class = ['High','Low','Low']
    
    d.prune_tree(test_prune_data, test_prune_class)
    
    #r = d.root
    #q = deque([])
    #q.append(r)
    #
    #while q:
    #    n = q.popleft()
    #    show_node(n, trains)
    #    for k in n.posterities:
    #        q.append(n.posterities[k])

def show_node(n, trains):
    print '======================='
    print 'id', n.id
    if n.parent_node:
        print 'parent', n.parent_node.id

    if n.cls:
        print 'class', n.cls
    else:
        if n.selected_attribute:
            print 'selected attribute', n.attribute_names[n.selected_attribute]
        print 'selection criteria', n.selection_criteria
        attrs = []
        if n.attribute_names:
            for i in n.attributes_left:
                attrs.append(n.attribute_names[i])

        print 'attributes lefted', attrs
        print 'posterites', [(x, n.posterities[x].id) for x in n.posterities]
        #print 'data left', n.left_data_index

        #print 'right count', n.right_count
        #print 'wrong count', n.wrong_count

if __name__ == '__main__':
    main()
