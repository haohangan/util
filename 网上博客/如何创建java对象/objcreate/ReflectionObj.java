package objcreate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionObj {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		TestObject to = null;
		try {
			to = TestObject.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(to);
		
		TestObject to2 = null;
//		try {
////			to2 = TestObject.class.getConstructor(Number.class).newInstance(2);
//			
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Constructor<?>[] cons = TestObject.class.getConstructors();
		for(Constructor con:cons){
			Class[] classarr = con.getParameterTypes();
			for(Class clazz:classarr){
				System.out.print(clazz.getName()+"\t"+clazz.getTypeName()+"\t"+clazz.getSigners());
			}
			System.out.println();
			Object obj = con.newInstance(2);
			to2 = (TestObject)obj;
		}
		System.out.println(to2);
	}
}
