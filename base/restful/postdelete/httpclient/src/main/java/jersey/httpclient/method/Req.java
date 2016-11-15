package jersey.httpclient.method;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

public class Req {
	public static String req(String url) throws IOException {
		return Request.Get(url).execute().returnContent().toString();
	}
	
	public static String delete(String url) throws IOException {
		return Request.Delete(url).execute().returnContent().toString();
	}
	
	public static Response reqResponse(String url) throws IOException {
		return Request.Get(url).execute();
	}
	
	public static String post(String url,Map<String,String> map) throws IOException {
		List<NameValuePair> list = new ArrayList<>();
		for(Map.Entry<String, String> entry :map.entrySet()){
			list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
		}
		Response resp = Request.Post(url).bodyForm(list).execute();
		return resp.returnContent().asString(StandardCharsets.UTF_8);
	}
}
