package ti2.producer;

public class Product implements Runnable {
	ResourceCache rc;

	public Product(ResourceCache rc) {
		this.rc = rc;
	}

	/**
	 * 生产者线程
	 */
	@Override
	public void run() {
		int max = 100;// 每秒生成一个
		try {
			for (int i = 0; i < max; i++) {
				Resource r = new Resource(i);
				rc.push(r);
				System.out.println("生产了：" + r);

				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
