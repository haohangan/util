package current.pool;

public class ThreadRunnable extends Thread implements Runnable{

	@Override
	public void run() {
		System.out.println("aa");
	}

	public static void main(String[] args) {
		Thread t = new Thread(new ThreadRunnable());
		t.start();
	}
}
