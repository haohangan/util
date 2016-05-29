package com.eva.model.user.jwtutils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JWTCreateUtil {
	static final Key key = MacProvider.generateKey();
	static final long expireMills = 24 * 60 * 60 * 1000;// 失效时间为一天的毫秒数
	static final String appName = "boot";// 发行者

	/**
	 * 获取token
	 * 
	 * @param id
	 * @return
	 */
	public static String create(String id,Set<String> role) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", id);// 设置用户唯一标识符号
		map.put("role", role);
		Date expireDate = new Date(System.currentTimeMillis() + expireMills);// 设置过期日期
		JwtBuilder builder = Jwts.builder();
		builder.setClaims(map);// 要先设置map，否则无效
		builder.setIssuer(appName);//app的名称
		builder.setExpiration(expireDate);// 设置过期时间
		return builder.signWith(SignatureAlgorithm.HS512, key).compact();
	}
	
	public static String create(String id,String role) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", id);// 设置用户唯一标识符号
		map.put("role", role);
		Date expireDate = new Date(System.currentTimeMillis() + expireMills);// 设置过期日期
		JwtBuilder builder = Jwts.builder();
		builder.setClaims(map);// 要先设置map，否则无效
		builder.setIssuer(appName);//app的名称
		builder.setExpiration(expireDate);// 设置过期时间
		return builder.signWith(SignatureAlgorithm.HS512, key).compact();
	}

	/**
	 * 解析token
	 * 
	 * @param token
	 * @return
	 * @throws MalformedJwtException
	 */
	public static Claims parse(String token) throws MalformedJwtException {
		Claims c = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return c;
	}

	/**
	 * 判断是否过期
	 * 
	 * @param token
	 * @return
	 */
	public static boolean analyzeExpireDate(String token) {
		Claims c = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		if (c.getExpiration().compareTo(new Date()) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否过期
	 * 
	 * @param c
	 * @return
	 */
	public static boolean analyzeExpireDate(Claims c) {
		if (c.getExpiration().compareTo(new Date()) >= 0) {
			return true;
		}
		return false;
	}

//	public static void main(String[] args) {
//		String phone = "13562514123";
//		// long a = System.currentTimeMillis();
//		// System.out.println(new Date(System.currentTimeMillis() +
//		// expireMills));
//		Set<String> rset = new HashSet<String>();
//		rset.add("asdasd");
//		String token = create(phone,rset);
//		Claims c = parse(token);
//		System.out.println(c.get("role"));
//		System.out.println(analyzeExpireDate(token));
//		Set<Map.Entry<String, Object>> set = c.entrySet();
//		for (Map.Entry<String, Object> entry : set) {
//			System.out.println(entry.getKey() + "\t:\t" + entry.getValue());
//		}
//	}
}
