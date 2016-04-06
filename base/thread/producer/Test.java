package ti2.producer;

public class Test {
	public static void main(String[] args) {
		ResourceCache rc = new ResourceCache();
		Product p = new Product(rc);
		Consume c = new Consume(rc);
		new Thread(p).start();
		new Thread(c).start();
	}
}
