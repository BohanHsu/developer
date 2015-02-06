class MinMax:
    def __init__(self, original_data):
        self.original_data = original_data
        self.normalized_data = []
        self.normalized_functions = []
        self.mins = []
        self.maxs = []
        self.calc_normalize_functions()
        self.normalize_original_data()

    def calc_normalize_functions(self):
        n = len(self.original_data[0])
        self.mins = [float('inf') for i in range(0, n)]
        self.maxs = [float('-inf') for i in range(0, n)]

        for tuple in self.original_data:
            for i in range(0, n):
                if tuple[i] < self.mins[i]:
                    self.mins[i] = tuple[i]

                if tuple[i] > self.maxs[i]:
                    self.maxs[i] = tuple[i]

    def normalize_original_data(self):
        n = len(self.original_data[0])
        for tuple in self.original_data:
            t = []
            for i in range(0, n):
                t.append(self.normalize_data(tuple[i], i))

            self.normalized_data.append(t)

    def normalize_data(self, data, i):
        return float(data - self.mins[i]) / float(self.maxs[i] - self.mins[i])
