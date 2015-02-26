import unittest
from naive_bayes import NaiveBayes

class TestNaiveBayesClass(unittest.TestCase):

    def setUp(self):
        training_set = [
                    ["Sunny", "No"],
                    ["Sunny", "No"],
                    ["Overcast", "Yes"],
                    ["Rainy", "Yes"],
                    ["Rainy", "Yes"],
                    ["Rainy", "No"],
                    ["Overcast", "Yes"],
                    ["Sunny", "No"],
                    ["Sunny", "Yes"],
                    ["Rainy", "Yes"],
                    ["Sunny", "Yes"],
                    ["Overcast", "Yes"],
                    ["Overcast", "Yes"],
                    ["Rainy", "No"]
                ]

        self.nb = NaiveBayes(training_set)

    def test_calc_pcs(self):
        self.assertEqual(self.nb.pcs['Yes'], 0.6428571428571429)
        self.assertEqual(self.nb.pcs['No'], 0.35714285714285715)

    def test_calc_cxcis(self):
        self.assertEqual(self.nb.cxcis['Yes'][0]['Sunny'], 2)
        #self.assertEqual(self.nb.cxcis['No'][0]['Overcast'], 0)

    def test_classify(self):
        training_set = [
                [1,'a', 'Y'],
                [1,'a', 'Y'],
                [1,'b', 'Y'],
                [1,'b', 'Y'],
                [2,'a', 'Y'],
                [2,'a', 'Y'],
                [2,'b', 'N'],
                [2,'b', 'N'],
                [1,'b', 'Y'],
                [1,'b', 'Y']
                ]
        nb = NaiveBayes(training_set)
        self.assertEqual(nb.classify([2, 'a']), 'Y')
        self.assertEqual(nb.classify([1, 'c']), 'Y')

if __name__ == "__main__":
    unittest.main()
