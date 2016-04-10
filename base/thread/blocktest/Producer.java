package ti2.blocktest;

public class Producer implements Runnable {
	private EnergyBarrier eb;

	public Producer(EnergyBarrier eb) {
		this.eb = eb;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				eb.produce(new Resource(i));
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
		}
		System.out.println("生产结束");
	}

}
