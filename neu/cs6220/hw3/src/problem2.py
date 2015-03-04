import numpy as np

l1 = [[-3.125],[0]]
m1 = np.matrix (l1)



l2 = [[1.875],[0]]
m2 = np.matrix (l2)

print 3 * np.matrix.dot (m1, m1.getT()) + 5 * np.matrix.dot (m2, m2.getT())

