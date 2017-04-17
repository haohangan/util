package org.eva.id.kmp;

/**
 * @author guihao
 * @date 2017年4月17日上午11:27:17
 * @desc
 */
public class KMP {

	/**
	 * 失配函数，跳转表，匹配数组
	 * @param pattern
	 * @return
	 */
	public static int[] getPartialTable(String pattern) {
		int size = pattern.length();
		int[] partialTab = new int[size];
		for (int i = 1; i < size; i++) {
			int partical = partialTab[i - 1];
			while (partical > 0 && (pattern.charAt(partical) != pattern.charAt(i))) {
				partical = partialTab[partical];// 确定当前位置之前的匹配长度
			}
			partialTab[i] = (pattern.charAt(partical) == pattern.charAt(i)) ? partical + 1 : 0;// 从最后一个字符开始比较，并且
		}
		return partialTab;
	}

	public static void main(String[] args) {
		String pattern = "abababca";
		System.out.print("下标:\t\t");
		for (int i = 0; i < pattern.length(); i++) {
			System.out.print(i + "\t");
		}
		System.out.println();
		System.out.print("pattern:\t");
		for (int i = 0; i < pattern.length(); i++) {
			System.out.print(pattern.charAt(i) + "\t");
		}
		System.out.println();
		System.out.print("匹配函数:\t\t");
		int[] arr = getPartialTable(pattern);//
		for (int t : arr) {
			System.out.print(t + "\t");
		}
	}
	/**
	 *     下标: 0 1 2 3 4 5 6 
	 *  pattern: A B C D A B D 
	 * 	匹配函数: 0 0 0 0 1 2 0
	 * 失配函数（fail）（跳转表的含义）：与之前位置匹配的长度是多少 匹配都是从首字母开始， 
	 * pattern下标为5的字符，与之前匹配的长度为fail[5] = 2,[0,1] 匹配 [4,5] 
	 * pattern下标为4的字符，和之前匹配的长度为 fail[4] = 1,[0,0] 匹配[4,4]
	 */
	
}
