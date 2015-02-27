class FakeRandom:
    count = 0
    sequence = []


    #def __init__(self, sequence):
    #    self.sequence  = sequence

    @staticmethod
    def set_sequence(sequence):
        FakeRandom.sequence = sequence

    @staticmethod
    def random():
        res = FakeRandom.sequence[FakeRandom.count]
        FakeRandom.count += 1
        #print "fake random count:", FakeRandom.count
        return res

    @staticmethod
    def reset_index():
        FakeRandom.count = 0

