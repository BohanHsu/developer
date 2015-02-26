from collections import deque

class DecisionTree:
    n_id = 0

    def __init__(self, training_set, attribute_names, attr_selector):
        self.training_data = [x[:-1] for x in training_set]
        self.classes = [x[-1] for x in training_set]
        self.attr_selector = attr_selector
        self.attribute_names = attribute_names
        self.root = None
        self.queue = deque([])
        self.build_tree()
    
    def build_tree(self):
        root = Node(DecisionTree.n_id)
        DecisionTree.n_id = DecisionTree.n_id + 1
        root.left_data_index = range(0, len(self.training_data))
        root.attributes_left = range(0, len(self.training_data[0]))
        self.root = root
        root.majority_vote = self.majority_vote_node(root)
        self.queue.append(root)

        while self.queue:
            n = self.queue.popleft()
            self.split_node(n)

    def split_node(self, node):
        if not node.attributes_left:
            # what's the class?
            counts = {}
            for i in node.left_data_index:
                value = self.classes[i]
                if not value in counts:
                    counts[value] = 0

                counts[value] = counts[value] + 1

            max = float('-inf')
            node_cls = 'unknown1'

            for k in counts:
                v = counts[k]
                if v > max:
                    max = v
                    node_cls = k

            node.cls = node_cls

        elif not node.left_data_index:
            # there is no data left but still attributes
            node.cls = 'unknown2'
        else:
            train_data = []
            cls_data = []
            for i in node.left_data_index:
                train_data.append(self.training_data[i])
                cls_data.append(self.classes[i])

            #print 'train_data', train_data
            #print 'cls_data', cls_data

            if (len(set(cls_data)) == 1):
                node.cls = cls_data[0]

            else:
                selector_result = self.attr_selector(train_data, self.attribute_names, cls_data, node.attributes_left)
                selected_attrubte_index = selector_result[0]
                node.selection_criteria = selector_result[1]
                node.selected_attribute = selected_attrubte_index
                node.attributes_left.remove(selected_attrubte_index)
                node.attribute_names = self.attribute_names

                for value in set([x[selected_attrubte_index] for x in train_data]):
                    n = Node(DecisionTree.n_id)
                    DecisionTree.n_id = DecisionTree.n_id + 1
                    n.attributes_left = list(node.attributes_left)
                    n.parent_node = node
                    node.posterities[value] = n
                    data_after_filter = []
                    for i in node.left_data_index:
                        if self.training_data[i][node.selected_attribute] == value:
                            data_after_filter.append(i)

                    n.left_data_index = data_after_filter
                    n.majority_vote = self.majority_vote_node(n)
                    self.queue.append(n)

    def majority_vote_node(self, node):
        # first vote the node
        counts = {}
        for i in node.left_data_index:
            cls = self.classes[i]
            if not cls in counts:
                counts[cls] = 0

            counts[cls] = counts[cls] + 1

        max = float('-inf')
        max_k = None
        for k in counts:
            v = counts[k]
            if v > max:
                max = v
                max_k = k
                
        # max_k is the majority vote
        return max_k

    def classify_test_data(self, data, data_cls):
        node = self.root

        while node:
            if data_cls == node.majority_vote:
                node.right_count = node.right_count + 1
            else:
                node.wrong_count = node.wrong_count + 1

            if not node.cls:
                attribute_value = data[node.selected_attribute]
                node = node.posterities[attribute_value]
            else:
                # this is a leaf
                node = None

    def _reset_prune_count(self):
        q = deque([])
        n = self.root
        q.append(n)
        while q:
            n = q.popleft()
            n.wrong_count = 0
            n.right_count = 0
            for k in n.posterities:
                v = n.posterities[k]
                q.append(v)

    def prune_tree(self, data, data_cls):
        pruned = True
        while pruned:
            self._reset_prune_count()
            for i in range(0, len(data)):
                self.classify_test_data(data[i], data_cls[i])

            pruned = False
            q = deque([])
            s = []
            n = self.root
            q.append(n)
            while q:
                n = q.popleft()
                s.append(n)
                for k in n.posterities:
                    v = n.posterities[k]
                    q.append(v)

            while s:
                n = s.pop()
                #print '-----'
                #print 'n', n
                #print 'n.wrong', n.wrong_count
                n.wrong_count
                sum = 0
                for k in n.posterities:
                    v = n.posterities[k]
                    sum += v.wrong_count
                    #print 'v', v
                    #print 'v.wrong', v.wrong_count

                print 'pruning process:', n.id, sum, n.wrong_count
                    
                if n.wrong_count < sum:
                    # prune n
                    n.posterities = {}
                    n.cls = n.majority_vote
                    pruned = True
                    #print '~~~pruning n', n, n.wrong_count
                    print '+++++pruning n', n.id, n.wrong_count, sum
                    break
                else:
                    if not n.cls:
                        n.wrong_count = sum

    def make_decision(self, data_tuple):
        node = self.root
        cls = node.cls
        while not cls:
            attribute_value = data_tuple[node.selected_attribute]
            node = node.posterities[attribute_value]
            cls = node.cls

        return cls

class Node:
    def __init__(self, id):
        self.id = id
        self.parent_node = None
        self.selected_attribute = None
        self.attribute_names = None
        self.attribute_type = None
        # index of lefted attributes
        self.attributes_left = None
        # if is numerical:
        self.split_value = None
        self.posterities = {}
        #if is a leaf node
        self.majority_vote = None
        self.cls = None
        self.left_data_index = None
        
        # data for prune tree 
        self.right_count = 0
        self.wrong_count = 0

        self.selection_criteria = None
