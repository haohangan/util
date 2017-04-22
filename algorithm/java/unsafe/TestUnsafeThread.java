package current.mb;

import java.util.concurrent.TimeUnit;

import sun.misc.Unsafe;

public class TestUnsafeThread {

	
	/**
	 * ��������
park��unpark������������ͣ�߳�һ��ʱ�䲢�ָ�����
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Unsafe unsafe = TestUnSafe.getUnsafe();
		final boolean [] run = new boolean [1];
		Thread t = new Thread(){
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				System.out.println("��ʼ��ͣ:"+start);
				unsafe.park(false, 1000000L*1000*5);//10000000000L=10s
				long end = System.currentTimeMillis();
				System.out.println("������ͣ:"+end);
				System.out.println("��ʱ��"+((end-start)/1000.0));
				run[0] = true;
			}
		};
		t.start();
		unsafe.unpark(t);
		t.join(100L);
		System.out.println("����:"+run[0]);
	}
}
