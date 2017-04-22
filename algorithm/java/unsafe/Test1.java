package current.mb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * �ڴ��Ż���ԭ���Ե���
 * AtomixXFieldUpdater������Atomic����ڴ��Ż��汾��
 * ��������API�ļ��������ȡ�ڴ�ռ�õ��Ż���
 * ͨ��������ĵ���ʵ������֧��ĳ����Ķ��ʵ����
 * �����ǵ�Test1�����У���������������volatile��
 * �ڶ��󴴽����棬���ַ�ʽ�ܹ����ɸ�Ϊ��Ч�Ĵ��롣ͬʱ�����updater��һ����̬��final��
 * ��������������record��ֻ��Ҫ��һ��updater�Ϳ����ˣ���������Ҫ���ǣ������ھ��ǿ��õġ�
 * ����֮�⣬������һ����֧�ֵĹ���API����ʼ��Ӧ������ѡ�Ĳ��ԡ�
 * ��������һ���棬���ǿ�һ��updater�Ĵ�����ʹ�÷�ʽ������Ȼ�ǳ���ª�����Ƿǳ��׶���̹��˵��ƾֱ�������������Ǹ���������
 * @author 976175665
 * @date 2017��4��22�� ����1:01:40
 */
public class Test1 {
	static final AtomicLongFieldUpdater<Test1> UPDATER = AtomicLongFieldUpdater.newUpdater(Test1.class, "time");
	private volatile long time;

	public Test1(long time) {
		super();
		this.time = time;
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Test1 t = new Test1(0);
		Add a1 = new Add(t);
		// Add a2 = new Add(t);
		executor.execute(a1);
		executor.execute(a1);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.SECONDS);
		System.out.println(executor);
		System.out.println(t.get());
	}

	static class Add implements Runnable {

		Test1 t;

		public Add(Test1 t) {
			super();
			this.t = t;
			System.out.println("init:" + t.get());
		}

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + i + "---" + t.get());
				t.update();
				System.out.println(Thread.currentThread().getName() + ":" + i + "---" + t.get());
			}
		}

	}

	long update() {
		return UPDATER.incrementAndGet(this);
	}

	long get() {
		return time;
	}
}
