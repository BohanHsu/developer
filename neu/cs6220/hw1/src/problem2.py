from linear_regression import LinearRegression
from data_handler import DataHandler
import random

def main():
    results = []
    step = 10
    times = 10
    training_file_name = 'train-1000-100.csv'
    training_file_path = './../data/' + training_file_name
    test_file_name = 'test-1000-100.csv'
    test_file_path = './../data/' + test_file_name
    training_set = DataHandler(training_file_path)
    test_set = DataHandler(test_file_path)
    test_x = test_set.x
    test_y = test_set.y
    count = len(training_set.x)
    lr = LinearRegression()
    for lambdaa in [1, 25, 150]:
        for t in range(0, times):
            for sub_size in range(1, count, step):
                sampled_indices = sample_indices(count, sub_size)
                tx = sub_set_by_sample(training_set.x, sampled_indices)
                ty = sub_set_by_sample(training_set.y, sampled_indices)
                #print 'training_set.x', training_set.x
                #print sampled_indices
                #print 'tx', tx
                #print 'ty', ty
                w = lr.linear_regression(tx, ty, lambdaa)
                training_mse = lr.mean_square_error(tx, ty, w)
                test_mse = lr.mean_square_error(test_x, test_y, w)
                print sub_size, ',', lambdaa, ',', training_mse, ',', test_mse
                results.append([sub_size, lambdaa, training_mse, test_mse])
                
    return results

def sub_set_by_sample(set, indices):
    result = []
    for i in indices:
        result.append(set[i])

    return result

def sample_indices(total, size):
    return random.sample(range(0, total), size)

if __name__ == '__main__':
    main()
