class ArffHandler:
    def __init__(self, path, is_training_set=True):
        file = open(path)
        self.lines = file.readlines()
        split = 0
        for i in range(0, len(self.lines)):
            line = self.lines[i]
            if line.startswith("@data"):
                split = i
        
        self.lines = self.lines[split + 1:]
        self.lines = [x.split(',')[:-1] for x in self.lines]
        #self.index = {}
        for i in range(0, len(self.lines)):
            line = self.lines[i] 
            #line = line.split(',')
            #self.index[i] = line[-1][:-1]
            #line = line[:-1]
            #if is_training_set:
            #    for j in range(0, len(line) - 1):
            #        line[j] = float(line[j])

            #else:
            #    for j in range(0, len(line)):
            #        line[j] = float(line[j])

            for j in range(0, len(line)):
                line[j] = float(line[j])

            self.lines[i] = line




