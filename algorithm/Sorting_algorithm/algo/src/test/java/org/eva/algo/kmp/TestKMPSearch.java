package org.eva.algo.kmp;

import org.eva.algo.character.KMP;
import org.junit.Test;

public class TestKMPSearch {

	
	
	String[] arr = {"A","AA","AAA","AAAA","AAAAA","AAAAAA","AAAAAAA","AAAAAAAA",
			"AB","ABAB","ABABAB","ABABABAB",
			"ABC","ABCABC","ABCABCABC",
			"ABCD","ABCDABCD","ABCDABCD",
			"ABCDA","ABCDAABCDA",
			"ABCDAB","ABCDABABCDAB",
			"ABCDABD","ABCDABDABCDABD",
			"1111111111111111111111111",
			"DDDDDDDDDDDDDDDDABCDABD",
			"EEEEEEEEABCDABDEEEEEEEE"};
	
	String pattern = "ABCDABD";
	
	@Test
	public void testSearch(){
		
		for(String text:arr){
			int index = KMP.search(text, pattern);
			System.out.printf("%s:%d\r\n",text,index);//text+"\t:\t\t"+index
		}
	}
}
