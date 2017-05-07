package objcreate;

public class CloneObj {
   public static void main(String[] args) {
	   TestObject to = new TestObject(3);
	   TestObject to2 = to.clone();
	   System.out.println(to2);
	   System.out.println(to2==to);
	   System.out.println(to2.equals(to));
}
}
