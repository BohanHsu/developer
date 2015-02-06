def function1():
    a = 1
    b = 2
    def function2(c):
        return a + b + c
    
    return function2

f = function1()
print f(3)

