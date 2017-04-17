package org.eva.id.kmp;

/**
 * @author guihao
 * @date 2017年4月17日下午4:35:46
 * @desc 
 */
public class KMP3 {

	public static int search(String S, String W) {
		int[] fail = KMP.getPartialTable(W);// 获取失配函数
		int sLen = S.length();
		int wLen = W.length();
		int m = 0;//m代表主文字符串S内匹配字符串W的当前查找位置，
		int i = 0;//i代表匹配字符串W当前做比较的字符位置。
		while(m<=sLen && i <= wLen){
			if(S.charAt(m+i) == W.charAt(i)){
				if(i==(wLen-1)){//W的匹配位置已经移动到最后，则说明已经完全匹配上了
					return m;
				}
				i++;
			}else{
				m = m + (i-fail[i-1]);
				i = fail[i-1];
				i++;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String text = "ABC ABCDAB ABCDABCDABDE";
		String pattern = "ABCDABD";
		int pos = search(text, pattern);
		System.out.println(pos);// 14
	}
}


//int m = text.length();
//int n = pattern.length();
//int t = 0;//text匹配到的位置
//int p = 0;//pattern上已经匹配的长度
//
//while((m-t+1) <= (n-p+1)){
//	if(text.charAt(t+p) == pattern.charAt(p)){
//		if(p==n){
//			return t;
//		}
//		p++;
//	}else{
//		t = t + p - fail[p-1]; 
//		p = fail[p-1]; 
//	}
//}
