package org.eva.algo.sort.insertion;

import org.eva.algo.sort.common.BinarySearch;
import org.eva.algo.sort.common.DisorderedHelper;

/**
 * ���ֲ��ҷ��Ĳ�������
 * 
 * @author 976175665
 * @date 2017��4��9�� ����6:40:35
 */
public class BinarySearchInsertionSort {

	public static void sort(int[] src) {
		int i = 1;
		int j;
		int temp;
		for (; i < src.length; i++) {
			j = i - 1;
			temp = src[i];
			int m_index = BinarySearch.search(src, j, temp);
			if (temp <= src[m_index]) {
				for (; j >= m_index; j--) {
					DisorderedHelper.swap(src, j, j + 1);
				}
				src[m_index] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = DisorderedHelper.getArr();
		DisorderedHelper.print(arr);
		sort(arr);
		System.out.println("===========�����");
		DisorderedHelper.print(arr);

	}
}
