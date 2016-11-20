package curator_y.common_configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.sync.ResponseHandler;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;

/**
 * 初始化测试用
 * @author 97617
 *
 */
public class InitTest {
    Set<String> urlName;
    
    @Before
    public void before(){
    	urlName = new HashSet<>();
    	urlName.add("add");
    	urlName.add("delete");
    	urlName.add("update");
    	urlName.add("query");
    	urlName.add("names");
    	urlName.add("append");
    	urlName.add("increase");
    	urlName.add("encrypt");
    	urlName.add("decrypt");
    	urlName.add("realize");
    	urlName.add("conscious");
    	urlName.add("head");
    }
    
    
    public void insert(String name,String appName) throws IOException{
    	List<NameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("name", "boot_"+name));
		list.add(new BasicNameValuePair("url", "http://127.0.0.1/"+name));
		list.add(new BasicNameValuePair("appName", appName));
		
		Request.Post(ConfigTest.BASIC_URL+"config").bodyForm(list).execute().handleResponse(new ResponseHandler<HttpResponse>() {

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
    
    
    @Test
    public void init() throws IOException{
    	for(String name:urlName){
    		insert(name,ConfigTest.appName);
    	}
    }
}
