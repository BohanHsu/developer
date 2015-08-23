@sequences = [
  [[2], [3], [4]],
  [[2, 5], [3]],
  [[3], [4], [5]],
  [[1],[2],[3]],
  [[1], [2, 5]],
  [[1], [5], [3]],
  [[5], [3, 4]]
]

@new_sequences = []

class Array
  def to_seq
    a = Array.new self 
    a.map! do |elem|
      #"{" + " ".join(elem) + "}"
      "{" + elem.join(" ") + "}"
    end
    #"<" + " ".join(a) +">"
    "< " + a.join(" ") +" >"
  end
end

def can_join?(arg1, arg2)
  s1 = Array.new arg1
  s2 = Array.new arg2
  s1[0] = s1[0][1..-1]
  s2[-1] = s2[-1][0...-1]
  s1.select! do |element|
    !element.empty?
  end
  s2.select! do |element|
    !element.empty?
  end
  puts ""
  puts "#{arg1.to_seq} remove first item is #{s1.to_seq}"
  puts "#{arg2.to_seq} remove last item is #{s2.to_seq}"
  puts "#{arg1.to_seq} and #{arg2.to_seq} could#{s1 == s2 ? "" : " not"} join"
  return s1 == s2
end

def join(arg1, arg2)
  s1 = Array.new arg1
  s2 = Array.new arg2
  s1[0] = s1[0][1..-1]
  s2[-1] = s2[-1][0...-1]

  s1.select! do |element|
    !element.empty?
  end
  s2.select! do |element|
    !element.empty?
  end

  new_arg2 = Array.new arg2
  if arg1[0].length == 1
     new_arg2 = [arg1[0]] + new_arg2
  else
    new_arg2[0] = [arg1[0][0]] + new_arg2[0]
  end
  puts ""
  puts "length-4 frequent #{new_arg2.to_seq}"
  new_arg2
end

def all_subsequences(sequence)
  subsequences = []
  sequence.each_with_index do |element, i|
    element.each_with_index do |element, j|
      sub = Array.new sequence
      sub[i] = sub[i][0...j] + sub[i][(j + 1)..-1]
      sub.select! do |element|
        !element.empty?
      end
      subsequences << sub
    end
  end
  puts "all subsequences of #{sequence.to_seq} are #{subsequences.map { |seq| seq.to_seq }}"
  subsequences
end

#all_subsequences [[1], [2, 5], [3]]


def pruning
  @new_sequences.select! do |new_sequence|
    all_sub_in_last_level = true
    all_subsequences(new_sequence).each do |sub|
      including = @sequences.include? sub
      all_sub_in_last_level &= including
      puts "#{sub.to_seq} #{including ? "in" : "not in"} length-3 frequent pattern set"
    end
    all_sub_in_last_level
  end
end

def main
  @sequences.each do |seq|
    puts seq.to_seq
  end

  @sequences.each do |element1|
    puts "-"  * 30
    @sequences.each do |element2|
      #p "elem1: #{element1}, elem2: #{element2}"
      @new_sequences << join(element1, element2) if can_join?(element1, element2)
    end
  end

  puts ""
  puts "=" * 10 + "length-4 frequent" + "=" * 10
  
  @new_sequences.each do |seq|
    puts seq.to_seq
  end

  puts ""
  puts "=" * 10 + "purning" + "=" * 10
  pruning

  @new_sequences.each do |seq|
    puts seq.to_seq
  end
end

main if __FILE__
