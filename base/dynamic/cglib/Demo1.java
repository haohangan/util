package simple.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Demo1 {

	public static void main(String[] args) {
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(Apple.class);
		hancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("Before invoke " + method);
				// Object result = proxy.invokeSuper(obj, args);
				// System.out.println("After invoke" + method);
				// return result;
				return "1111111";
			}
		});
		Object proxy = hancer.create();
		Apple app = (Apple) proxy;
		app.setName("22222222");
		System.out.println(app.getName());

	}
}
