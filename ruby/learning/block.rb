# this ruby script is for learning blocks

def fib(max)
  i1 = 1
  i2 = 1
  while i2 < max
    yield i1
    i1,i2 = i2,i1+i2
  end
end

#fib(100){|x| print x, ' '}
fib(100)
