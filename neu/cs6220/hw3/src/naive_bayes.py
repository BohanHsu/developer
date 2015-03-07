class NaiveBayes:
    def __init__(self, training_set):
        self.training_data = [x[0:-1] for x in training_set]
        self.classes = [x[-1] for x in training_set]
        self.pcs = {}
        self.cxcis = None
        self.attr_count = None
        self.class_count = None
        self._calc_pcs()
        self._calc_cxcis()

    def _calc_pcs(self):
        c_countes = {}
        for cls in self.classes:
            if not cls in c_countes:
                c_countes[cls] = 0

            c_countes[cls] = c_countes[cls] + 1

        length = len(self.classes)
        for k in c_countes:
            self.pcs[k] = float(c_countes[k]) / length

    def _calc_cxcis(self):
        pxc_counts = {}
        for cls in self.classes:
            if not cls in pxc_counts:
                pxc_counts[cls] = [{} for x in self.training_data[0]]

        self.class_count = {}
        self.attr_count = [set([]) for x in self.training_data[0]]

        for i in range(0, len(self.classes)):
            cls = self.classes[i]
            if not cls in self.class_count:
                self.class_count[cls] = 0

            self.class_count[cls] += 1

            tuple = self.training_data[i]
            for j in range(0, len(tuple)):
                inst_val = tuple[j]
                self.attr_count[j].add(inst_val)
                if not inst_val in pxc_counts[cls][j]:
                    pxc_counts[cls][j][inst_val] = 0

                pxc_counts[cls][j][inst_val] = pxc_counts[cls][j][inst_val]+ 1

        self.cxcis = pxc_counts

    def _calc_probability(self, cls, index, value):
        length = self.class_count[cls]
        count = 1
        smoothing_length = len(self.attr_count[index])
        if value in self.cxcis[cls][index]:
            count += self.cxcis[cls][index][value]
        else:
            smoothing_length += 1

        print "P(" + value + " | " + cls + ") = " + str(count - 1) + " + 1 / " + str(length) + " + " + str(smoothing_length)

        return float(count) / (length + smoothing_length)


    # calculate probability on the fly
    def classify(self, data):
        p_of_c = {}
        for cls in self.cxcis:
            pxc = float(1)
            for i in range(0, len(data)):
                inst_val = data[i]
                prob = self._calc_probability(cls, i, inst_val)
                pxc = prob * pxc

            p_of_c[cls] = pxc * self.pcs[cls]

        max = float('-inf')
        max_cls = None
        for cls in p_of_c:
            v = p_of_c[cls]
            print "P(" + cls + " | X) = " + str(v)
            if v > max:
                max = v
                max_cls = cls

        return max_cls
