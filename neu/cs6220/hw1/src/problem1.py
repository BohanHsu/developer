import numpy as np
import math

class Problem1:

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

class DataHandler:
    def __init__(self, file_path, training=True):
        f = open(file_path)
        lines = f.readlines()
        s = lines[0]
        ls = s.split('\r')
        self.row_data = [x.split(',') for x in ls][1:-1]
        self.y = [[float(x[-1])] for x in self.row_data]
        self.x = [[1] + [float(z) for z in x][1:] for x in self.row_data]

data_file = [   'train-100-10.csv',
                'test-100-10.csv',
                'test-1000-100.csv',
                'train-1000-100.csv',
                'test-100-100.csv',
                'train-100-100.csv',
                '50(1000)_100_train.csv',
                '100(1000)_100_train.csv',
                '150(1000)_100_train.csv']

train_test_set = {
            'train-100-10.csv':'test-100-10.csv',
            'train-1000-100.csv':'test-1000-100.csv',
            'train-100-100.csv':'test-100-100.csv',
            '50(1000)_100_train.csv':'test-1000-100.csv',
            '100(1000)_100_train.csv':'test-1000-100.csv',
            '150(1000)_100_train.csv':'test-1000-100.csv'
        }

def main():
    p = Problem1()
    results = {}
    for file_name in train_test_set:
        results[file_name] = []
        file_path = './../data/' + file_name
        train_set = DataHandler(file_path)
        test_set = DataHandler('./../data/' + train_test_set[file_name])
        for i in range(0, 151):
            #print 'train_set.x', train_set.x
            #print 'train_set.y', train_set.y
            w = p.linear_regression(train_set.x, train_set.y, i)
            training_set_mse = p.mean_square_error(train_set.x, train_set.y, w)
            test_set_mse = p.mean_square_error(test_set.x, test_set.y, w)
            results[file_name].append([w, training_set_mse, test_set_mse])
            print '====='
            print 'i', i
            print 'w', w
            print 'file_name', file_name
            print 'training_set_mse', training_set_mse
            print 'test_set_mse', test_set_mse

    print results
    
if __name__ == '__main__':
    main()
