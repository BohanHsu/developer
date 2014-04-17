# simply test is that freeze is associate with all the class
class Test1
  def initialize(i)
    @i = i
  end  
  attr_reader :i
  attr_writer :i
end

t = Test1.new(0)
t.i
t.i = 1
t.i
t.freeze
t.i = 2

# the result shows that freeze is inherted by every class

