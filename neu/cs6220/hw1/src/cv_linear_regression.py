from linear_regression import LinearRegression

class CVLinearRegression:
    def cv_linear_regression(self, x, y, lambdaa, fold):
        lr = LinearRegression()
        step = len(x) / fold
        mses = []
        for i in range(0, fold):
            index_begin = i * step
            index_end = (i + 1) * step
            xs = self.split_cv_set(x, index_begin, index_end)
            training_x = xs[0]
            test_x = xs[1]
            ys = self.split_cv_set(y, index_begin, index_end)
            training_y = ys[0]
            test_y = ys[1]
            w = lr.linear_regression(training_x, training_y, lambdaa)
            mse = lr.mean_square_error(test_x, test_y, w, lambdaa)
            mses.append(mse)

        #average_mse = mses.reduce(lambda x, y: x + y) / float(fold)
        average_mse = reduce(lambda x, y: x + y, mses) / float(fold)
        return average_mse

    # return: [train_set, test_set]
    def split_cv_set(self, set, index_begin, index_end):
        training_set = []
        test_set = []
        for i in range(0, len(set)):
            if i < index_begin or i >= index_end:
                training_set.append(set[i])
            else:
                test_set.append(set[i])

        return [training_set, test_set]


