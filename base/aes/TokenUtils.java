package com.grgbanking.auth;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang.time.StopWatch;

import com.google.gson.Gson;

/**
 * 
 * token操作的工具类<br>
 * guihao <br>
 * ghao3@grgbanking.com<br>
 */
public class TokenUtils {
	
	public static final String TOKEN_STR = "Token";//定义key
	
	/**
	 * 生成token字符串
	 * @param id
	 * @return
	 */
	public static String createToken(String id) {
		Token token = new Token(id);
		String json = token.toTokenJson();
		return  AES.encrypt2(json);
	}

	/**
	 * 验证token字符串
	 * @param tokenJson
	 * @return
	 */
	public static Boolean validToken(String etoken) {
		String tokenJson;
		try {
			tokenJson = AES.decrypt2(etoken);
		} catch (DecoderException e) {
			return Boolean.FALSE;
		}
		Gson gson = new Gson();
		Token token = gson.fromJson(tokenJson, Token.class);
		if (token.valid()) {
			if (Long.compare(token.getExpire(), System.currentTimeMillis()) > 0) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	
	public static void main(String[] args) {
		StopWatch sw = new StopWatch();
		sw.start();
		String token =  createToken("123");
		sw.stop();
		System.out.println(sw.getTime());
		System.out.println(token);
		System.out.println(token.length());
		sw.reset();
		sw.start();
		boolean flag = validToken(token);
		sw.stop();
		System.out.println(sw.getTime());
		System.out.println(flag);
		
	}
}
