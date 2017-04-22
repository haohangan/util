package current.mb;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class TestUnSafe {

	// private static final Unsafe unsafe = Unsafe.getUnsafe();

	public static void main(String[] args) {
		testCAS();
//		unsafeInit();
	}
	
	/**
	 * 测试构造函数
	 * Java运行时本身创建对象而不调用构造函数，例如创建反序列化对象。 因此， ReflectionFactory提供更多的访问单个对象的创建： 
	 */
	/**
	 * 创建不调用构造函数的类的实例
我第一次使用Unsafe类是为了创建一个类的实例，而不调用任何类的构造函数。
 我需要代理一个只有一个相当嘈杂的构造函数的整个类，但是我只想将所有的方法调用委托给一个我在构建时不知道的实例。
  创建一个子类很容易，如果该类已经被一个接口表示，创建一个代理将是一个直接的任务。
   随着昂贵的建设者，我被卡住了。 通过使用“ Unsafe类，我可以用它来解决这个问题。 考虑一个人造昂贵的建设者的课程：
	 */
	public static void unsafeInit(){
		Class<CASTest> cc = CASTest.class;
		Unsafe unsafe = getUnsafe();
		CASTest cast = null;
		try {
			cast = (CASTest) unsafe.allocateInstance(cc);//没有使用构造函数:创建对象
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		System.out.println(cast.a+"\t"+cast.fieloffset+"\t"+cast.testField);
	}
	
	/**
	 * 测试CAS
	 */
	public static void testCAS(){
		Unsafe unsafe = getUnsafe();
		CASTest cast = new CASTest(0);
		System.out.println(cast);
		boolean f = unsafe.compareAndSwapInt(cast, cast.fieloffset, 0, 1);
		System.out.println(f+"\t\t"+cast.fieloffset);
		System.out.println("cast:"+cast);
		System.out.println("testField:"+cast.testField);
		
		try {
			Field field = CASTest.class.getDeclaredField("a");
			System.out.println(field.get(cast));
			System.out.println("得到 filed在对象中的偏移 :"+unsafe.objectFieldOffset(field));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	static class CASTest{
		volatile int a = 0;
		static long fieloffset;//得到 filed在对象中的偏移 :12   就是这个意思，直接修改内存，简直666
		
		final long testField;
		
		static {
	        try {
	        	fieloffset = getUnsafe().objectFieldOffset
	                (CASTest.class.getDeclaredField("a"));
	        } catch (Exception ex) { throw new Error(ex); }
	    }

		public CASTest(int a) {
			super();
			System.out.println("init CASTest");
			this.a = a;
			testField = 100L;
		}

		@Override
		public String toString() {
			return Integer.toString(a);
		}
		
	}
	
	/**
	 * API介绍： 
boolean compareAndSwapInt(Object obj,long fieldoffset, int expect, int update); 
修改 obj对象的（fieldoffset）Int 属性值，若属性值为expect,则修改为 update ，返回true,若属性值不为expect则不修改，返回false 
boolean compareAndSwapObject(Object obj,long Fieldoffset, Object expect, Object update); 
修改 obj对象的（fieldoffset）属性值，若属性值为expect,则修改为 update ，返回true,若属性值不为expect则不修改，返回false 
long objectFieldOffset (Field field); 
得到 filed在对象中的偏移 
void park(boolean flag, long time); 
使当前线程等待 
void unpark(Thread  thread) 
使当前线程停止等待 
Object getObject(Object obj,long fieldoffset); 
得到 obj 的 偏移为fieldoffset 的属性 
int getInt(Object obj,long fieldoffset); 
得到 obj 的 偏移为fieldoffset 的int属性
	 * @return
	 */
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
