package ti2.producer2;

public class Producer implements Runnable {
	Storage cache;

	public Producer(Storage cache) {
		this.cache = cache;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 40; i++) {// 每秒生产一个
				cache.push(new Resource(i));
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
