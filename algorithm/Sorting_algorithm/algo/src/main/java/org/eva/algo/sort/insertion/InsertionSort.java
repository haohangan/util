package org.eva.algo.sort.insertion;

import org.eva.algo.sort.common.DisorderedHelper;

/**
 * һ����˵���������򶼲���in-place��������ʵ�֡������㷨�������£� 
 * 1:�ӵ�һ��Ԫ�ؿ�ʼ����Ԫ�ؿ�����Ϊ�Ѿ�������
 * 2:ȡ����һ��Ԫ�أ����Ѿ������Ԫ�������дӺ���ǰɨ�� 
 * 3:�����Ԫ�أ������򣩴�����Ԫ�أ�����Ԫ���Ƶ���һλ��
 * 4:�ظ�����3��ֱ���ҵ��������Ԫ��С�ڻ��ߵ�����Ԫ�ص�λ�� 
 * 5:����Ԫ�ز��뵽��λ�ú� 
 * 6:�ظ�����2~5
 * ����Ƚϲ����Ĵ��۱Ƚ���������Ļ������Բ��ö��ֲ��ҷ������ٱȽϲ�������Ŀ�����㷨������Ϊ�ǲ��������һ�����֣���Ϊ���ֲ��Ҳ�������
 * 
 * @author 976175665
 * @date 2017��4��9�� ����4:26:17
 */
public class InsertionSort {

	public static void sort(int[] src) {
		int i, j;
		int temp;
		for (i = 1; i < src.length; i++) {
			temp = src[i];
			for (j = i - 1; j >= 0; j--) {
				if (temp < src[j]) {
					DisorderedHelper.swap(src, j, j + 1);
				} else {
					src[j + 1] = temp;
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = DisorderedHelper.getArr(10);
		DisorderedHelper.print(arr);
		sort(arr);
		System.out.println("===========�����");
		DisorderedHelper.print(arr);
	}
}
