import numpy as np
import math

class LinearRegression:

    def linear_regression(self, x, y, lambdaa):
        vx = np.matrix(x)
        vxT = vx.getT()
        vy = np.matrix(y)
        identity_matrix = np.matrix(np.identity(len(x[0])))
        w1 = np.matrix.dot(vxT, vx)
        w2 = np.add(w1, lambdaa * identity_matrix)
        w3 = np.matrix.dot(w2.getI(), vxT)
        w = np.matrix.dot(w3, vy)
        return self.matrix_to_list(w)

    # @params:
    # x: a list of list
    # y: a list
    # w: a list
    def mean_square_error(self, x, y, w):
        wT = np.matrix(w).getT()
        N = len(x)
        vy = np.matrix(y)
        sum = 0
        for i in range(0, len(x)):
            vxnT = np.matrix(x[i])
            yn = np.matrix(y[i])
            vxn = vxnT.getT()
            sum = sum + math.pow((np.matrix.dot(wT, vxn) - yn), 2)

        ew = sum / float(N)
        return ew

    # return a list represent same matrix
    def matrix_to_list(self, matrix):
        return np.array(matrix).tolist()

