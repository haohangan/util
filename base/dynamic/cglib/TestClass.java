
package simple.cglib;

public class TestClass {
	public static void main(String[] args) {
		Class<?> clazz1 = String.class;
		Class<?> clazz2 = String.class;
		System.out.println(clazz1 == clazz2);
		System.out.println(clazz1.equals(clazz2));
	}
}
