package com.grgbanking.auth;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

class AES {
	// AES对称加密的密码
	public static final String DEFAULT_STATIC_SPLIT = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJuZIUkdlHkrUCrdY5WTJT02xTRozHd5vASKGKKkOBWET9vx1N2IYyujdZT/ZdyFxoeDi4pDSHiwP0yrP9Bvo879Vcl36ZxYjgB+mIW5//+kZdf4WEhk1qXW2yAfXYi+UGWepApG4fbIkS9YwmGLiC4S4ItRu+cbrbbfhnnE8fh1AgMBAAECgYAx2Uk0p2lduGailPLGhLG1fsqHE9wNms6Flch2sq3VFuKO0Fcl8f/zhxaweXIkyCbmW1plHVEmeTCOW37CREHyFpPqkScOvaMXwl94/Jgu1Nk+zWiH/lFgvk8EX1jJGJ1bUZoqzw6tJ8Z2MGdiwo5E+LVcCvhq8rpXS81OBwrPAQJBAP/vsXDm+6+xOPrTqP0WzD8gIyP8ae4oVpN2z0qjSPoOy8OjYTVsN3ZcmGaxONde9JnhMHEwm3J1vOt2aBrpt/ECQQCbows8fhGa8LWyA3GJvFaYh6Y27v6eKzleaf1B0Ig5Onsw61endkf/M5cbEHyEjDxc0/z9EdT2W+VlAnCFGizFAkEA3LATolYaSqoXhvmCKxJAdmJAyuP5d7nixW9aWXKpF64wd/bmeIli3bYEV/CTIesoIZQzEDcooy7xA1xrcmF8EQJAXxWaXMfMq7NZ3jraV8LXyPI+6xrSNgHEV9k7H8VuJECFWNq/P5E05UY4kBFfcX9XtYLR8i69xBFasX5vDfJy3QJBAIsctFpK9+2xEDhZoLqlienKGpCSKOeQWaTRIldjKlvLnOwdUqI7dWxRmFyiL6/PhaHqdSCvjdAjCUTfYMTDLkk=";
	public static byte[] password;
	static {
		password = DEFAULT_STATIC_SPLIT.getBytes(StandardCharsets.UTF_8);
	}

	/**
	 * 加密
	 * 
	 * @param content
	 * @return
	 */
	private static byte[] encrypt(String content) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, secureRandom());
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static SecureRandom secureRandom() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
//			 new SecureRandom(password)
		 secureRandom.setSeed(password);
		return secureRandom;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	private static byte[] decrypt(byte[] content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, secureRandom());
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
//		} catch (NoSuchAlgorithmException e) {
////			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
////			e.printStackTrace();
//		} catch (InvalidKeyException e) {
////			e.printStackTrace();
//		} catch (IllegalBlockSizeException e) {
////			e.printStackTrace();
//		} catch (BadPaddingException e) {
////			e.printStackTrace();
//		}
//		return null;
	}
	
	public static String encrypt2(String content) {
		byte[] en = encrypt(content);
		return new String(Hex.encodeHex(en));
	}
	
	public static String decrypt2(String encrypeStr) throws Exception {
		byte[] de = decrypt(Hex.decodeHex(encrypeStr.toCharArray()));
		return new String(de);
	}

//	public static void main(String[] args) throws Exception {
//		String content = "你好";
//		String en = encrypt2(content);
//		System.out.println(en);
//		System.out.println(decrypt2(en));
//	}
}
