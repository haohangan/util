package current.mb;

import java.util.concurrent.TimeUnit;

import sun.misc.Unsafe;

public class TestUnsafeThread {

	
	/**
	 * 本机并发
park和unpark方法允许您暂停线程一段时间并恢复它：
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
				System.out.println("开始暂停:"+start);
				unsafe.park(false, 1000000L*1000*5);//10000000000L=10s
				long end = System.currentTimeMillis();
				System.out.println("结束暂停:"+end);
				System.out.println("耗时："+((end-start)/1000.0));
				run[0] = true;
			}
		};
		t.start();
		unsafe.unpark(t);
		t.join(100L);
		System.out.println("结束:"+run[0]);
	}
}
