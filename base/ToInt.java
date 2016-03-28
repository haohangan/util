package com.eva.core.string;

public class ToInt {

	public static void main(String[] args) {
		// Integer s = new Integer("03");
		// System.out.println(s);
		String str = "12.031";
		Integer rtn = ToInt.TOInt(str);
		System.out.println("rtn:" + rtn);
	}

	/**
	 * 
	 * @param intString
	 *            格式为 xxxx.xxxx ,xxxx为数字
	 * @return
	 */
	public static Integer TOInt(final String intString) {
		Integer rtn1 = null;
		String[] arr = intString.split("\\.");
		String fn = "59";
		if (arr.length == 2) {
			rtn1 = new Integer(arr[0]);
			int ch = arr[1].codePointAt(0);
			if (ch >= fn.codePointAt(0) && ch <= fn.codePointAt(1)) {
				rtn1 += 1;
			}
		}
		return rtn1;
	}

	public static void print() {
		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int len = str.length();
		for (int i = 0; i < len; i++) {
			System.out.println(str.charAt(i) + "\t:" + str.codePointAt(i)
					+ "\t=" + new Integer(str.charAt(i)));
		}
	}

	public static void test() {
	}
}
