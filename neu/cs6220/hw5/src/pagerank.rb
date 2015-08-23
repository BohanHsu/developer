# adjacency matrix
# [
#     1 2 3 4 5 6
# 1  [0,1,0,0,1,0]
# 2  [0,1,0,1,0,1]
# 3  [0,1,0,0,0,0]
# 4  [1,0,1,0,0,0]
# 5  [1,0,0,1,0,0]
# 6  [0,1,1,0,0,0]
# ]

#column stochastic matrix

def adjacency_to_stochastic(adjacency_matrix)
  stochastic_matrix_t = Array.new adjacency_matrix
  stochastic_matrix_t.map! do |row|

    cnt = (row.select { |i| i != 0}).length.to_f.to_r
    row.map! do |element|
      element.to_f.to_r / cnt
    end
    row
  end
  stochastic_matrix = [].tap do |sm|
    adjacency_matrix[0].length.times do |i|
      sm << [].tap do |row|
        adjacency_matrix.length.times do |j|
          row << stochastic_matrix_t[j][i]
        end
      end
    end
  end
  #p stochastic_matrix_t
  #p stochastic_matrix

  # stochastic_matrix_t.each do |r|
  #   p r
  # end
  # p ""
  
  stochastic_matrix.each do |r|
    p r
  end

  #stochastic_matrix.each do |r|
  #  row = r.map do |element|
  #    element.to_f == 0 ? 0 : element.to_i == element.to_f ? element.to_i : element
  #  end
  #  p row
  #end

  stochastic_matrix
end


@adjacency_matrix = [
  [0,1,0,0,1,0],
  [0,1,0,1,0,1],
  [0,1,0,0,0,0],
  [1,0,1,0,0,0],
  [1,0,0,1,0,0],
  [0,1,1,0,0,0]
]

@stochastic_matrix = adjacency_to_stochastic @adjacency_matrix

p "=" * 20

def page_rank(stochastic_matrix, p, threshold)
  len = stochastic_matrix[0].length
  vector = [].tap do |v|
    len.times do
      v << 1.to_r / len.to_r
    end
  end

  #p vector

  b_matrix = [].tap do |mat|
    #mat << [].
    stochastic_matrix.length.times do
      mat << [].tap do |row|
        stochastic_matrix[0].length.times do
          row << 1
        end
      end
    end
  end

  #p b_matrix
  #p num_mult_matrix(p, b_matrix)
  #p num_mult_matrix((1 - p), stochastic_matrix)

  p vector

  m1 = num_mult_matrix((1 - p), stochastic_matrix)
  m2 = num_mult_matrix(p, b_matrix)

  m = mat_add_mat(m1, m2)
  p m

  10.times do
    new_vector = []
    vector.each_with_index do |elem, i|
      new_vector << vec_mult_vec(m[i], vector)
    end
    
    vec_f = new_vector.map do |elem|
      elem.to_f
    end
    p vec_f
    vector = new_vector
  end
end

def update_vector(m, v)
  nv = matrix_mult_vector(m, v)
end

def num_mult_matrix(num, matrix)
  new_m = Array.new matrix
  new_m.map! do |row|
    row.map do |elem|
      elem * num
    end
  end
end

def matrix_mult_vector(matrix, vector)
  matrix.map do |row|
    vec_mult_vec row, vector
  end
end

def vec_mult_vec(v1, v2)
  sum = 0
  v1.each_with_index do |elem, i|
    sum += v2[i] * elem
  end
  sum
end

def mat_add_mat(m1, m2)
  new_m = Array.new(m1)
  m1.each_with_index do |row, i|
    new_m[i] = vec_add_vec(row, m2[i])
  end
  new_m
end

def vec_add_vec(v1, v2)
  new_v = Array.new v1
  v1.each_with_index do |elem, i|
    new_v[i] = elem + v2[i]
  end
  new_v
end

def compare_vec(v1, v2, threshold)
  v1 = Array.new v1
  v2 = Array.new v2
  v1.map do |elem|
    elem.to_f
  end
  v2.map do |elem|
    elem.to_f
  end
  v1.each_with_index do |elem, i|
    return false if (elem - v2[i]).abs > i
  end
  true
end

page_rank @stochastic_matrix, 3.to_r/20.to_r, 0.001
