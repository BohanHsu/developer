class Ms:
    def __init__(self, array):
        self.array = array

    def sort(self, arr):
        if len(arr) <= 1:
            return arr

        i = (len(arr) + 1) / 2
        ar1 = self.sort(arr[0:i])
        ar2 = self.sort(arr[i:])
        return self.merge(ar1, ar2)

    def merge(self, ar1, ar2):
        len1 = len(ar1)
        len2 = len(ar2)
        i = 0
        j = 0
        na = []
        while i < len1 or j < len2:
            if i == len1:
                na.append(ar2[j])
                j = j + 1
            elif j == len2:
                na.append(ar1[i])
                i = i + 1
            else:
                if ar1[i] < ar2[j]:
                    na.append(ar1[i])
                    i = i + 1
                else:
                    na.append(ar2[j])
                    j = j + 1

        return na

    def merge_sort(self):
        return self.sort(self.array)

