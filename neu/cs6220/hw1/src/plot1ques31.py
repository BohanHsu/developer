import plotly.plotly as py
from plotly.graph_objs import *

f = open('./../results/problem1.txt')
lines = f.readlines()

ls1 = [s[:-1].split(',') for s in lines]

training_set_name1 = 'train-1000-100.csv'
training_set_name2 = '50(1000)_100_train.csv'
training_set_name3 = '100(1000)_100_train.csv'
training_set_name4 = '150(1000)_100_train.csv'

train_mses1 = {}
test_mses1 = {}

train_mses2 = {}
test_mses2 = {}

train_mses3 = {}
test_mses3 = {}

train_mses4 = {}
test_mses4 = {}

for line in ls1:
    if line[0].strip() == training_set_name1:
        train_mses1[int(line[1])] = float(line[2])
        test_mses1[int(line[1])] = float(line[3])

    if line[0].strip() == training_set_name2:
        train_mses2[int(line[1])] = float(line[2])
        test_mses2[int(line[1])] = float(line[3])

    if line[0].strip() == training_set_name3:
        train_mses3[int(line[1])] = float(line[2])
        test_mses3[int(line[1])] = float(line[3])

    if line[0].strip() == training_set_name4:
        train_mses4[int(line[1])] = float(line[2])
        test_mses4[int(line[1])] = float(line[3])


#print len(lambdas)
#print len(train_mses)
#print len(test_mses)
x1 = []
y1tr = []
y1te = []
y2tr = []
y2te = []
y3tr = []
y3te = []
y4tr = []
y4te = []

for i in range(1, 151):
    x1.append(i)
    y1tr.append(train_mses1[i])
    y1te.append(test_mses1[i])
    y2tr.append(train_mses2[i])
    y2te.append(test_mses2[i])
    y3tr.append(train_mses3[i])
    y3te.append(test_mses3[i])
    y4tr.append(train_mses4[i])
    y4te.append(test_mses4[i])

trace1 = Scatter(
        x = x1,
        y = y1te,
        name = training_set_name1,
        mode = 'lines+markers'
        )

trace2 = Scatter(
        x = x1,
        y = y2te,
        name = training_set_name2,
        mode = 'lines+markers'
        )

trace3 = Scatter(
        x = x1,
        y = y3te,
        name = training_set_name3,
        mode = 'lines+markers'
        )

trace4 = Scatter(
        x = x1,
        y = y4te,
        name = training_set_name4,
        mode = 'lines+markers'
        )

data = Data([trace1, trace2, trace3, trace4])
plot_url = py.plot(data, filename = "compare number of example change")
print plot_url
