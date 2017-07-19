package simple.http;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

public class Gaode {

	static final String key = "13a584fea4fc0f3023f6f6f66ffe354c";
	static final String base_url = "http://restapi.amap.com/v3/geocode/geo";

	static final String driver_url = "http://restapi.amap.com/v3/direction/driving";
	
	static final String distance_url = "http://restapi.amap.com/v3/distance";

	static Executor executor = Executor.newInstance();

	public static void main(String[] args) throws ClientProtocolException, IOException {
		distince();
	}
	
	static void distince() throws ClientProtocolException, IOException{
		List<NameValuePair> formParams = new ArrayList<>();
		formParams.add(new BasicNameValuePair("key", key));
		formParams.add(new BasicNameValuePair("origins", "120.211397,30.339878"));
		formParams.add(new BasicNameValuePair("destination", "120.227735,30.269100"));
		formParams.add(new BasicNameValuePair("type", "0"));
		Request req = Request.Post(distance_url).bodyForm(formParams, StandardCharsets.UTF_8);
		System.out.println(executor.execute(req).returnContent().toString());
	}
	
	static void driver() throws ClientProtocolException, IOException{
		List<NameValuePair> formParams = new ArrayList<>();
		formParams.add(new BasicNameValuePair("key", key));
		formParams.add(new BasicNameValuePair("origin", "120.211397,30.339878"));
		formParams.add(new BasicNameValuePair("destination", "120.227735,30.269100"));
		Request req = Request.Post(driver_url).bodyForm(formParams, StandardCharsets.UTF_8);
		System.out.println(executor.execute(req).returnContent().toString());
	}

	static void loc() throws ClientProtocolException, IOException {
		List<NameValuePair> formParams = new ArrayList<>();
		formParams.add(new BasicNameValuePair("key", key));
		formParams.add(new BasicNameValuePair("address", "华丰路1号"));
		formParams.add(new BasicNameValuePair("city", "杭州"));
		Request req = Request.Post(base_url).bodyForm(formParams, StandardCharsets.UTF_8);
		System.out.println(executor.execute(req).returnContent().toString());
	}
}
