package algorithm.str;

public class TestString {
//     String str;

//     StringBuilder sb;
	
	public static void main(String[] args) {
//		String s = "abc123";
//		String b = "abc123";
//		System.out.println("s== \"abc\":"+s=="abc123");
//		System.out.println("s== \"abc\":"+(s=="abc123"));
//		System.out.println("s==b:"+s==b);
//		System.out.println("s==b:"+(s==b));
//		System.out.println("s.equals(b):"+s.equals(b));
//		s.intern()
		/*String a = new String("abcdefg");
		String b = new String("abcdefg");
		System.out.println(a==b);
		
		String a1 = new String("abcdefg").intern();
		String b1 = new String("abcdefg").intern();
		System.out.println(a1==b1);*/
		String st1 = "abc123";
		String st2 = "abc"+"123";
		String st3 = "abc";
		String st4 = "123";
		System.out.println(st1==st2);
		System.out.println(st1==(st3+st4));
	}
}
