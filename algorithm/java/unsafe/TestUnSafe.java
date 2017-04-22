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
	 * ���Թ��캯��
	 * Java����ʱ����������������ù��캯�������紴�������л����� ��ˣ� ReflectionFactory�ṩ����ķ��ʵ�������Ĵ����� 
	 */
	/**
	 * ���������ù��캯�������ʵ��
�ҵ�һ��ʹ��Unsafe����Ϊ�˴���һ�����ʵ�������������κ���Ĺ��캯����
 ����Ҫ����һ��ֻ��һ���൱���ӵĹ��캯���������࣬������ֻ�뽫���еķ�������ί�и�һ�����ڹ���ʱ��֪����ʵ����
  ����һ����������ף���������Ѿ���һ���ӿڱ�ʾ������һ��������һ��ֱ�ӵ�����
   ���Ű���Ľ����ߣ��ұ���ס�ˡ� ͨ��ʹ�á� Unsafe�࣬�ҿ������������������⡣ ����һ�����찺��Ľ����ߵĿγ̣�
	 */
	public static void unsafeInit(){
		Class<CASTest> cc = CASTest.class;
		Unsafe unsafe = getUnsafe();
		CASTest cast = null;
		try {
			cast = (CASTest) unsafe.allocateInstance(cc);//û��ʹ�ù��캯��:��������
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		System.out.println(cast.a+"\t"+cast.fieloffset+"\t"+cast.testField);
	}
	
	/**
	 * ����CAS
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
			System.out.println("�õ� filed�ڶ����е�ƫ�� :"+unsafe.objectFieldOffset(field));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	static class CASTest{
		volatile int a = 0;
		static long fieloffset;//�õ� filed�ڶ����е�ƫ�� :12   ���������˼��ֱ���޸��ڴ棬��ֱ666
		
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
	 * API���ܣ� 
boolean compareAndSwapInt(Object obj,long fieldoffset, int expect, int update); 
�޸� obj����ģ�fieldoffset��Int ����ֵ��������ֵΪexpect,���޸�Ϊ update ������true,������ֵ��Ϊexpect���޸ģ�����false 
boolean compareAndSwapObject(Object obj,long Fieldoffset, Object expect, Object update); 
�޸� obj����ģ�fieldoffset������ֵ��������ֵΪexpect,���޸�Ϊ update ������true,������ֵ��Ϊexpect���޸ģ�����false 
long objectFieldOffset (Field field); 
�õ� filed�ڶ����е�ƫ�� 
void park(boolean flag, long time); 
ʹ��ǰ�̵߳ȴ� 
void unpark(Thread  thread) 
ʹ��ǰ�߳�ֹͣ�ȴ� 
Object getObject(Object obj,long fieldoffset); 
�õ� obj �� ƫ��Ϊfieldoffset ������ 
int getInt(Object obj,long fieldoffset); 
�õ� obj �� ƫ��Ϊfieldoffset ��int����
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
