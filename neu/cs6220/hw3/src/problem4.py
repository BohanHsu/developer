from dbscan import DBScan
import math

training_set = [
        (0, 0), 
        (1, 2), 
        (1, 6), 
        (2, 3), 
        (3, 4), 
        (5, 1), 
        (4, 2), 
        (5, 3), 
        (6, 2),
        (7, 4)
        ]
eps = math.sqrt(2)
min_pts = 3

def euclidean_distance(a, b):
    return math.sqrt(math.pow((a[0] - b[0]), 2) + math.pow((a[1] - b[1]), 2))

dbs = DBScan(eps, min_pts, training_set, euclidean_distance)

#print dbs.n_eps_p
print dbs.core_points
print dbs.clusters
