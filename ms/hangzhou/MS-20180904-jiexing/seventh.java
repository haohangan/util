package ms;

import java.util.Random;

public class Seventh {
	
	
	static  void  swap(int x,int y,int arr[]) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	
	static void sort(int arr[]) {
		quick_sort(0,arr.length-1,arr);
	}
	
	
	static  void quick_sort(int left,int right,int arr[]) {
		if(left<right) {
			int pa = partition(left,right,arr);
			quick_sort(left,pa-1,arr);
			quick_sort(pa+1,right,arr);
		}
	}
	
	/**
	 * 找到中间数
	 * @param left
	 * @param right
	 * @param arr
	 * @return
	 */
	static int partition(int left,int right,int arr[]) {
		int povit = arr[right];
		int i = left - 1;
		for(int  j = left;j<right;j++) {//冒泡
			if(arr[j]<povit) {
				i++;
				swap(i,j,arr);
			}
		}
		swap(i+1,right,arr);
		return i+1;
	}
	
	
	/**
	 * 洗牌
	 * @param arr
	 */
	static void shuffle(int arr[]) {
		int len = arr.length;
		Random rgen = new Random();
		
		for(int i = 0;i<len;i++) {
			int index = rgen.nextInt(len);
			swap(i,index,arr);
		}
	}
	
	
	/**
	 * 输出数组
	 * @param arr
	 */
	static void print(int arr[]) {
		for(int i = 0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		int[] arr = {0,1,2,3,4,5,6,7,8,9};
		shuffle(arr);
		print(arr);
		sort(arr);
		print(arr);
	}
}
