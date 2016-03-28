package core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import core.protocal.Invocation;
import core.support.Client;
import core.support.Listener;
import core.support.Server;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����3:56:18 ��˵��
 */
public class RPC {
	public static <T> T getProxy(final Class<T> clazz, String host, int port) {
		final Client client = new Client(host, port);
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Invocation invo = new Invocation();// ��ʼ������

				invo.setInterfaces(clazz);// ���÷�����ƽ�java.lang.reflect.Method
											// ������ķ�����,���� ��װ�� Invocation invo������
				invo.setMethod(new core.protocal.Method(method.getName(),
						method.getParameterTypes()));
				invo.setParams(args);

				client.invoke(invo);

				return invo.getResult();// ��ȡ���
			}
		};
		@SuppressWarnings("unchecked")
		T t = (T) Proxy.newProxyInstance(RPC.class.getClassLoader(),
				new Class[] { clazz }, handler);
		return t;
	}

	public static class RPCServer implements Server {

		private int port = 20382;
		private Listener listener;
		private boolean isRunning = true;

		private Map<String, Object> serviceEngine = new HashMap<String, Object>();

		public void setRunning(boolean isRunning) {
			this.isRunning = isRunning;
		}

		@Override
		public void stop() {
			this.setRunning(false);
		}

		@Override
		public void start() {
			System.out.println("����������");
			/*
			 * server ����ʱ,��ҪListener�����Ƿ���client���������� listener ��һ���߳�,��������������
			 */
			listener = new Listener(this);
			this.isRunning = true;
			listener.start();// listener ��һ���߳���,start()���ִ���̵߳�run����
		}

		@Override
		public void register(Class<?> interfaceDefiner, Class<?> interfaceImpl) {
			try {
				serviceEngine.put(interfaceDefiner.getName(),
						interfaceImpl.newInstance());
				System.out.println(serviceEngine);
			} catch (Throwable th) {
				th.printStackTrace();
			}
		}

		@Override
		public void call(Invocation invo) {
			System.out.println(invo.getClass().getName());
			Object obj = serviceEngine.get(invo.getInterfaces().getName());
			if (obj != null) {
				try {
					Method m = obj.getClass().getMethod(
							invo.getMethod().getMethodName(),
							invo.getMethod().getParams());// �{�õķ���
					Object result = m.invoke(obj, invo.getParams());
					invo.setResult(result);

				} catch (Throwable th) {
					th.printStackTrace();
				}
			} else {
				throw new IllegalArgumentException("has no these class");
			}
		}

		@Override
		public boolean isRunning() {
			return isRunning;
		}

		@Override
		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

	}
}
