package ti2.blocktest;

public class Consumer implements Runnable {
	private EnergyBarrier eb;

	public Consumer(EnergyBarrier eb) {
		this.eb = eb;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				eb.consume();
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
		}
		System.out.println("消费结束");		
	}

}
