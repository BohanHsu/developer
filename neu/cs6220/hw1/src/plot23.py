import plotly.plotly as py
from plotly.graph_objs import *

f = open('./../results/problem2.txt')
lines = f.readlines()

ls1 = [s[:-1].split(',') for s in lines]

lambdaa = '150'

train_mses = {}
test_mses = {}

for line in ls1:
    #if line[0].strip() == training_set_name:
    #    lambdas.append(int(line[1]))
    #    train_mses.append(float(line[2]))
    #    test_mses.append(float(line[3]))
    size = int(line[0])

    if line[1].strip() == lambdaa:
        if not size in train_mses:
            train_mses[size] = []

        if not size in test_mses:
            test_mses[size] = []

        train_mses[size].append(float(line[2]))
        test_mses[size].append(float(line[3]))

sizes = [k for k in train_mses]
sizes.sort()
train_mses_avg = []
test_mses_avg = []

for size in sizes:
    #sizes.append(size)
    train_mse = reduce(lambda x, y: x+y, train_mses[size]) / 10
    test_mse = reduce(lambda x, y: x+y, test_mses[size]) / 10
    train_mses_avg.append(train_mse)
    test_mses_avg.append(test_mse)

print sizes
print train_mses_avg
print test_mses_avg
#print len(train_mses_avg)
#print len(train_mses_avg)

trace1 = Scatter(
        x = sizes,
        y = train_mses_avg,
        name = 'training set mse',
        mode = 'lines+markers'
        )

trace2 = Scatter(
        x = sizes,
        y = test_mses_avg,
        name = 'test set mse',
        mode = 'lines+markers'
        )

data = Data([trace1, trace2])
plot_url = py.plot(data, filename = "lambda="+lambdaa)
print plot_url
