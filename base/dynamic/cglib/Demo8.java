package simple.cglib;

import net.sf.cglib.beans.BeanMap;

public class Demo8 {

	static class Sample {
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

	static class OtherSample {
		private String str;

		@Override
		public String toString() {
			return "OtherSample [str=" + str + "]";
		}

		public void setStr(String str) {
			this.str = str;
		}
		
	}

	public static void main(String[] args) {
		Sample s = new Sample();
		s.setStr("11111");
		BeanMap map = BeanMap.create(s);
		System.out.println(s);
		System.out.println(map);
		System.out.println(map.getPropertyType("str"));
		System.out.println(map.getBean());
		System.out.println(map.get("str"));
		map.clear();
		System.out.println(map.get("str"));
	}

}
