package com.grgbanking.api.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;

/**
 * 通用工具类
 * 
 * @author guihao <br>
 *         ghao3@grgbanking.com
 * 
 */
public class Utils {
	private Utils() {
	}

	private static final NumberFormat cashAmount = new DecimalFormat("###0.00");
	private static final String dateFormat = "yyyy-MM-dd hh:mm:ss";
	private static final FastDateFormat fdf = FastDateFormat.getInstance(dateFormat);
	private static final Charset charSet = Charset.forName("UTF-8");

	/**
	 * 金额格式Number 单位为元，必须是含两位小数。格式为99.00
	 * 
	 * @param amount
	 * @return
	 */
	public static String formatCurrency(double amount) {
		return cashAmount.format(amount);
	}

	/**
	 * 日期和时间格式 统一使用yyyy-MM-dd hh:mm:ss格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return fdf.format(date);
	}

	/**
	 * 信息摘要
	 * 
	 * @param info
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] MD5encrypt(String info) throws NoSuchAlgorithmException {
		// 根据MD5算法生成MessageDigest对象
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcBytes = info.getBytes();
		// 使用srcBytes更新摘要
		md5.update(srcBytes);
		// 完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	/**
	 * 信息摘要
	 * 
	 * @param info
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] MD5encrypt(byte[] srcBytes) throws NoSuchAlgorithmException {
		// 根据MD5算法生成MessageDigest对象
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		// 使用srcBytes更新摘要
		md5.update(srcBytes);
		// 完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	/**
	 * 将contetn内容转换为utf-8编码方式的base64字符串
	 * 
	 * @param jsonContent
	 * @return
	 */
	public static String contentEncode64(String jsonContent) {
		byte[] bytes = Base64.encodeBase64(jsonContent.getBytes(charSet));
		return new String(bytes, charSet);
	}

	/**
	 * 字节数据转字符串专用集合
	 */
	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * 字节数据转十六进制字符串
	 * 
	 * @param data
	 *            输入数据
	 * @return 十六进制内容
	 */
	public static String byteArrayToString(byte[] data) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			// 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
			stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
			// 取出字节的低四位 作为索引得到相应的十六进制标识符
			stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
			if (i < data.length - 1) {
//				stringBuilder.append(' ');
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 加签
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String sign(List<String> list) throws Exception {
		String content = StringUtils.join(list, "##");
		byte[] encrypt = RSAUtils.encrypt(contentEncode64(content));
//		byte[] rtn = MD5encrypt(encrypt);
//		System.out.println("1:\t"+new String(org.apache.commons.codec.binary.Hex.encodeHex(RSAUtils.encrypt(contentEncode64(content)))));
//		System.out.println("2:\t"+new String(rtn));
		System.out.println(byteArrayToString(encrypt));
		System.out.println(new String(org.apache.commons.codec.binary.Hex.encodeHex(encrypt)));
		return new String(org.apache.commons.codec.binary.Hex.encodeHex(encrypt));
//		return byteArrayToString(rtn);
	}
	
}
