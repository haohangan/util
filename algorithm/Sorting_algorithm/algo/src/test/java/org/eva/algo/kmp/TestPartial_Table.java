package org.eva.algo.kmp;

import org.eva.algo.character.ProperPrefix_Suffix;
import org.junit.Test;

public class TestPartial_Table {

	@Test
	public void test() {
		print("abababca");
		System.out.println("===============");
		print("ABCDABD");
	}

	public void print(String src) {
		int[] arr = ProperPrefix_Suffix.partial_table(src);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(src.charAt(i) + "\t");
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}
}
