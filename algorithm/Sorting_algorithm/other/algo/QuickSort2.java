package algorithm.basic.copy;

public class QuickSort2 {
	int[] arr;

	public QuickSort2(int[] arr) {
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
		int mid = arr[left];
		int i = left;
		int j = right + 1;
		while (i < j) {
			while (--j > i) {
				if (arr[j] <= mid) {
					break;
				}
			}

			while (++i < j) {
				if (arr[i] > mid) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			swap(i, j);
		}
		swap(left, j);
		sort(left, j - 1);
		sort(j + 1, right);
	}

	public void print() {
		for (int i : arr) {
			System.out.printf("%d\t", i);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 3, 2, 7, 5, 0,8, 9, 1, 6, 4, 8 };
		QuickSort2 qs = new QuickSort2(arr);
		qs.print();
		qs.sort();
		qs.print();
	}
}
