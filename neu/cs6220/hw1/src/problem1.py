import numpy as np
import math

from linear_regression import LinearRegression
from data_handler import DataHandler

train_test_set = {
            'train-100-10.csv':'test-100-10.csv',
            'train-1000-100.csv':'test-1000-100.csv',
            'train-100-100.csv':'test-100-100.csv',
            '50(1000)_100_train.csv':'test-1000-100.csv',
            '100(1000)_100_train.csv':'test-1000-100.csv',
            '150(1000)_100_train.csv':'test-1000-100.csv'
        }

def main():
    p = LinearRegression()
    results = {}
    for file_name in train_test_set:
        results[file_name] = []
        file_path = './../data/' + file_name
        train_set = DataHandler(file_path)
        test_set = DataHandler('./../data/' + train_test_set[file_name])
        for i in range(0, 151):
            w = p.linear_regression(train_set.x, train_set.y, i)
            training_set_mse = p.mean_square_error(train_set.x, train_set.y, w)
            test_set_mse = p.mean_square_error(test_set.x, test_set.y, w)
            results[file_name].append([w, training_set_mse, test_set_mse])
            print file_name, ',', i, ',', training_set_mse, ',', test_set_mse

    #print results
    
if __name__ == '__main__':
    main()
