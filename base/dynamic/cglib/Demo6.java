package simple.cglib;

import net.sf.cglib.beans.ImmutableBean;

public class Demo6 {

	static class Rtn {
		static int a = 0;
		private String str;

		@Override
		public String toString() {
			return "Rtn [str=" + str + "]";
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		public Rtn() {
			System.out.println("init rtn");
			str = (++a)+ "";
		}
	}
	
	static class Sample{
		private String str;
		
		

		public Sample() {
			System.out.println("init Sample");
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}
		
		
	}

	public static void main(String[] args) {
		Sample s = new Sample();
		s.setStr("abcd");
		System.out.println(s);
		Sample s2 =(Sample)ImmutableBean.create(s);
		System.out.println(s2);
		s2.setStr("asd");
	}

	static void test1(){
		Rtn r1 = new Rtn();
		Rtn r2 =(Rtn)ImmutableBean.create(r1);
//		r.setStr("aaaaaaaaaaa");
		System.out.println(r2.getStr());
		System.out.println(r2);
		
		System.out.println(r1);
		System.out.println(r1);
	}
	
}
