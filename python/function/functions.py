
#define a simple function 
def fib(n):
	#"""Print a Fibonacci series up to n."""
    a,b = 0,1
    while a < n:
        print(a, end=' ')
        a,b = b, a + b
    print()
    
fib(2000)

#function itself is a object?
f=fib
f(2000)
print(fib)


#define a function with return value
def fib2(n): # return Fibonacci series up to n
    """Return a list contaiing the Fibonacci seies up to n."""
    result = []
    a,b = 0,1
    while a< n:
        result.append(a)
        a,b = b, a + b
    return result
    
f100 = fib2(100)
print(f100)

#default value arguments
def ask_ok(prompt, retries=4,complaint='Yes or no, please!'):
	while True:
		ok = input(prompt)
		if ok in ('y', 'ye', 'yes'):
			return True
		if ok in ('n','no'):
			return False
		retries = retries - 1
		if retries < 0:
			raise IOError("refusenik user")
		print(complaint)
		
#try to call the ask_ok function 
#ask_ok('Do you really want to quit?')
ask_ok('Do you really want to quit?','come on')
