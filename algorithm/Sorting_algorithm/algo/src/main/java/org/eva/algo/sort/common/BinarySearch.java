package org.eva.algo.sort.common;

public class BinarySearch {

	/**
	 * ���ֲ���
	 * 
	 * @param src
	 * @param num
	 * @return
	 */
	public static int search(int[] src, int num) {
		if (num < src[0]) {
			return -1;
		}
		if (num > src[src.length - 1]) {
			return -2;
		}
		return search(src, 0, src.length - 1, num);
	}

	private static int search(int[] src, int l, int r, int num) {
		if (l <= r) {
			int mid_index = l + (r - l) / 2;
			if (num < src[mid_index]) {
				return search(src, l, mid_index - 1, num);
			} else if (num > src[mid_index]) {
				return search(src, mid_index + 1, r, num);
			} else {
				return mid_index;
			}
		}
		return -3;
	}

	/**
	 * ��src���ҵ�num��λ��
	 * 
	 * @param src
	 * @param max_index
	 * @param num
	 * @return
	 */
	public static int search(int[] src, int max_index, int num) {
		if (num < src[0]) {
			return 0;
		}
		if (num > src[max_index]) {
			return max_index;
		}
		int left = 0;
		int right = max_index;
		while (true) {
			int mid_index = left + (right - left) / 2;
			if (num < src[mid_index]) {
				if (num > src[mid_index - 1]) {
					return mid_index;
				}
				right = mid_index - 1;
			} else if (num > src[mid_index]) {
				if (num < src[mid_index + 1]) {
					return mid_index + 1;
				}
				left = mid_index + 1;
			} else {
				return mid_index;
			}
		}
	}

	public static void main(String[] args) {
		int[] src = { 0, 1, 2, 3, 4, 5, 7, 8, 9 };
		for (int i = -1; i < 11; i++) {
			System.out.println(i + "\t:" + BinarySearch.search(src, 8, i));
		}

	}
}
