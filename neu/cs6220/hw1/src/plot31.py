import plotly.plotly as py
from plotly.graph_objs import *

f = open('./../results/problem1.txt')
lines = f.readlines()

ls1 = [s[:-1].split(',') for s in lines]

training_set_name = 'train-1000-100.csv'

lambdas = []
mses = []

for line in ls1:
    if line[0].strip() == training_set_name:
        lambdas.append(int(line[1]))
        mses.append(float(line[2]))

print lambdas
print mses

trace1 = Scatter(
        x = lambdas,
        y = mses,
        name = 'mses',
        mode = 'lines+markers'
        )

data = Data([trace1])
plot_url = py.plot(data, filename = training_set_name + '_cross_validation')
print plot_url
