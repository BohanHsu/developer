import unittest
from kmeans import KMeams
from fake_random import FakeRandom

class TestKMeansClass(unittest.TestCase):
    #def setUp(self):
    #    FakeRandom.set_sequence([1,2,3] * 100)
    #    self.training_set = [
    #            [0,0],
    #            [1,1],
    #            [1,2],
    #            [1,4],
    #            [1,8],
    #            [50, 50],
    #            [51, 51],
    #            [102, 102],
    #            [100, 100],
    #            [101, 101]
    #            ]
    #    self.kmeans = KMeams(self.training_set, 3)

    #def test_initial_centroids(self):
    #    kmeans = KMeams(self.training_set, 3)
    #    kmeans._initial_centroids()
    #    self.assertEqual(kmeans.centroids, [[0,0],[1,1],[1,2]])

    #def test_assign_cluster_by_centroids(self):
    #    kmeans = KMeams(self.training_set, 3)
    #    kmeans._initial_centroids()
    #    self.assertEqual(kmeans.centroids, [[0,0],[1,1],[1,2]])
    #    kmeans._assign_cluster_by_centroids()
    #    self.assertEqual(kmeans.clusters, [[0], [1], [2, 3, 4, 5, 6, 7, 8, 9]])

    #def test_calc_centroids_by_clusters(self):
    #    kmeans = KMeams(self.training_set, 3)
    #    kmeans._initial_centroids()
    #    self.assertEqual(kmeans.centroids, [[0,0],[1,1],[1,2]])
    #    kmeans._assign_cluster_by_centroids()
    #    self.assertEqual(kmeans.clusters, [[0], [1], [2, 3, 4, 5, 6, 7, 8, 9]])
    #    self.assertEqual(kmeans._calc_centroids_by_clusters(), [[0.0, 0.0], [1.0, 1.0], [50.875,
    #        52.25]])

    def setUp(self):
        FakeRandom.set_sequence([1,2,3] * 100)
        self.training_set = [
                [0,0],
                [50, 50],
                [100, 100],
                [1,1],
                [51, 51],
                [101, 101]
                ]
        self.kmeans = KMeams(self.training_set, 3)

    def test_initial_centroids(self):
        kmeans = KMeams(self.training_set, 3)
        kmeans._initial_centroids()
        self.assertEqual(kmeans.centroids, [[0,0],[50,50],[100,100]])

    def test_assign_cluster_by_centroids(self):
        kmeans = KMeams(self.training_set, 3)
        kmeans._initial_centroids()
        self.assertEqual(kmeans.centroids, [[0,0],[50,50],[100,100]])
        kmeans._assign_cluster_by_centroids()
        self.assertEqual(kmeans.clusters, [[0, 3], [1, 4], [2, 5]])

    def test_calc_centroids_by_clusters(self):
        kmeans = KMeams(self.training_set, 3)
        kmeans._initial_centroids()
        self.assertEqual(kmeans.centroids, [[0,0],[50,50],[100,100]])
        kmeans._assign_cluster_by_centroids()
        self.assertEqual(kmeans.clusters, [[0, 3], [1, 4], [2, 5]])
        self.assertEqual(kmeans._calc_centroids_by_clusters(), [[0.5, 0.5],
            [50.5, 50.5], [100.5, 100.5]])

    def test_sse(self):
        self.assertEqual(self.kmeans.sse, 3)

    #def test_code(self):
    #    self.kmeans

if __name__ == "__main__":
    unittest.main()

