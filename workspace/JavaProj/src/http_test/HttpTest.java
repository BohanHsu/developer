package http_test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HttpTest {
	List<String> cookies = new LinkedList<String>();
	HttpURLConnection conn = null;
	private final String USER_AGENT = "Mozilla/5.0";

	public String getPageContent(String url) throws IOException {
		String content = null;
		URL urlObj = new URL(url);
		conn = (HttpURLConnection) urlObj.openConnection();
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");

		// add cookies

		int responseCode = conn.getResponseCode();
		String inputLine = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuffer sb = new StringBuffer();

		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine);
		}
		br.close();
		setCookies(conn.getHeaderFields().get("Set-Cookie"));
		content = sb.toString();
		return content;
	}

	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}
	
	public String getUuid(String html){
		String key = "document.cplogin.uuid.value=";
		int index = html.indexOf(key);
		System.out.println(index);
		String uuid = html.substring(index+1+key.length(),index+37+key.length());
		return uuid;
	}
	
	/*
	cplogin.user.value=document.userid.user.value;
    document.cplogin.uuid.value="4a3be495-9baf-43d2-aee2-3197f3b5b43e";
    action = "https://myneu.neu.edu/cp/home/login"
    user:
    uuid:
    pass:
	*/
	
	public String getPostForm(String uuid, String username, String password) throws UnsupportedEncodingException{
		HashMap<String, String> form = new HashMap<String,String>();
		LinkedList<String> param = new LinkedList<String>();
		form.put("user", username);
		form.put("pass", password);
		form.put("uuid", uuid);
		
		for (String key : form.keySet()) {
			param.add(key+"="+URLEncoder.encode(form.get(key), "UTF-8"));
		}
		
		StringBuilder sb = new StringBuilder();
		for (String string : param) {
			if (sb.length() == 0){
				// this is the first argument
				sb.append(string);
			}else{
				sb.append("&" + string);
			}
		} 
		return sb.toString();
	}
	
	
	public void sendPost(String url, String postForm) throws Exception{
		URL urlObj = new URL(url);
		conn = (HttpURLConnection) urlObj.openConnection();
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		conn.setRequestProperty("Cache-Control","max-age=0");
		conn.setRequestProperty("Connection","keep-alive");
		conn.setRequestProperty("Content-Length", Integer.toString(postForm.length()));
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conn.setRequestProperty("Host","myneu.neu.edu");
		conn.setRequestProperty("Origin","http://myneu.neu.edu");
		conn.setRequestProperty("Referer","http://myneu.neu.edu/cp/home/displaylogin");
		conn.setRequestProperty("User-Agent", USER_AGENT);
		for (String cookie : this.cookies) {
			conn.addRequestProperty("Cookie", cookie);
		}
		conn.setDoOutput(true);
		conn.setDoInput(true);
		
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		dos.writeBytes(postForm);
		dos.flush();
		dos.close();
		int responseCode = conn.getResponseCode();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine = null;
		StringBuilder sb = new StringBuilder();
		while((inputLine = br.readLine()) != null){
			sb.append(inputLine);
		}
		br.close();
		System.out.println(br.toString());
	}
	
	// test 
	public static void main(String[] args) throws IOException {
		HttpTest ht = new HttpTest();
		String url = "http://myneu.neu.edu/cp/home/displaylogin";
		String returnPage = ht.getPageContent(url);
		System.out.println(returnPage);
		
		for (String string : ht.cookies) {
			System.out.println(string);
		}
		
		String uuid = ht.getUuid(returnPage);
		System.out.println(uuid);
		
		String postForm = ht.getPostForm(uuid, "xu.bo", "xbh");
		System.out.println(postForm);
		
		String verifySuccessUrl = "http://myneu.neu.edu/render.userLayoutRootNode.uP?uP_root=root";
		returnPage = ht.getPageContent(verifySuccessUrl);
		System.out.println(returnPage);
	}
}
