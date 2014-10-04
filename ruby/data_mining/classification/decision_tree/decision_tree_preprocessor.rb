class DecisionTreePreprocessor
  def initialize(row_data)
    @row_data = row_data

  end
  
  def self.set_attributes_attribute
    @attribute_attribute = []
    @row_data[0].each_with_index do |attribute, index|
      @attribute_attribute[index] = :numerical
      @row_data[1..-1].each do |tuple|
        unless is_numerical_attribute?(tuple[index])
          @attribute_attribute[index] = :discrete
          break
        end
      end
    end
  end

  def self.is_numerical_attribute?(value)
    value.to_f.to_s == value
  end
end
