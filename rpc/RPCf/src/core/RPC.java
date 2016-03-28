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
 * @version 创建时间：2015年11月18日 下午3:56:18 类说明
 */
public class RPC {
	public static <T> T getProxy(final Class<T> clazz, String host, int port) {
		final Client client = new Client(host, port);
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Invocation invo = new Invocation();// 初始化服务

				invo.setInterfaces(clazz);// 利用反射机制将java.lang.reflect.Method
											// 所代表的方法名,参数 封装到 Invocation invo对象中
				invo.setMethod(new core.protocal.Method(method.getName(),
						method.getParameterTypes()));
				invo.setParams(args);

				client.invoke(invo);

				return invo.getResult();// 获取结果
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
			System.out.println("启动服务器");
			/*
			 * server 启动时,需要Listener监听是否有client的请求连接 listener 是一个线程,由它来监听连接
			 */
			listener = new Listener(this);
			this.isRunning = true;
			listener.start();// listener 是一个线程类,start()后会执行线程的run方法
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
							invo.getMethod().getParams());// 調用的方法
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
