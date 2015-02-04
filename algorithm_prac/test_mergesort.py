import unittest

from mergesort import Ms

class TestMsClass(unittest.TestCase):
    def setUp(self):
        self.m = Ms([2,3,5,1,3,4,67,7,4,73,6435,24,45])

    def test_mergesort (self):
        sorted_array = self.m.merge_sort()
        print sorted_array

if __name__ == '__main__':
    unittest.main()
