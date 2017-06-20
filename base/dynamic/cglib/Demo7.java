package simple.cglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanGenerator;

public class Demo7 {

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
		BeanGenerator generator = new BeanGenerator();
		generator.setSuperclass(Sample.class);
		generator.addProperty("age", Integer.class);

		Object myBean = generator.create();

		try {
			Method setter = myBean.getClass().getMethod("setAge", Integer.class);
			setter.invoke(myBean, 100);

			Method getter = myBean.getClass().getMethod("getAge");
			System.out.println(getter.invoke(myBean));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------");

		BeanCopier copier = BeanCopier.create(Sample.class, OtherSample.class, false);
		Sample s1 = new Sample();
		s1.setStr("111111");
		
		OtherSample s2 = new OtherSample();
		
		copier.copy(s1, s2, null);
		System.out.println(s2);
	}

}
