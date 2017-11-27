package reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phone {
	/**
	 * http://www.cnblogs.com/ggjucheng/p/3423731.html
	 * @param args
	 */
   public static void main(String[] args) {
	   Pattern p=Pattern.compile("\\d+"); 
	   Matcher m=p.matcher("我的QQ是:456456 我的电话是:0532214 我的邮箱是:aaa123@aaa.com"); 
	   while(m.find()) { 
	        System.out.println(m.group()); 
	   } 
}
}
