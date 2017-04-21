package org.eva.algo.sort.bubble;

import org.eva.algo.sort.common.DisorderedHelper;

/**
 * ð�������㷨���������£�
 * 1:�Ƚ����ڵ�Ԫ�ء������һ���ȵڶ����󣬾ͽ�������������
 * 2:��ÿһ������Ԫ����ͬ���Ĺ������ӿ�ʼ��һ�Ե���β�����һ�ԡ��ⲽ���������Ԫ�ػ�����������
 * 3:������е�Ԫ���ظ����ϵĲ��裬�������һ����
 * 4:����ÿ�ζ�Խ��Խ�ٵ�Ԫ���ظ�����Ĳ��裬ֱ��û���κ�һ��������Ҫ�Ƚϡ�
 * �������ļ�࣬ð������ͨ�����������ڳ���������ŵ�ѧ�������㷨�ĸ��
 * 
 * @author 976175665
 * @date 2017��4��9�� ����4:15:22
 */
public class BubbleSort {
	public static void sort(int[] src) {
		for (int i = 0; i < src.length; i++) {
			for (int j = i + 1; j < src.length; j++) {
				if (src[i] > src[j]) {
					DisorderedHelper.swap(src, i, j);
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
