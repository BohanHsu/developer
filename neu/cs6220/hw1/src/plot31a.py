import plotly.plotly as py
from plotly.graph_objs import *

f = open('./../results/problem3.txt')
f1 = open('./../results/problem1.txt')
lines = f.readlines()
lines1 = f1.readlines()

ls1 = [s[:-1].split(',') for s in lines]
ls11 = [s[:-1].split(',') for s in lines1]

training_set_name = 'train-1000-100.csv'

cv_mses = {}
train_mses = {}
test_mses = {}

for line in ls1:
    if line[0].strip() == training_set_name:
        #lambdas.append(int(line[1]))
        #mses.append(float(line[2]))
        cv_mses[int(line[1])] = float(line[2])

for line in ls11:
    if line[0].strip() == training_set_name:
        train_mses[int(line[1])] = float(line[2])
        test_mses[int(line[1])] = float(line[3])

x = []
y_cv = []
y_train = []
y_test = []

min_cv = float('inf')
min_cv_i = 0
min_train = float('inf')
min_train_i = 0
min_test = float('inf')
min_test_i = 0

for i in range(0, 151):
    x.append(i)
    y_cv.append(cv_mses[i])
    y_train.append(train_mses[i])
    y_test.append(test_mses[i])
    if cv_mses[i] < min_cv:
        min_cv = cv_mses[i]
        min_cv_i = i

    if train_mses[i] < min_train:
        min_train = train_mses[i]
        min_train_i = i

    if test_mses[i] < min_test:
        min_test = test_mses[i]
        min_test_i = i

print 'min cv mse is:', min_cv, '. lambda at this time is:', min_cv_i
print 'min train mse is:', min_train, '. lambda at this time is:', min_train_i
print 'min test mse is:', min_test, '. lambda at this time is:', min_test_i
    
trace1 = Scatter(
        x = x,
        y = y_cv,
        name = 'cross validation',
        mode = 'lines+markers'
        )

trace2 = Scatter(
        x = x,
        y = y_train,
        name = 'training set',
        mode = 'lines+markers'
        )

trace3 = Scatter(
        x = x,
        y = y_test,
        name = 'test set',
        mode = 'lines+markers'
    )

data = Data([trace1, trace2, trace3])
plot_url = py.plot(data, filename = training_set_name +
'_cross_validation_compare')
print plot_url
