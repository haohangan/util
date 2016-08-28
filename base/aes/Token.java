package com.grgbanking.auth;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import com.google.gson.Gson;

/**
 * 
 * token工具类，验证用户访问的合法性<br>
 * guihao <br>
 * ghao3@grgbanking.com<br>
 */
class Token {
	private static final long EXPIRE_MILLS = 24 * 60 * 60 * 1000;
	private static final Charset charset = StandardCharsets.UTF_8;
	private static String DEFAULT_SPLIT;

	/** 以下为调用者设置的属性 */

	private String id;// id,自定义的标识符
	private long expire;// 过期时间
	private String sign;// 签名

	static {
		DEFAULT_SPLIT = AES.DEFAULT_STATIC_SPLIT;
	}

	public Token(String id) {
		super();
		this.id = id;
		this.expire = System.currentTimeMillis() + EXPIRE_MILLS;
		this.sign = MD5(id, expire);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * 验证token是否合法
	 * 
	 * @return
	 */
	public boolean valid() {
		String md5 = MD5(id, expire);
		if (sign.equals(md5)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 对id和过期时间进行消息摘要
	 * 
	 * @param _id
	 * @param _expire
	 * @return
	 */
	private static String MD5(String _id, long _expire) {
		try {
			return new String(Hex.encodeHex(
					MD5encrypt(new StringBuilder().append(_id).append(DEFAULT_SPLIT).append(_expire).toString())));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	/**
	 * md5摘要算法
	 * 
	 * @param info
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private static byte[] MD5encrypt(String info) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// 根据MD5算法生成MessageDigest对象
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcBytes = info.getBytes(charset);
		// 使用srcBytes更新摘要
		md5.update(srcBytes);
		// 完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	public String toTokenJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
