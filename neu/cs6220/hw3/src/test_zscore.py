import unittest
from zscore import ZScore

class TestZScore(unittest.TestCase):
    def setUp(self):
        data = [
                [2,2,10],
                [4,4,10],
                [4,4,10],
                [4,4,10],
                [5,5,10],
                [5,5,10],
                [7,7,10],
                [9,9,10]
                ]
        self.zs = ZScore(data)

    def test_mues(self):
        self.assertEqual(self.zs.mues, [5,5,10])

    def test_sigmas(self):
        self.assertEqual(self.zs.sigmas, [2,2,0])

    def test_normalized_data(self):
        self.assertEqual(self.zs.normalized_data[0][0], -1.5)
        self.assertEqual(self.zs.normalized_data[-1][0], 2)
        self.assertEqual(self.zs.normalized_data[0][2], 0)
        self.assertEqual(self.zs.normalized_data[-1][2], 0)

if __name__ == '__main__':
    unittest.main()
