package org.eva.algo.character;

/**
 * 失配函数
 * 
 * @author 976175665
 * @date 2017年4月28日 下午1:11:09
 */
public class Fail {

	/**
	 * 简化了下
	 * @param pattern
	 * @return
	 */
	public static int[] fail(String pattern) {
		int pLen = pattern.length();
		int[] fail = new int[pLen];
		for(int i = 1;i<pLen;i++){
			int p = fail[i-1];
			if(p==0){
				fail[i] = pattern.charAt(0)==pattern.charAt(i)?1:0;
			}else{
				fail[i] = pattern.charAt(p)==pattern.charAt(i)?(p+1):0;
			}
		}
		return fail;
	}
	
	public static void main(String[] args) {
		int[] fail = fail("abracadabra");
		for(int t:fail){
			System.out.print(t+"\t");
		}
	}
}
