package org.eva.algo.character;

public class ProperPrefixSuffix {

	/**
	 * 更高效的做法
	 * 部分匹配表(失配函数 fail(n))规律： 
	 * 1:第一位肯定是0,fail(0) = 0
	 * 2:必定是[0,...,0,1,2,3,4,...,0,0,...]
	 * 这种类型的数组，若跳转值不为0，则在表内必定存在为连续的整数，不会出现[0,2,4,5,0]这种情况
	 * 3:表中值得关系:要么为0，要么比前一个值大1
	 * 4:求匹配表第j个位置的值，需要先查看fail(j-1)的值,如果值为fail(j-1)=0
	 * @param pattern
	 * @return
	 */
	public static int[] fail(String pattern) {
		int pLen = pattern.length();
		int[] fail = new int[pLen];
		int p = 0;
		for (int j = 1; j < pLen; j++) {
			p = fail[j - 1];//获取匹配表中前一位
			if (p == 0) {//若p==0,则直接将pattern[j] 和 pattern[0] 比较即可
				fail[j] = pattern.charAt(0) == pattern.charAt(j) ? 1 : 0;
			} else {//若p!=0,则说明 [0,p-1] 和 [j-1-p,j-1]是匹配的，直接将pattern[p] 和 pattern[j] 比较即可
				if (pattern.charAt(p) == pattern.charAt(j)) {
					fail[j] = p + 1;
				}
			}

		}
		return fail;
	}
}
