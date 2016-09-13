package impl.reader;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import impl.demo.HelloWorldImpl;
import path.PackUtils;

public class ClassReader {
	static final String POINT = ".";
	static final String SEP = "/";

	@Deprecated
	public static Class<?>[] readAll() {
		Class<?>[] clazzs = new Class<?>[1];
		clazzs[0] = HelloWorldImpl.class;
		// 当前类的绝对路径
		System.out.println(ClassReader.class.getResource("/").getFile());
		// 指定CLASSPATH文件的绝对路径
		System.out.println(ClassReader.class.getResource("/impl/reader/..").getFile());

		return clazzs;
	}

	public static Class<?>[] readAll(String... paths) throws IOException {
		Set<Class<?>> classes = new HashSet<>();
		for (String path : paths) {
			classes.addAll(PackUtils.getClassFromPackage(path));
		}
		Class<?>[] clazzs = new Class<?>[classes.size()];
		return classes.toArray(clazzs);
	}
}
