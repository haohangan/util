package deadlock;

public class ResUser implements Runnable {
	Resource r1;
	Resource r2;

	public ResUser(Resource r1, Resource r2) {
		super();
		this.r1 = r1;
		this.r2 = r2;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (r1) {
				System.out.println(Thread.currentThread().getName() + "获取了锁" + r1.i);
				synchronized (r2) {
					System.out.println(Thread.currentThread().getName() + "获取了锁" + r2.i);
				}
			}
		}
	}

}
