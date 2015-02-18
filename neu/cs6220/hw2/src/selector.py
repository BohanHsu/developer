import math

def information_gain(data_set, attribute_names, classes, available_attributes):
    gains = {}
    n = len(data_set)
    infoD = info(classes)
    max_gain = float('-inf')
    max_attribute_index = None
    for attribute_index in available_attributes:
        attrs = []
        for data in data_set:
            attrs.append(data[attribute_index])

        infoDA = infoA(attrs, classes)
        gainA = infoD - infoDA
        gains[attribute_names[attribute_index]] = gainA
        if gainA > max_gain:
            max_gain = gainA
            max_attribute_index = attribute_index

    return (max_attribute_index, gains)

def infoA(attr, cls):
    nD = len(cls)
    sum = 0
    for attr_value in set(attr):
        cls_for_value = []
        count = 0
        for i in range(0, nD):
            if attr[i] == attr_value:
                cls_for_value.append(cls[i])
                count +=  1

        info_value = info(cls_for_value)
        sum += float(count) / nD * info_value

    return sum

def info(cls):
    nD = len(cls)
    cls_count = {}
    count = 0
    for c in cls:
        count = count + 1
        if not c in cls_count:
            cls_count[c] = 0

        cls_count[c] = cls_count[c] + 1

    sum = 0
    for k in cls_count:
        pi = cls_count[k] / float(nD)
        sum -= pi * math.log(pi, 2)
        
    return sum
