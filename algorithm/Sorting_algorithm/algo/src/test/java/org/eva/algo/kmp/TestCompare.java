package org.eva.algo.kmp;

import org.eva.algo.character.ProperPrefix_Suffix;
import org.junit.Test;

public class TestCompare {

	String src = "abababca";
	String src2 = "ABCDABD";

	@Test
	public void testCompare() {
		System.out.println(ProperPrefix_Suffix.compare(src, 0, 6, 1, 7));
		System.out.println(ProperPrefix_Suffix.compare(src, 0, 5, 2, 7));
		System.out.println(ProperPrefix_Suffix.compare(src, 0, 4, 3, 7));
		System.out.println(ProperPrefix_Suffix.compare(src, 0, 3, 4, 7));
		System.out.println(ProperPrefix_Suffix.compare(src, 0, 2, 5, 7));
		System.out.println(ProperPrefix_Suffix.compare(src, 0, 1, 6, 7));
		System.out.println(ProperPrefix_Suffix.compare(src, 0, 0, 7, 7));
	}

	@Test
	public void testStep() {
		int start = 0;
		int end = src.length();
		int len = end - start;

		for (; len > 0; len--) {
			int step = len - 1;
			boolean result = ProperPrefix_Suffix.compare(src, start, start + step, end - step, end);
			System.out.println(result);
		}
	}

	@Test
	public void testProper_prefix_suffix() {
		for (int i = 1; i < src.length(); i++) {
			System.out.println("i==" + i);
			String[] arr = ProperPrefix_Suffix.proper_prefix_suffix(src, 0, i);
			for (String s : arr) {
				System.out.println(s);
			}
			System.out.println("i==" + i + "--------------");
		}
	}

	@Test
	public void test_Partial_table() {
		int[] partial_table = new int[src2.length()];
		for (int i = 1; i < src2.length(); i++) {
			String[] arr = ProperPrefix_Suffix.proper_prefix_suffix(src2, 0, i);
			for (String s : arr) {
				if (s.length() > partial_table[i]) {
					partial_table[i] = s.length();
				}
			}
		}
		for (int p : partial_table) {
			System.out.print(p + "\t");
		}
	}
	
}
