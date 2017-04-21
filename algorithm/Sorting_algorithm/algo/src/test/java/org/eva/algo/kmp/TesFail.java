package org.eva.algo.kmp;

import org.eva.algo.character.ProperPrefixSuffix;
import org.junit.Test;

public class TesFail {

	@Test
	public void test() {
		print("abababca");
		System.out.println("===============");
		print("ABCDABD");
		System.out.println("===============");
		print("ABCABCABCABCABCABCABCABC");
	}

	public void print(String pattern) {
		int[] arr = ProperPrefixSuffix.fail(pattern);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(pattern.charAt(i) + "\t");
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();
	}
}
