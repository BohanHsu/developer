@a = {
  :start => {:u1 => 0.8, :u2 => 0.2},
  :u1 => {:u1 => 0.9, :u2 => 0.1},
  :u2 => {:u1 => 0.1, :u2 => 0.9}
}

@e = {
  :u1 => {:g => (1.0/3), :r => (1.0/3),:b => (1.0/3)},
  :u2 => {:g => (1.0/2), :r => (1.0/4),:b => (1.0/4)}
}

def decoding(sequence)
  states = [:u1, :u2]

  matrix = [].tap do |array|
    (states.length + 1).times do
      array << [].tap do |a|
        (sequence.length + 1).times do
          a << 0
        end
      end
    end
  end

  matrix[0][0] = 1

  ptr = [].tap do |array|
    states.length.times do
      array << [].tap do |a|
        sequence.length.times do
          a << nil
        end
      end
    end
  end

  sequence.each_with_index do |elem, index|
    i = index + 1
    states.each_with_index do |state, jndex|
      j = jndex + 1

      max = 0
      max_ptr = nil

      if i == 1
        (0..states.length).each do |k|
          tmp = matrix[k][i - 1] * @a[:start][state]
          if tmp > max
            max = tmp
            max_ptr = k
          end
        end
      else
        (0...states.length).each do |k|
          tmp = matrix[k + 1][i - 1] * @a[states[k]][state]
          if tmp > max
            max = tmp
            max_ptr = k + 1
          end
        end
      end
      matrix[j][i] = max * @e[state][elem]
      ptr[jndex][index] = max_ptr
    end
  end
  #p matrix
  #p ptr
  matrix.each do |row|
    nr = row.map do |elem|
      elem.round(4)
    end
    p nr
  end

  puts ""

  ptr.each do |row|
    p row
  end
end

decoding [:g, :g, :g]
