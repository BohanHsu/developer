import math

class ZScore:
    def __init__(self, original_data):
        self.original_data = original_data
        self.normalized_data = []
        self.mues = []
        self.sigmas= []
        self.calc_mues()
        self.calc_sigmas()
        self.normalize_original_data()

    #def calc_normalize_functions(self):

    def calc_mues(self):
        sums = [0 for x in range(0, len(self.original_data[0]))]
        for tuple in self.original_data:
            for i in range(0, len(tuple)):
                sums[i] = sums[i] + tuple[i]
        
        length = len(self.original_data)
        self.mues = [float(x)/length for x in sums]

    def calc_sigmas(self):
        sums = [0 for x in range(0, len(self.mues))]
        for tuple in self.original_data:
            for i in range(0, len(tuple)):
                sums[i] = (tuple[i] - self.mues[i]) ** 2 + sums[i]

        length = len(self.original_data)
        self.sigmas = [math.sqrt(float(x)/length) for x in sums]

    def normalize_original_data(self):
        n = len(self.original_data[0])
        for tuple in self.original_data:
            t = []
            for i in range(0, n):
                t.append(self.normalize_data(tuple[i], i))

            self.normalized_data.append(t)

    def normalize_data(self, data, i):
        return float (data - self.mues[i]) / self.sigmas[i]
