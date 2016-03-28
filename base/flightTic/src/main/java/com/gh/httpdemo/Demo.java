package com.gh.httpdemo;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月24日 下午4:55:17 类说明
 */
public class Demo {
	static String url = "";

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		Content content = Request.Get("http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.5.1").execute().returnContent();
        System.out.println(content.asString());
		//		Request.Post("http://targethost/login")
//				.bodyForm(
//						Form.form().add("username", "vip")
//								.add("password", "secret").build()).execute()
//				.returnContent();
	}
}
