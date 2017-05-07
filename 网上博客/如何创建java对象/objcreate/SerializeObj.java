package objcreate;

import java.io.IOException;

public class SerializeObj {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		TestObject to = new TestObject(4);
		byte[] buf = to.writeToArr();

		TestObject to2 = TestObject.readFromArr(buf);

		System.out.println(to2);
		System.out.println(to2 == to);
		System.out.println(to2.equals(to));
	}
}
