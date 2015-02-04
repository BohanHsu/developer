import unittest

from quicksort import Qs

class TestQsClass(unittest.TestCase):
    def setUp(self):
        self.q = Qs([2,3,5,1,3,4,67,7,4,73,6435,24,45])

    def test_quicksork(self):
        sorted_array = self.q.sort()
        print sorted_array

if __name__ == '__main__':
    unittest.main()
