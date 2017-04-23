package org.eva.algo.character;

public class KMP {

	public static int search(String S, String W) {
		int[] fail = ProperPrefix_Suffix.partial_table(W);// 失配函数
		int sLen = S.length();
		int wLen = W.length();
		int m = 0;//主文字字符串S中，下一个需要比较的位置
		int i = 0;// 模式串W中，下一个需要比较的位置
		// while ((m < sLen) && (i < wLen)) {
		while ((m + i < sLen) && (i < wLen)) {
			if (S.charAt(m + i) == W.charAt(i)) {
				if ((i + 1) == wLen) {
					return m;
				}
				i++;
			} else {
				if (i == 0) {
					m++;
				} else {
					//《没有调整之前，已经在不匹配的位置之上了》
					m = m + i - fail[i - 1];// 跳过的位置（此时m已经在下一个匹配的位置上了）
					i = fail[i - 1] + 1;// 移位到下一个需要匹配的位置
				}
			}
		}
		return -1;
	}

}