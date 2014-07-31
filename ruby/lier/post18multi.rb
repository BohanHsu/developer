# Request URL:http://lao.com.md.gz.cn/qozr203/xianzhiconn.asp
# u:6786
# p:6786789
# 48 -57
# 65 - 90
# 97 - 122
require "net/http"
require "uri"

@@count = 0
@@start_time = Time.now

class Array
  def sample
    length = self.length
    index = rand(length)
    self[index]
  end
end

class Post
  @@lock = Mutex.new

  def initialize(url) 
    @url = url
    @numbers = (48..57).to_a
    @characs = (48..57).to_a + (65..90).to_a + (97..122).to_a
  end

  def post_data(url,opts)
    uri = URI.parse(url)
    http = Net::HTTP.new(uri.host, uri.port)
    request = Net::HTTP::Post.new(uri.request_uri)
    request.set_form_data(opts)
    response = http.request(request)
  end

  def forever(sleep_time = 0)
    while true
      yield
      sleep sleep_time
    end
  end

  def generate_random_str(range,char_set)
    #length = rand(range)
    length = range.to_a.sample
    str = ""
    length.times do
      str += char_set.sample.chr.to_s
    end
    str
  end

  def generate_password
    generate_random_str(8..14, @characs)
  end

  def generate_id
    generate_random_str(8..10, @numbers)
  end

  def come_on
    t2 = Time.now
    c = 0
    forever(1) do
      id = generate_id
      pass = generate_password
      response = post_data(@url,{
        :u => id,
        :p => pass 
      })
      #puts '=' * 20
      #puts id
      #puts pass
      puts "response code: #{response.code}"
      #t1 = Time.now
      #puts "time: #{t1}"
      #puts "time usage: #{t1 - t2 unless t2.nil?}"
      #t2 = t1
      #c = c + 1
      #puts "count: #{c}"
      #puts Thread.current
      update_count
      puts "total #{@@count}"
      puts "average time usage: #{(Time.new-@@start_time)/@@count}"
    end
  end

  def update_count
    @@lock.synchronize do
      @@count = @@count + 1
    end
  end
end

url = 'http://lao.com.md.gz.cn/qozr203/xianzhiconn.asp'
threads = []

30.times do
  t = Thread.new{
    @post = Post.new url
    @post.come_on
  }
  threads << t
end

threads.each do |t|
  t.join
  puts t
end

while true
  puts "average time usage: #{(Time.new-@@start_time)/@@count}"
  sleep 3
end
