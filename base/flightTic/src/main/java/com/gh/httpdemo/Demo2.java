package com.gh.httpdemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月26日 下午3:05:34 类说明
 */
public class Demo2 {
	public static final String loginUrl = "http://www.chinaexpressair.com/hxhklandr.ac?reqCode=dologin";

	public static void enter() throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://httpbin.org/get");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response object
            // to allow the response content to be streamed directly from the network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST call CloseableHttpResponse#close() from a finally clause.
            // Please note that if response content is not fully consumed the underlying
            // connection cannot be safely re-used and will be shut down and discarded
            // by the connection manager.
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

            HttpPost httpPost = new HttpPost("http://httpbin.org/post");
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
	} 
	
	public static void login() throws ClientProtocolException, IOException {
		List<NameValuePair> formParams = new ArrayList<>();
		formParams.add(new BasicNameValuePair("username", "123213123"));
		formParams
				.add(new BasicNameValuePair(
						"mmp",
						"83edcaa523880474706d606e106685d77f753ac616973a7da8b6f074fcd3021ec404aced33aa85fdab73b40bc48f513053d90965a12d818d1020f7258dd027a3ce3c861bbb2c24b55a87d694fdaa9aff7d4e42ba05c8d61d9367ec06e58b34b12f95000fc299426f9c79592e7c3049ae92a83b97a14532b897ed53032b3b0c68"));
		formParams.add(new BasicNameValuePair("vcode", "xxxj"));
		Content ct = Request.Post(loginUrl).bodyForm(formParams).execute()
				.returnContent();
		System.out.println(ct.asString());
	}

	public static void main(String[] args) {
		try {
			login();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
