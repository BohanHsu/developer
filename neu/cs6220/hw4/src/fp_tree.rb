class Node
  def initialize()
    @father = nil
    @child = nil
    @value = nil
    @count = 0
  end
end

def frequent_database(database, threshold)
  counts = {}
  database.each do |instance|
    instance.each do |element|
      counts[element] = counts[element].nil? ? 1 : counts[element] + 1
    end
  end
  
  p counts

  database.each do |itemset|
    itemset.select! do |element|
      counts[element] > threshold
    end
    itemset.sort! do |x, y|
      if counts[x] == counts[y]
        (x <=> y)
      else
        - (counts[x] <=> counts[y])
      end
    end
  end

  p database
end

#def tree_construction(database)
#  root = Node.new
#  database.each |itemset| do
#    father = root
#    node = nil
#    itemset.each do |element|
#    end
#  end
#end

def main
  db = [
    [:a,:b,:e],
    [:a,:b,:c,:d],
    [:a,:c,:d],
    [:a,:c,:e],
    [:b,:c,:f],
    [:a],
    [:a,:b,:c],
    [:b,:d,:e],
    [:a,:c],
    [:a,:b,:d,:e]
  ]
  db = frequent_database db, 2
end

if __FILE__ == $0
  main
end
