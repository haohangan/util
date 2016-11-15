package httpclient.method;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hc.client5.http.fluent.Response;
import org.junit.Before;
import org.junit.Test;

import jersey.httpclient.method.Req;

public class ReqTest {
	String url1;
	String url2;
	String url3;
	
	@Before
	public void before(){
		url1 = "http://localhost/app1/ticket";
		url2 = "http://localhost/app1/ticket/1";
		url3 = "http://localhost/app1/ticket/1";
	}
	
	@Test
	public void testGet(){
		try {
			String result = Req.req(url1);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet2(){
		try {
			Response result = Req.reqResponse(url2);
			System.out.println(result.returnContent().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete(){
		try {
			String result = Req.delete(url3);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPost() throws IOException{
		Map<String,String> map = new HashMap<>();
		map.put("id", "1");
		map.put("name", "Bill");
		System.out.println(Req.post(url1, map));
	}
}
