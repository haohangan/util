package ms;

import io.jsonwebtoken.lang.Objects;
/**
 * 翻转序列
 * @author 97617
 *
 */
public class Third {
	
	public static void main(String[] args) {
		char[] s = {'1','2','3','4','5','6'};
		reverse(s);
		
		char[] s1 = {'1','2','3','4','5','6','7'};
		reverse(s1);
	}

	private static void reverse(char[] s) {
		int len = s.length/2;
		
		for(int i = 0;i<=len;i++) {
			swap(i,s.length-i-1,s);
		}
		
		System.out.println(Objects.getDisplayString(s));
	}
	
	static void swap(int x,int y,char[] s) {
		char temp = s[x];
		s[x] = s[y];
		s[y] = temp;
	}

}
