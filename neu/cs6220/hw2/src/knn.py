class KNN:

    def __init__(self, training_set,  normaliztion_func=None):
        self.training_set = training_set
        self.normaliztion_func = normaliztion_func
        if not self.normaliztion_func is None:
            self.normalize_train_set()

    def normalize_train_set(self):
        pass

    def normalize_test_tuple(self, tuple):
        pass

class Nor
