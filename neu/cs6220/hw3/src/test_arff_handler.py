import unittest
from arff_handler import ArffHandler

class TestArffHandler(unittest.TestCase):
    def setUp(self):
        self.ah = ArffHandler('./../segment.arff')

    def test_data_set(self):
        print self.ah.lines[-1]
        #print len(self.ah.lines)
        #print self.ah.index

if __name__ == '__main__':
    unittest.main()
