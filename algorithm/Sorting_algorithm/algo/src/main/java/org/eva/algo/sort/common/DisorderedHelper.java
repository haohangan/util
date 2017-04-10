package org.eva.algo.sort.common;

import java.security.SecureRandom;

/**
 * ������
 * 
 * @author 976175665
 * @date 2017��4��9�� ����4:15:41
 */
public class DisorderedHelper {

	static SecureRandom sr = new SecureRandom();

	/**
	 * �����������
	 * 
	 * @param num
	 * @return
	 */
	public static int[] getArr(int num) {
		int[] arr = new int[num];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sr.nextInt(num);
		}
		return arr;
	}

	public static int[] getArr() {
		return new int[] { 4, 5, 2, 3, 6, 8, 0, 1, 7, 9 };
	}

	/**
	 * ����̨���
	 * 
	 * @param arr
	 */
	public static void print(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println("---------����");
	}

	/**
	 * ���������е�Ԫ��
	 * 
	 * @param src
	 * @param x
	 * @param y
	 */
	public static void swap(int[] src, int x, int y) {
		System.out.printf("����%d<->%d\r\n", x, y);
		int temp = src[x];
		src[x] = src[y];
		src[y] = temp;
	}
}
