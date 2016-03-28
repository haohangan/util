package dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��24�� ����11:03:06 ��˵��
 */
public class ObjectInvocation implements InvocationHandler {

	private Object target;

	public ObjectInvocation(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("�������ִ�з���");
		Object result = method.invoke(target, args);
		System.out.println("��ȥ����ִ�з���");
		return result;
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(ObjectInvocation.class.getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

}
