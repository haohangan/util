package curator_y.common_configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.sync.ResponseHandler;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.Test;

public class ConfigTest {
	
	static String BASIC_URL = "http://192.168.0.114:80/";
	static String appName = "boot";
   
	@Test
	public void getBasic() throws IOException{
		String result = Request.Get(BASIC_URL).execute().returnContent().toString();
		System.out.println(result);
	}
	
	@Test
	public void getByAppName() throws IOException{
		String result = Request.Get(BASIC_URL+"config/aa").execute().returnContent().toString();
		System.out.println(result);
	}
	
	@Test
	public void insert() throws IOException{
		List<NameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("name", "boot_decrease"));
		list.add(new BasicNameValuePair("url", "http://127.0.0.1/decrease"));
		list.add(new BasicNameValuePair("appName", "boot"));
		
		Request.Post(BASIC_URL+"config").bodyForm(list).execute().handleResponse(new ResponseHandler<HttpResponse>() {

			@Override
			public HttpResponse handleResponse(HttpResponse response) throws IOException {
				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				try {
					System.out.println("entity:"+EntityUtils.toString(entity));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				EntityUtils.consumeQuietly(entity);
				return null;
			}
		});
		
	}
}
