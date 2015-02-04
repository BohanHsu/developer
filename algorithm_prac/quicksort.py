import random

class Qs:
    def __init__(self, array):
        self.array = array

    def quicksort(self, lo, hi):
        if lo < hi:
            p = self.partition(lo, hi)
            self.quicksort(lo, p - 1)
            self.quicksort(p + 1, hi)

    def partition(self, lo, hi):
        pivot_index = random.randint(lo, hi)
        pivot_value = self.array[pivot_index]
        self.array[pivot_index] = self.array[hi]
        self.array[hi] = pivot_value

        j = lo
        for i in range(lo, hi):
            if self.array[i] < pivot_value:
                tmp = self.array[i]
                self.array[i] = self.array[j]
                self.array[j] = tmp
                j = j + 1

        self.array[hi] = self.array[j]
        self.array[j] = pivot_value
        return j


    def sort(self):
        self.quicksort(0, len(self.array) - 1)
        return self.array
