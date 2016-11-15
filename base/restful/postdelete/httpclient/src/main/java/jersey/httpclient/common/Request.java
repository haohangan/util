package jersey.httpclient.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.hc.client5.http.impl.sync.HttpClients;
import org.apache.hc.client5.http.methods.HttpUriRequest;
import org.apache.hc.client5.http.methods.RequestBuilder;
import org.apache.hc.client5.http.sync.HttpClient;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.entity.EntityUtils;

public class Request {

	public static String delete2(String url) throws IOException, ParseException {
		HttpClient client = HttpClients.createDefault();
		final HttpUriRequest request = RequestBuilder.delete(url)
				.addHeader("content-type", "application/x-www-form-urlencoded").build();
		;
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity,StandardCharsets.UTF_8);
	}

	public static void main(String[] args) throws IOException, ParseException {
		System.out.println(delete2("http://localhost/app1/ticket/1"));
	}
}
