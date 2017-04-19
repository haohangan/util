package org.eva.algo.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5讯息摘要演算法（英语：MD5 Message-Digest
 * Algorithm），一种被广泛使用的密码杂凑函数，可以产生出一个128位元（16位元组）的散列值（hash
 * value），用于确保信息传输完整一致。MD5由罗纳德·李维斯特设计，于1992年公开，用以取代MD4演算法。这套演算法的程序在 RFC 1321
 * 中被加以规范。 将数据（如一段文字）运算变为另一固定长度值，是杂凑算法的基础原理。
 * 1996年后被证实存在弱点，可以被加以破解，对于需要高度安全性的资料，专家一般建议改用其他演算法，如SHA-1。2004年，证实MD5演算法无法防止碰撞，因此无法适用于安全性认证，如SSL公开金钥认证或是数位签章等用途。
 * 目前，MD5算法因其普遍、稳定、快速的特点，仍广泛应用于普通数据的错误检查领域。例如在一些BitTorrent下载中，软件将通过计算MD5检验下载到的文件片段的完整性。
 * 2009年谢涛和冯登国仅用了220.96的碰撞算法复杂度，破解了MD5的碰撞抵抗，该攻击在普通计算机上运行只需要数秒钟[1]。 MD5("")=
 * d41d8cd98f00b204e9800998ecf8427e
 * 
 * @author 976175665
 * @date 2017年4月19日 下午11:22:14
 */
public class MD5Hash {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytes = md.digest("".getBytes());
		System.out.println("长度："+bytes.length);
		for (byte b : bytes) {
			String s = Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3);
			System.out.print(s);
		}
		System.out.println();
		System.out.println(new BigInteger(1,bytes).toString(16));
	}
}
