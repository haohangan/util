package com.eva.rpc.io.protocols;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.eva.rpc.io.common.RPCRequestVO;

public enum PtotocalMap {
	INSTANCE;

	private final ConcurrentMap<Class<?>, Object> map = new ConcurrentHashMap<>();

	public void addProtocol(Class<?> clazz, Object instance) throws Exception {
		if (ClassUtils.isSuit(clazz, instance.getClass())) {
			throw new Exception("实例与接口不符");
		}
		if (map.containsKey(clazz)) {
			map.remove(clazz);
		}
		map.put(clazz, instance);
	}

	public Object invoke(RPCRequestVO rvo) throws Exception {
		Class<?> clazz = rvo.getProtocal();
		if (!map.containsKey(clazz)) {
			throw new Exception("不存在此接口");
		}
		Object instance = map.get(clazz);
		Method method = clazz.getMethod(rvo.getMethod(), rvo.getParams());
		return method.invoke(instance, rvo.getValues());
	}
}
