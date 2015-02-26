from arff_handler import ArffHandler
from knn import KNN
#from minmax import MinMax
from zscore import ZScore


#training_set = ArffHandler('./../hw2-data/train.arff').lines
#test_set = ArffHandler('./../hw2-data/test.arff', False)

training_set = ArffHandler('./../hw2-data/train.arff')
test_set = ArffHandler('./../hw2-data/test.arff', False)

#print test_set.lines

training_set_data = []
training_set_class = []

for line in training_set.lines:
    training_set_data.append(line[0:-1])
    training_set_class.append(line[-1])

#normalizer = MinMax(training_set_data)
#normalized_training_data = normalizer.normalized_data

training_normalizer = ZScore(training_set_data)
test_normalizer = ZScore(test_set.lines)

normalized_training_data = training_normalizer.normalized_data
normalized_test_data = test_normalizer.normalized_data

training_set = []
for i in range(0, len(normalized_training_data)):
    training_set.append(normalized_training_data[i] + [training_set_class[i]])

#print training_set
#print test_set.lines

knns = {}

for k in [1,3,5,7,9]:
    knns[k] = KNN(training_set, k)

results = []

for i in range(0, len(test_set.lines)):
    tuple = test_set.lines[i]
    result = list(tuple)
    result.append(test_set.index[i])

    #for j in range(0, len(tuple)):
    #    tuple[j] = normalizer.normalize_data(tuple[j], j)
    tuple = normalized_test_data[i]

    for k in [1,3,5,7,9]:
        knn = knns[k]
        res = knn.classify_tuple(tuple)
        result.append(res)

    results.append(result)

for result in results:
    for i in range(0, 4):
        result[i] = str(result[i])
    print ",".join(result)
    #if not (result[-1] == result[-2] and result[-2] == result[-3] and result[-3] == result[-4] and result[-4] == result[-5]):
    #    #print "!!!!!"
    #    #print "^"
    #    #print "|"





