import unittest

from knn import KNN

class TestKNNClass(unittest.TestCase):
    def setUp(self):
        train_data = [
                [-2, -2, 'n'],
                [0, 0, 'n'],
                [1, 1, 'n'],
                [3, 3, 'y'],
                [5, 5, 'y'],
                [5, 6, 'y'],
                ]
        self.test_data = [2, 2]
        self.knn3 = KNN(train_data, 3)
        self.knn5 = KNN(train_data, 5)
        self.knn6 = KNN(train_data, 6)

    def test_knn_3(self):
        self.assertEqual(self.knn3.classify_tuple(self.test_data), 'n')

    def test_knn_5(self):
        self.assertEqual(self.knn5.classify_tuple(self.test_data), 'y')

    def test_knn_6(self):
        self.assertEqual(self.knn6.classify_tuple(self.test_data), 'y')

if __name__ == '__main__':
    unittest.main()
