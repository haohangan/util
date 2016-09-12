package impl;

import java.util.HashSet;
import java.util.Set;

import impl.reader.ClassReader;

/**
 * 对外公布的类：获取所有服务对象的class
 * 
 * @author Administrator
 *
 */
public class ImplUtils {
	public static Class<?>[] getImpls() {
		Set<String> set = new HashSet<>();
		set.add("impl.demo");
		set.add("impl.demo");
		return ClassReader.readAll(set.toArray(new String[set.size()]));
	}
	
	public static void main(String[] args) {
		System.out.println(getImpls()[1].getName());
	}
}
