package ti2.blocktest;

public class Test {
   public static void main(String[] args) {
	   EnergyBarrier eb = new EnergyBarrier();
	   Producer p = new Producer(eb);
	   Consumer c = new Consumer(eb);
	   new Thread(p).start();
	   new Thread(c).start();
}
}
