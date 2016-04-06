package ti2.producer;

public class Consume implements Runnable {

	ResourceCache rc;

	public Consume(ResourceCache rc) {
		this.rc = rc;
	}

	/**
	 * 消费者线程
	 */
	@Override
	public void run() {
		try {
			for (int i = 0; i < 30; i++) {
				int wait = (int) (8000 * Math.random());
				Thread.sleep(wait);
				Resource pop = rc.pop();
				System.out.println("消费了：" + pop + ",等待时间为：" + wait);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
