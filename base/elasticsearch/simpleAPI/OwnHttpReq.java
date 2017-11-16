package es;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;

public class OwnHttpReq {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		query();
	}

	private static void index() throws ClientProtocolException, IOException {
		Twitter twitter = Twitter.builder().user("guihao")
				.post_date(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1)).message("this is fouth").build();
		String result = Request.Put("http://localhost:9200/twitter/doc/4?pretty")
				.addHeader("Content-Type", "application/json").bodyByteArray(JsonUtils.toJson(twitter).getBytes())
				.execute().returnContent().asString();
		System.out.println(result);
	}

	private static void search() throws ClientProtocolException, IOException {
		Content c = Request.Get("http://localhost:9200/twitter/_search").execute().returnContent();
		String result = c.asString();
		Gson g = new Gson();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> m = g.fromJson(result, HashMap.class);
		m.forEach((k, v) -> System.out.println(k + "\t" + v));
	}

	private static void query() throws ClientProtocolException, IOException {
//		String query = "{\"query\" : {\"match_all\" : {}}}";
		String query = "{\"query\" : { \"match\" : {\"user\": \"kimchy\" }}}";
		String result = Request.Post("http://localhost:9200/twitter/_search?pretty=true")
				.addHeader("Content-Type", "application/json").bodyByteArray(query.getBytes()).execute().returnContent()
				.asString();
		System.out.println(result);
	}
}
