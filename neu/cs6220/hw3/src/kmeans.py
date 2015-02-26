import math
from fake_random import FakeRandom

class KMeams:
    def __init__(self, training_data, k):
        self.training_data = training_data
        self.k = k
        self.centroids = []
        self.clusters = []

    # set the initial centroids by fake random number
    def _initial_centroids(self):
        for i in range(0, k):
            index = FakeRandom.random() - 1
            self.centroids.append(self.training_data[index])

    def _assign_cluster_by_centroids(self):
        self.clusters = [[] for x in range(0, k)]
        for i in range(0, len(self.training_data)):
            max = 0
            max_j = 0
            for j in range(0, len(self.centroids)):
                dist = self._euclidean_distance(self.centroids[j],
                        self.training_data[i])
                if dist > max:
                    max = dist
                    max_j = j

            self.clusters[j].append(i)

    def _euclidean_distance(self, t1, t2):
        sum = 0
        for i in range(0, len(t1)):
            sum += math.pow((t1[i] - t2[i]), 2)

        return math.sqrt(sum)

    def _calc_centroids_by_clusters(self):
        centroids = []
        for i in range(0, self.centroids):
            centroid = [0 for x in self.training_data[0]]
            length = len(self.clusters[i])
            indices = self.clusters[i]
            for index in self.clusters[i]:
                for j in range(0, len(centroid)):
                    centroid[j] += self.training_data[index][j]

            for i in range(0, len(centroid)):
                centroid[i] /= length

            centroids.append(centroid)

    def _clustering(self):
        self._initial_centroids()

        self._assign_cluster_by_centroids()
        new_centroids = self._calc_centroids_by_clusters()
        
        while self._cmp_centroids(self.centroids, new_centroids):
            self.centroids = new_centroids
            self._assign_cluster_by_centroids()
            new_centroids = self._calc_centroids_by_clusters()
