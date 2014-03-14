import httplib, urllib 
params = urllib.urlencode({'spam': 1, 'eggs': 2, 'bacon': 0})
headers = {"Content-type": "application/x-www-form-urlencoded","Accept": "text/plain"}



conn = httplib.HTTPConnection('https://myneu.neu.edu/cp/home/login')
conn.request("POST", "/cgi-bin/query", params, headers)
response = conn.getresponse()
print response.status, response.reason
