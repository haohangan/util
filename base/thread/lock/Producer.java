package current.cp;

/**
 * 生产者对象
 * @author 976175665
 * @date 2017年2月20日 下午12:09:39
 */
public class Producer implements Runnable{
	
	private Storage storage;
	

	public Producer(Storage storage) {
		super();
		this.storage = storage;
	}



	@Override
	public void run() {
		for(int i = 0;i<10;i++){//每秒钟生产一个资源
			Resource r = new Resource("name::"+i);
			storage.push(r);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("生产停止");
	}

}
