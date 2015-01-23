import unittest

from problem1 import Problem1

class TestProblem1(unittest.TestCase):
    def setUp(self):
        self.p = Problem1()

    def test_mean_square_error(self):
        x = [
                [1, 2, 3], 
                [1, 5, 6], 
                [1, 8, 9]]
        y = [[6], [3], [-2]]
        w = [[7], [4], [1]]
        ew = self.p.mean_square_error(x, y, w)
        ew_should_be = 3544 / float(3)
        self.assertEqual(ew, ew_should_be)


    def test_linear_regression(self):
        x = [
                [1, 2, 3], 
                [1, 5, 6], 
                [1, 8, 9]]
        y = [[6], [3], [-2]]
        lambdaa = 2

        w = [round(x[0], 4) for x in self.p.linear_regression(x, y, lambdaa)]
        w_should_be = [round(x[0], 4) for x in [
                [1.73442623],
                [-0.99672131],
                [0.73770492]]]

        self.assertEqual(w, w_should_be)

if __name__ == '__main__':
    unittest.main()
