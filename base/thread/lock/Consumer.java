package current.cp;

public class Consumer implements Runnable{
	private Storage storage;
	
	public Consumer(Storage storage) {
		super();
		this.storage = storage;
	}


	@Override
	public void run() {
		for(int i = 0;i<10;i++){
			Resource r = storage.pop();
			System.out.println("消耗了一个资源:"+r);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("消费结束");
	}

}
