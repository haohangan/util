package dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月24日 上午11:03:06 类说明
 */
public class ObjectInvocation implements InvocationHandler {

	private Object target;

	public ObjectInvocation(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("进入代理执行方法");
		Object result = method.invoke(target, args);
		System.out.println("出去代理执行方法");
		return result;
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(ObjectInvocation.class.getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

}
