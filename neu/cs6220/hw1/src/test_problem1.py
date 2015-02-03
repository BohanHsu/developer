import unittest

from linear_regression import LinearRegression

class TestLinearRegression(unittest.TestCase):
    def setUp(self):
        self.p = LinearRegression()

    #def test_mean_square_error(self):
    #    x = [
    #            [1, 2, 3], 
    #            [1, 5, 6], 
    #            [1, 8, 9]]
    #    y = [[6], [3], [-2]]
    #    w = [[7], [4], [1]]
    #    ew = self.p.mean_square_error(x, y, w, 0)
    #    ew_should_be = 3544 / float(3)
    #    self.assertEqual(ew, ew_should_be)

    #def test_mean_square_error1(self):
    #    x = [
    #            [1, 2, 3], 
    #            [1, 5, 6], 
    #            [1, 8, 9]]
    #    y = [[6], [3], [-2]]
    #    w = [[7], [4], [1]]
    #    lambdaa = 1
    #    ew = self.p.mean_square_error(x, y, w, lambdaa)
    #    ew_should_be = 3544 / float(3)
    #    self.assertEqual(ew, ew_should_be)

    #def test_linear_regression(self):
    #    x = [
    #            [1, 2, 3], 
    #            [1, 5, 6], 
    #            [1, 8, 9]]
    #    y = [[6], [3], [-2]]
    #    lambdaa = 2

    #    w = [round(x[0], 4) for x in self.p.linear_regression(x, y, lambdaa)]
    #    w_should_be = [round(x[0], 4) for x in [
    #            [1.73442623],
    #            [-0.99672131],
    #            [0.73770492]]]

    #    self.assertEqual(w, w_should_be)

    def test_linear_regression1(self):
        train_x = [
                [1, 2],
                [1, 3]
                ]
        train_y = [
                [3],
                [4]
                ]
        lambdaa = 1
        w = self.p.linear_regression(train_x, train_y, lambdaa)
        print w
        test_x = [[1, 4]]
        test_y = [[5]]
        train_mse = self.p.mean_square_error(train_x, train_y, w, lambdaa)
        test_mse = self.p.mean_square_error(test_x, test_y, w, lambdaa)
        print 'train_mse', train_mse
        print 'test_mse', test_mse


if __name__ == '__main__':
    unittest.main()
