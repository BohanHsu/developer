package http_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class HttpTest {
	LinkedList<String> cookies = new LinkedList<String>();
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
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		
		return content;
	}
}
