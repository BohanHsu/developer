import numpy as np
import math

v1 = [[1],[0],[1]]
v2 = [[1],[1],[1]]
v3 = [[1],[0],[0]]
v4 = [[1],[1],[0]]

m1 = np.matrix(v1)
m2 = np.matrix(v2)
m3 = np.matrix(v3)
m4 = np.matrix(v4)

y1 = 1
y2 = -1
y3 = 1
y4 = 1

#w = m1 * (-0.8) * y1 + m2 * 1 * y2 + m3 * 6.4 * y3 + m4 * (-1.9) * y4
w = m2 * 1 * y2 + m3 * 6.4 * y3

print 'w', w

#b1 = 1 / y1 - np.matrix.dot(w.getT(), m1)
b2 = 1 / y2 - np.matrix.dot(w.getT(), m2)
b3 = 1 / y3 - np.matrix.dot(w.getT(), m3)
#b4 = 1 / y4 - np.matrix.dot(w.getT(), m4)

#print b1
print b2
print b3
#print b4

b = math.pow(b2, 1)

v = [[1],[0.8],[1]]
m = np.matrix(v)
res = np.matrix.dot(w.getT(), m) + b
print res
