package reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg {

	/**
	 * .find()方法是部分匹配，是查找输入串中与模式匹配的子串，如果该匹配的串有组还可以使用group()函数。
		matches()是全部匹配，是将整个输入串与模式匹配，如果要验证一个输入的数据是否为数字类型或其他类型，一般要用matches()。
	 * @param args
	 */
	public static void main(String[] args) {
		String content = "{{first.DATA}}佣金金额：{{keyword1.DATA}}时间：{{keyword2.DATA}}{{remark.DATA}}{{wqewq22.DATA}}";
		show(content);
		
		
		Pattern p = Pattern.compile("\\{\\{\\w*\\.DATA}}");
		Matcher m = p.matcher(content);
		
		while(m.find()){//m.find() and m.matches()
			System.out.println(m.start() + "\t" + m.end());
			String s = m.group(0);
			System.out.println(s);
		}
	}

	static void show(String content) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbIndex = new StringBuilder();
		for (int i = 0; i < content.length(); i++) {
			sb.append("\t").append(content.charAt(i));
			sbIndex.append("\t").append(i);
		}
		System.out.println(sb.toString());
		System.out.println(sbIndex.toString());
	}
}
