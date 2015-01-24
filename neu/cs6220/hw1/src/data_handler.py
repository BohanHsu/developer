class DataHandler:
    def __init__(self, file_path, training=True):
        f = open(file_path)
        lines = f.readlines()
        s = lines[0]
        ls = s.split('\r')
        self.row_data = [x.split(',') for x in ls][1:-1]
        self.y = [[float(x[-1])] for x in self.row_data]
        self.x = [[1] + [float(z) for z in x][1:] for x in self.row_data]
