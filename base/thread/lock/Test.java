package current.cp;

public class Test {
   public static void main(String[] args) {
	   Storage storage = new Storage();
	   
	   Consumer c = new Consumer(storage);
	   Producer p = new Producer(storage);
	   
	   new Thread(c).start();
	   new Thread(p).start();
}
}
