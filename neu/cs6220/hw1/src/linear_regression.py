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
    def mean_square_error(self, x, y, w, lambdaa):
        N = len(x)
        vx = np.matrix(x)
        vw = np.matrix(w)
        vwT = vw.getT()
        vy = np.matrix(y)
        w1 = np.matrix.dot(vx, vw)
        w2 = w1 - y
        w2T = w2.getT()
        w3 = np.matrix.dot(w2T, w2)
        #w4 = np.matrix.dot(vwT, vw)
        #ew = 1 / float(N) * w3 + lambdaa * w4
        ew = 1 / float(N) * w3
        return math.pow(ew, 1)

    # return a list represent same matrix
    def matrix_to_list(self, matrix):
        return np.array(matrix).tolist()
