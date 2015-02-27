import math
from fake_random import FakeRandom

class KMeams:
    def __init__(self, training_data, k, maximum_turn = 50):
        self.training_data = training_data
        self.k = k
        self.maximum_turn = maximum_turn
        self.centroids = []
        self.clusters = []
        self._clustering()
        self.sse = self._calc_squared_error()

    # set the initial centroids by fake random number
    def _initial_centroids(self):
        self.centroids = []
        for i in range(0, self.k):
            index = FakeRandom.random() - 1
            #print "index", index
            self.centroids.append(self.training_data[index])

    def _assign_cluster_by_centroids(self):
        self.clusters = [[] for x in range(0, self.k)]
        for i in range(0, len(self.training_data)):
            min = float('inf')
            max_j = 0
            for j in range(0, len(self.centroids)):
                dist = self._euclidean_distance(self.centroids[j],
                        self.training_data[i])
                if dist < min:
                    min = dist
                    min_j = j

            self.clusters[min_j].append(i)

    def _euclidean_distance(self, t1, t2):
        sum = 0
        for i in range(0, len(t1)):
            sum += math.pow((t1[i] - t2[i]), 2)

        return math.sqrt(sum)

    def _calc_centroids_by_clusters(self):
        centroids = []
        for i in range(0, len(self.centroids)):
            centroid = [0 for x in self.training_data[0]]
            length = len(self.clusters[i])
            indices = self.clusters[i]
            for index in self.clusters[i]:
                for j in range(0, len(centroid)):
                    centroid[j] += float(self.training_data[index][j])

            for i in range(0, len(centroid)):
                centroid[i] /= length

            centroids.append(centroid)

        return centroids

    def _clustering(self):
        self._initial_centroids()

        #print "self.centroids", self.centroids

        self._assign_cluster_by_centroids()

        #print "self.clusters", self.clusters

        new_centroids = self._calc_centroids_by_clusters()

        #print "self.centroids", self.centroids
        #print "new_centroids", new_centroids
        
        i = 0
        #while self._cmp_centroids(self.centroids, new_centroids) or i < 50:
        while (not self.centroids == new_centroids) and i < 50:
            self.centroids = new_centroids
            self._assign_cluster_by_centroids()
            new_centroids = self._calc_centroids_by_clusters()
            #print "======"
            #print "cluters", self.clusters
            #print "self.centroids", self.centroids
            #print "new_centroids", new_centroids
            #print "compare", self.centroids == new_centroids
            i += 1


    def _calc_squared_error(self):
        #for index in range(0, len(self.training_data)):
        sse = 0
        for i in range(0, len(self.centroids)):
            centroid = self.centroids[i]
            for tuple_index in self.clusters[i]:
                sse += self._sse_dist(centroid, self.training_data[tuple_index])

        return sse
    
    def _sse_dist(self, centroid, tuple):
        sum = 0
        for i in range(0, len(centroid)):
            sum += math.pow((centroid[i] - tuple[i]), 2)

        return sum
