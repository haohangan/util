package algorithm.sort;

public class Reverse {
	Object[] arr;

	public Reverse(Object[] arr) {
		this.arr = arr;
	}

	public void swap(int x, int y) {
		// System.out.printf("下标%d和%d",x,y);
		// System.out.println();
		if (x == y) {
			return;
		}
		Object temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	public void reverse(int start, int end) {
		if (start >= end) {
			return;
		}
		int time = (end - start + 1) / 2;
		for (int i = 0; i < time; i++) {
			swap(start + i, end - i);
		}
	}

	public void print() {
		for (Object obj : arr) {
			System.out.print(obj + "\t");
		}
		System.out.println();
	}

	public void moveN(int i) {
		reverse(0, i - 1);
		reverse(i, arr.length - 1);
		reverse(0, arr.length - 1);
	}

	public static void main(String[] args) {
		Character[] arr = new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' };
		Reverse r = new Reverse(arr);
		r.print();
		// r.reverse(0, 8);
		// r.reverse(0, 4);
		r.moveN(3);
		r.print();
	}
}
