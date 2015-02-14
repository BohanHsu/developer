import unittest
from arff_handler import ArffHandler

class TestArffHandler(unittest.TestCase):
    def setUp(self):
        self.ah = ArffHandler('./../hw2-data/train.arff')

    def test_data_set(self):
        print self.ah.lines
        print len(self.ah.lines)
        print self.ah.index

if __name__ == '__main__':
    unittest.main()
