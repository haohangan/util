package ti2.producer2;

public class Test {
	public static void main(String[] args) {
		Storage st = new Storage();

		Producer p = new Producer(st);
		Consumer c = new Consumer(st);

		new Thread(p).start();
		new Thread(c).start();
	}
}
