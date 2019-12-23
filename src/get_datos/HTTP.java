package get_datos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTP {
	private String url;
	private String userAgent;
	private String authorization;
	private String method;
	
	public HTTP(String url) {
		url = this.url;
		userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, likeGecko) Chrome/55.0.2883.87 Safari/537.36";
		authorization = "Basic aW5mb3JtYXR1cHY6dHlBMGJxVWU=";
		method = "GET";
	}
	
	public HTTP(String url, String userAgent, String authorization, String method) {
		 url = this.url;
		 userAgent = this.userAgent;
		 authorization = this.authorization;
		 method = this.method;
	}
	
	public String connect() {
		StringBuffer response = new StringBuffer();
		try {
			URL obj = new URL(url);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod(method);
			con.setRequestProperty("authorization", authorization);
			con.setRequestProperty("user-agent", userAgent);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
						
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();		
			
		}catch(Exception e) {e.getStackTrace();}
		
		return response.toString();
	}
}
