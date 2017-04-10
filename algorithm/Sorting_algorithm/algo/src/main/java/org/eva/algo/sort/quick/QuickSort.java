package org.eva.algo.sort.quick;

import org.eva.algo.sort.common.DisorderedHelper;

/**
 * ��������ʹ�÷��η���Divide and
 * conquer����������һ�����У�list����Ϊ���������У�sub-lists���� ����Ϊ��
 * 1��������������һ��Ԫ�أ���Ϊ"��׼"��pivot����
 * 2:�����������У����бȻ�׼ֵС��Ԫ�ذڷ��ڻ�׼ǰ�棬���бȻ�׼ֵ���Ԫ�ذ��ڻ�׼���棨��ͬ�������Ե���һ�ߣ����������������֮�󣬸û�׼�ʹ������е��м�λ�á������Ϊ������partition��������
 * 3:�ݹ�أ�recursively����С�ڻ�׼ֵԪ�ص������кʹ��ڻ�׼ֵԪ�ص�����������
 * �ݹ鵽��ײ�ʱ�����еĴ�С�����һ��Ҳ�����Ѿ�������ˡ�����㷨һ�����������Ϊ��ÿ�εĵ�����iteration���У������ٻ��һ��Ԫ�ذڵ�������λ��ȥ��
 * 
 * @author 976175665
 * @date 2017��4��9�� ����10:55:26
 */
public class QuickSort {

	public static void sort(int[] src) {
		sort(src, 0, src.length - 1);
	}

	private static void sort(int[] src, int left, int right) {
		if (left >= right) {
			return;
		}
		// int mid = src[right];// ����˳��
		int pivotIndex = right;
		int i = left;
		int j = right - 1;
		while (i < j) {
			while (i < j && src[i] < src[pivotIndex]) {
				i++;
			}
			while (i < j && src[j] >= src[pivotIndex]) {
				j--;
			}
			if (i < j) {
				DisorderedHelper.swap(src, i, j);
			}
		}
		if (src[i] > src[pivotIndex]) {
			DisorderedHelper.swap(src, i, right);
			pivotIndex = i;
			sort(src, pivotIndex + 1, right);
		}
		sort(src, left, pivotIndex - 1);
	}

	/**
	 * 错误的
	 * @param src
	 * @param left
	 * @param right
	 */
	@SuppressWarnings("unused")
	private static void sort2(int[] src, int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = src[left];// ����˳��
		int i = left + 1;
		int j = right;
		while (i < j) {
			while (i < j && src[j] >= mid) {
				j--;
			}
			while (i < j && src[i] < mid) {
				i++;
			}
			if (i < j) {
				DisorderedHelper.swap(src, i, j);
			}
		}
		if (src[j] < mid) {
			DisorderedHelper.swap(src, j, left);
		} else {
			j--;
		}
		sort(src, left, j - 1);
		sort(src, j + 1, right);
	}

	public static void main(String[] args) {
		int[] arr = DisorderedHelper.getArr();
		// int[] arr = { 5, 3, 9, 7, 6 };
		DisorderedHelper.print(arr);
		sort(arr);
		System.out.println("===========�����");
		DisorderedHelper.print(arr);
	}
}