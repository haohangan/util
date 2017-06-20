package simple.cglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.objectweb.asm.Type;

import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class InterfaceMakerTest {
	public static void main(String[] args) {
		// method signature
		Signature signature = new Signature("getName", Type.getType(String.class), new Type[0]);
		InterfaceMaker imaker = new InterfaceMaker();
		imaker.add(signature, new Type[0]);

		Class<?> iface = imaker.create();
		System.out.println(iface.getMethods().length);
		System.out.println(iface.getMethods()[0]);
		System.out.println(iface.getMethods()[0].getReturnType());

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(iface);

		enhancer.setCallback(new MethodInterceptor() {

			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Logger.getGlobal().info("before method:" + method);
				Object result = proxy.invokeSuper(obj, args);
				Logger.getGlobal().info("after method:" + method);
				Logger.getGlobal().info("result:" + result);
				return result;
			}
		});
		System.out.println("--------------");
		Object obj = enhancer.create();
		System.out.println(obj);
		try {
			iface.getMethods()[0].invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
	}
}