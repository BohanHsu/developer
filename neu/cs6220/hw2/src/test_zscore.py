import unittest
from zscore import ZScore

class TestZScore(unittest.TestCase):
    def setUp(self):
        data = [
                [2,2],
                [4,4],
                [4,4],
                [4,4],
                [5,5],
                [5,5],
                [7,7],
                [9,9]
                ]
        self.zs = ZScore(data)

    def test_mues(self):
        self.assertEqual(self.zs.mues, [5,5])

    def test_sigmas(self):
        self.assertEqual(self.zs.sigmas, [2,2])

    def test_normalized_data(self):
        self.assertEqual(self.zs.normalized_data[0][0], -1.5)
        self.assertEqual(self.zs.normalized_data[-1][0], 2)

if __name__ == '__main__':
    unittest.main()
