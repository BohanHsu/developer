# Request URL:http://lao.com.md.gz.cn/qozr203/xianzhiconn.asp
# u:6786
# p:6786789
# 48 -57
# 65 - 90
# 97 - 122
require "net/http"
require "uri"

class Array
  def sample
    length = self.length
    index = rand(length)
    self[index]
  end
end

class Post
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
    forever(1) do
      id = generate_id
      pass = generate_password
      response = post_data(@url,{
        :u => id,
        :p => pass 
      })
      puts id
      puts pass
      puts response.code
    end
  end
end

opts = {:u => '12345678', :p => 'pzsb'}
url = 'http://lao.com.md.gz.cn/qozr203/xianzhiconn.asp'

#@response = post_data(url, opts)
#p @response

@post = Post.new url
@post.come_on

