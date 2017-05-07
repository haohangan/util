package objcreate;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeObj {
	
	public static void main(String[] args) throws InstantiationException {
		Unsafe usafe = getUnsafe();
		TestObject to = (TestObject) usafe.allocateInstance(TestObject.class);//不调用构造函数
		System.out.println(to);
	}
	
	
	@SuppressWarnings("restriction")
	public static Unsafe getUnsafe(){
		Unsafe unsafe = null;
		try {
			Class<?> clazz = Unsafe.class;
			Field f;

			f = clazz.getDeclaredField("theUnsafe");

			f.setAccessible(true);
			unsafe = (Unsafe) f.get(clazz);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return unsafe;
	}
}
