import unittest

from minmax import MinMax as MM

class TestMinMaxClass(unittest.TestCase):
    def setUp(self):
        self.m = MM([[1, 6], [3, 4], [5, 2]])

    def test_minmax1(self):
        self.assertEqual(self.m.mins, [1, 2])
        self.assertEqual(self.m.maxs, [5, 6])

    def test_minmax2(self):
        self.assertEqual(self.m.normalized_data, [[0, 1], [0.5, 0.5], [1, 0]])

if __name__ == '__main__':
    unittest.main()
