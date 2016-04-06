package ti2.producer2;

public class Consumer implements Runnable {
	Storage cache;

	public Consumer(Storage cache) {
		this.cache = cache;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 40; i++) {
				int wait = (int) (3000 * Math.random());// 一定时间内消费一个
				Thread.sleep(wait);
				Resource r = cache.pop();
				System.out.println("消費了：" + r + "，時間爲：" + wait);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
