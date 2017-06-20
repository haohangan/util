package simple.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

public class Demo2 {

	public static void main(String[] args) {
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(Apple.class);
		hancer.setCallback(new FixedValue() {
			int i = 0;

			@Override
			public Object loadObject() throws Exception {
				System.out.println("loadObject");
				return "1" + (i++);
			}

		});
		Object proxy = hancer.create();
		Apple app = (Apple) proxy;

		System.out.println("name:" + app.getName());
		app.setName("a");
		System.out.println("name:" + app.getName());
//		 System.out.println(app.getDouble());
	}
}
