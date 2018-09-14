package simple;

/**
 * 给定一个字符序列，以O(n)时间复杂度删除序列里的重复序列
 * @author Administrator
 *
 */
public class Delete {

	
	public static void main(String[] args) {
//		char[] s = {'a','b','c','a','d','b','c','a'};//目标输出  acbd
		char[] s = {'a','d','b','b','c','a','c','a'};//目标输出  acbd
		int len = s.length;
		
		boolean[] indexFlag = new boolean[len];
		
		for(int i = 0;i<len;i++) {
			char c = s[i];
			int index = myHash(c,len);
			if(indexFlag[index] == false) {
				indexFlag[index] = true;
			}else {
				s[i] = ' ';
			}
			
		}
		
		for(int i = 0;i<len;i++) {
			System.out.print(s[i]);
		}
		
//		System.out.println((char)97);
	}
	
	/**
	 * 找到下标
	 * @param c
	 * @param len
	 * @return
	 */
	static int myHash(char c,int len) {
		return c%(char) len;
	}
}
