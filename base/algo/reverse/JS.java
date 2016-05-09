package algorithm.sort;
/*
*编程主机：将一个有n个元素的一维向量向左旋转i个位置
*比如 n=8，i=3 的向量 abcdefgh旋转后得到 defghabc
*/
public class JS {
	char[] arr;

	public JS(char[] arr) {
		this.arr = arr;
	}

	public void print() {
		if (arr == null) {
			System.out.println("数组为空");
			return;
		}
		for (char c : arr) {
			System.out.print(c + "\t");
		}
		System.out.println();
	}

	private void moveOne() {
		char temp = arr[0];
		for (int i = 1; i < arr.length; i++) {
			arr[i - 1] = arr[i];
		}
		arr[arr.length-1] = temp;
	}

	public void moveN(int n) {
		for (int i = 0; i < n; i++) {
			moveOne();
		}
	}

	public static void main(String[] args) {
		// System.out.println(Math.log(1000)/Math.log(2));
		char[] arr = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
		int i = 3;
		JS j = new JS(arr);
		j.print();
		j.moveN(i);
		j.print();
	}
}
