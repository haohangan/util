package current.cp;

/**
 * �����߶���
 * @author 976175665
 * @date 2017��2��20�� ����12:09:39
 */
public class Producer implements Runnable{
	
	private Storage storage;
	

	public Producer(Storage storage) {
		super();
		this.storage = storage;
	}



	@Override
	public void run() {
		for(int i = 0;i<10;i++){//ÿ��������һ����Դ
			Resource r = new Resource("name::"+i);
			storage.push(r);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("����ֹͣ");
	}

}
