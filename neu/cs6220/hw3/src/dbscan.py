import random

class DBScan:
    def __init__(self, eps, min_pts, training_set, distance_measure):
        self.eps = eps
        self.min_pts =  min_pts
        self.training_set = training_set
        self.distance_measure = distance_measure
        self.n_eps_p = {}
        self.core_points = set([])
        self.clusters = []
        self._calc_n_eps_p()
        self._find_core_points()
        self._clusting()

    def _calc_n_eps_p(self):
        for p in self.training_set:
            eps_p = []
            for q in self.training_set:
                if p != q and self.distance_measure(p, q) <= self.eps:
                    eps_p.append(q)

            self.n_eps_p[p] = eps_p

    def _find_core_points(self):
        for p in self.n_eps_p:
            d = self.n_eps_p[p]
            if len(d) >= self.min_pts:
                self.core_points.add(p)

    # is q directly density-reachable from p
    def _directly_density_reachable(self, p, q):
        return p in self.core_points and q in self.n_eps_p[p]

    # is q density-reachable from p
    def _density_reachable(self, p, q):
        visited = set([])
        s = []
        s.append(p)

        while s:
            n = s.pop()
            visited.add(n)
            if n == q:
                return True

            for node in self.training_set:
                if node != n and \
                   self._directly_density_reachable(n, node) \
                   and not node in visited:
                    s.append(node)
        
        return False

    def _clusting(self):
        edges = []
        unscaned_pts = list(self.training_set)
        while unscaned_pts:
            point = random.sample(unscaned_pts, 1)[0]
            #print 'point', point
            unscaned_pts.remove(point)

            if point in self.core_points:
                for node in self.training_set:
                    if point != node and self._density_reachable(point, node):
                        if not ((point, node) in edges or
                                (node, point) in edges):
                            edges.append((point, node))

        cluster_h = {}

        #visited = set([])
        for edge in edges:
            n1 = edge[0]
            n2 = edge[1]

            if n1 in cluster_h and not n2 in cluster_h:
                cluster_h[n1].append(n2)
                cluster_h[n2] = cluster_h[n1]
            elif n2 in cluster_h and not n1 in cluster_h:
                cluster_h[n2].append(n1)
                cluster_h[n1] = cluster_h[n2]
            elif (not n1 in cluster_h) and (not n2 in cluster_h):
                new_cluster = [n1, n2]
                self.clusters.append(new_cluster)
                cluster_h[n1] = new_cluster
                cluster_h[n2] = new_cluster

            #print 'edge', edge
            #print 'self.clusters', self.clusters
