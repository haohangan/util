package algorithm.basic.copy;

public class QuickSort {
	int[] arr;

	public QuickSort(int[] arr) {
		super();
		this.arr = arr;
	}

	private void swap(int x, int y) {
		if (x == y || arr[x] == arr[y]) {
			return;
		}
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	public void sort() {
		sort(0, arr.length - 1);
	}

	private void sort(int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = arr[right];
		int i = left - 1;
		int j = right;
		while (i < j) {
			while (++i < j) {
				if (arr[i] > mid) {
					break;
				}
			}

			while (--j > i) {
				if (arr[j] <= mid) {
					break;
				}
			}

			if (i >= j) {
				break;
			}
			swap(i, j);
		}
		swap(i, right);
		sort(left, i - 1);
		sort(i + 1, right);
	}

	public void print() {
		for (int i : arr) {
			System.out.printf("%d\t", i);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 3, 2, 7, 5, 0, 9, 1, 6, 4, 8 };
		QuickSort qs = new QuickSort(arr);
		qs.print();
		qs.sort();
		qs.print();
	}
}
