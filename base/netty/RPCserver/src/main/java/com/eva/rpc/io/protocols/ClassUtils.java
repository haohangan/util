package com.eva.rpc.io.protocols;

public class ClassUtils {
	public static boolean isSuit(Class<?> intf, Class<?> son) {
		Class<?> iclass = son.getClass();
		if (iclass.equals(intf)) {
			return Boolean.TRUE;
		}
		Class<?>[] interfaces = iclass.getInterfaces();
		if (interfaces != null && interfaces.length > 0) {
			for (Class<?> clazz : interfaces) {
				if (clazz.equals(intf)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
}
