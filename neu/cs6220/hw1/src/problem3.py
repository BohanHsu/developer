from cv_linear_regression import CVLinearRegression
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
    fold = 10
    cvlr = CVLinearRegression()
    results = []
    for file_name in train_test_set:
        file_path = './../data/' + file_name
        train_set = DataHandler(file_path)
        x = train_set.x
        y = train_set.y
        for i in range(0, 151):
            mse = cvlr.cv_linear_regression(x, y, i, fold)
            print file_name, ',', i, ',', mse
            results.append([file_name, i, mse])

if __name__ == '__main__':
    main()
