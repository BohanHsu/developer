import unittest

from data_handler import DataHandler as DH

class TestDataHandler(unittest.TestCase):
    def setUp(self):
        pass

    def test_read_data(self):
        d1 = DH('./../data/train-100-10.csv')
        print d1.x[0]
        print d1.y[0]

if __name__ == '__main__':
    unittest.main();
