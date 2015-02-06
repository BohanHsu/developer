import math

class KNN:

    def __init__(self, training_set, k):
        self.k = k
        self.training_tuples = [line[0:-1] for line in training_set]
        self.classes = [line[-1] for line in training_set]

    def classify_tuple(self, tuple):
        # {index:-1, class: None, distance}
        nearest_tuples = [{'index': -1, 'class': None, 'distance': float('inf')} for i in range(0, self.k)]
        nearest_tuples.sort(key=lambda x: x['distance'])

        for i in range(0, len(self.training_tuples)):
            dist = self._distance(tuple, self.training_tuples[i])
            if dist < nearest_tuples[-1]['distance']:
                nearest_tuples[-1]['index'] = i
                nearest_tuples[-1]['class'] = self.classes[i]
                nearest_tuples[-1]['distance'] = dist
                nearest_tuples.sort(key=lambda x: x['distance'])

        votes = {}
        for cls_hash in nearest_tuples:
            if not cls_hash['class'] in votes:
                votes[cls_hash['class']] = 0

            votes[cls_hash['class']] = votes[cls_hash['class']] + 1

        max1 = 0
        max1_cls = None
        max2 = 0
        max2_cls = None
        for k in votes:
            v = votes[k]
            if v >= max1:
                max2 = max1
                max2_cls = max1_cls
                max1 = v
                max1_cls = k

        if not max1 == max2:
            return max1_cls
        else:
            dist1 = 0
            dist2 = 0
            for cls_hash in nearest_tuples:
                if cls_hash['class'] == max1_cls:
                    dist1 += cls_hash['distance']

                if cls_hash['class'] == max2_cls:
                    dist2 += cls_hash['distance']

            if dist1 > dist2:
                return max1_cls
            else:
                return max2_cls

    def _distance(self, tuple1, tuple2):
        #n = len()
        sum = 0
        for i in range(0, len(tuple1)):
            sum += math.pow((tuple1[i] - tuple2[i]), 2)

        distance = math.sqrt(sum)
        return distance
