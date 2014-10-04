class Criterion
  def initialize(attribute, is_discrete?, opts = {})
    @attribute = attribute
    @is_discrete? = is_discrete?
    unless is_discrete?
      raise "not split points" if opts[:split_points].nil? || opts[:split_points].empty?
      @split_points = opts[:split_points]
    end
  end
end
