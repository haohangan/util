package impl.reader;

import impl.demo.HelloWorldImpl;

public class ClassReader {
	public static Class<?>[] readAll() {
		Class<?>[] clazzs = new Class<?>[1];
		clazzs[0] = HelloWorldImpl.class;
		return clazzs;
	}
}
