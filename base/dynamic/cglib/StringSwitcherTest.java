package simple.cglib;

import net.sf.cglib.util.StringSwitcher;

public class StringSwitcherTest {
    public static void main(String[] args) {
		String[] strs = new String[]{"one","two","three","four","five"};
		int[] ints = new int[]{1,2,3,4,5};
		 StringSwitcher stringSwitcher = StringSwitcher.create(strs, ints, true);
		 System.out.println(stringSwitcher.intValue("one"));
	}
}
