package c.t;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年4月19日 上午10:27:50 类说明
 */
public class Dengdai {
	static StopWatch sw = new StopWatch();

	public static void main(String[] args) throws InterruptedException {
		sw.start();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(i + " 等待1秒"
							+ Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		// t.join();
		t.join();
		sw.stop();
		System.out.println("用时为：" + sw.getTime());
	}
}
