package org.eva.algo.character;

import java.util.ArrayList;
import java.util.List;

public class ProperPrefix_Suffix {

	/**
	 * 给定字符串的跳转表：简单求法
	 * 
	 * @param src
	 * @return
	 */
	public static int[] partial_table(String src) {
		int[] partial_table = new int[src.length()];
		for (int i = 1; i < src.length(); i++) {
			String[] arr = ProperPrefix_Suffix.proper_prefix_suffix(src, 0, i);
			for (String s : arr) {
				if (s.length() > partial_table[i]) {
					partial_table[i] = s.length();
				}
			}
		}
		return partial_table;
	}

	/**
	 * 求出前缀和后缀相的公有元素
	 * 
	 * @param src
	 * @param start
	 * @param end
	 * @return 共有元素的数组
	 */
	public static String[] proper_prefix_suffix(String src, int start, int end) {
		if (src == null) {
			return null;
		}
		List<String> list = new ArrayList<>();
		int len = end - start;

		for (; len > 0; len--) {
			int step = len - 1;
			boolean result = compare(src, start, start + step, end - step, end);
			if (result) {
				list.add(src.substring(start, start + step + 1));
			}
		}
		String[] arr = new String[list.size()];
		list.toArray(arr);
		return arr;
	}

	/**
	 * 比较两个范围内的字符串是否匹配
	 * 
	 * @param src
	 * @param prefix_start
	 * @param prefix_end
	 * @param suffix_start
	 * @param suffix_end
	 * @return
	 */
	public static boolean compare(String src, int prefix_start, int prefix_end, int suffix_start, int suffix_end) {
		if (suffix_start == prefix_start && suffix_start == suffix_end) {
			if (src.charAt(prefix_start) == src.charAt(suffix_start)) {
				return true;
			}
		} else {
			if ((prefix_end - prefix_start) == (suffix_end - suffix_start)) {
				for (; prefix_start <= prefix_end; prefix_start++, suffix_start++) {
					if (src.charAt(prefix_start) != src.charAt(suffix_start)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
