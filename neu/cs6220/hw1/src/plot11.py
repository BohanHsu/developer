import plotly.plotly as py
from plotly.graph_objs import *

f = open('./../results/problem1.txt')
lines = f.readlines()

ls1 = [s[:-1].split(',') for s in lines]

training_set_name = 'train-1000-100.csv'

lambdas = []
train_mses = []
test_mses = []

for line in ls1:
    if line[0].strip() == training_set_name:
        lambdas.append(int(line[1]))
        train_mses.append(float(line[2]))
        test_mses.append(float(line[3]))

print len(lambdas)
print len(train_mses)
print len(test_mses)

trace1 = Scatter(
        x = lambdas,
        y = train_mses,
        name = 'training set mse',
        mode = 'lines+markers'
        )

trace2 = Scatter(
        x = lambdas,
        y = test_mses,
        name = 'test set mse',
        mode = 'lines+markers'
        )

data = Data([trace1, trace2])
plot_url = py.plot(data, filename = training_set_name)
print plot_url
