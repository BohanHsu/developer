@a = [39, 44, 43, 39, 46, 38, 39, 43]
@b = [37, 44, 41, 44, 39, 39, 39, 40]

def l1_form(a,b)
  c = Array.new b
  a.each_with_index do |elem, i|
    c[i] -= elem
  end
  
  c.inject(0) do |sum, elem|
    sum += elem.abs
  end
end

p l1_form @a, @b
#p a
#p b

@stack = []
@matrix = nil
@prev = nil

def set(i, j)
  if !@matrix[i-1][j].nil? && !@matrix[i][j-1].nil? && !@matrix[i-1][j-1].nil?
    origins = [[@matrix[i-1][j], i-1, j], [@matrix[i][j-1], i, j-1], [@matrix[i-1][j-1], i-1, j-1]]
    #@matrix[i][j] = origins.min + (@b[i] - @a[j]).abs
    min = 100000
    min_i = nil
    min_j = nil
    origins.each do |origin|
      if origin[0] < min
        min = origin[0]
        min_i = origin[1]
        min_j = origin[2]
      end
    end
    @matrix[i][j] = min + (@b[i] - @a[j]).abs
    @prev[i][j] = [min_i, min_j]
    return true
  else
    @stack.push [i-1,j] if @matrix[i-1][j].nil?
    @stack.push [i,j-1] if @matrix[i][j-1].nil?
    @stack.push [i-1,j-1] if @matrix[i-1][j-1].nil?
  end
  return false
end

def dtw(a,b)
  matrix = @matrix
  prev = @prev
  @matrix = [].tap do |mat|
    b.length.times do
      mat << [].tap do |row|
        a.length.times do
          row << nil
        end
      end
    end
  end

  @prev = [].tap do |mat|
    b.length.times do
      mat << [].tap do |row|
        a.length.times do
          row << 0
        end
      end
    end
  end

  (0...b.length).each do |i|
    sum = 0
    (0...i+1).each do |j|
      sum += (b[j] - a[0]).abs
    end
    @matrix[i][0] = sum
  end

  (0...a.length).each do |i|
    sum = 0
    (0...i+1).each do |j|
      sum += (a[j] - b[0]).abs
    end
    @matrix[0][i] = sum
  end

  #p @matrix
  
  @stack.push [b.length - 1, a.length - 1]
  
  while !@stack.empty?
    pair = @stack[-1]
    @stack.pop if set(pair[0], pair[1])
  end
  p @matrix[b.length-1][a.length-1]

  #p @matrix
  #p @prev
  p '=' * 10
  #p @matrix.reverse
  #p @prev.reverse

  @matrix.reverse.each do |row|
    p row
  end

  @prev.reverse.each do |row|
    p row
  end

end

dtw @a, @b
