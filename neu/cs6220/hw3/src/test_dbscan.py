import unittest
from dbscan import DBScan

class TestDBScanClass(unittest.TestCase):
    def setUp(self):
        training_set = [
                (0,0),
                (1,1),
                (1,2),
                (1,4),
                (1,8),
                (50, 50),
                (51, 51),
                (100, 100),
                (102, 102),
                (101, 101)
                ]
        eps = 5
        min_pts = 2
        self.dbs = DBScan(eps, min_pts, training_set, distance)

    def test_n_eps_p(self):
        self.assertEqual(self.dbs.n_eps_p[(0,0)], [(1, 1), (1, 2), (1, 4)])

    def test_core_pointes(self):
        self.assertEqual(list(self.dbs.core_points),[(1, 2), (0, 0), (101, 101),
            (102, 102), (100, 100), (1, 4), (1, 1)])

    #def test_clusters(self):
    #    #self.assertEqual(self.dbs.clusters, [[(0, 0), (1, 1), (1, 2), (1, 4),
    #        #(1, 8)], [(101, 101), (100, 100), (102, 102)]])


def distance(n1, n2):
    return abs(n1[0] - n2[0]) + abs(n1[1] - n2[1])

if __name__ == '__main__':
    unittest.main()
