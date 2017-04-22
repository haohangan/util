package current.mb;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * 内存优化的原子性递增
 * AtomixXFieldUpdater是正常Atomic类的内存优化版本，
 * 它牺牲了API的简洁性来换取内存占用的优化。
 * 通过该组件的单个实例就能支持某个类的多个实例，
 * 在我们的Test1场景中，可以用它来更新volatile域。
 * 在对象创建方面，这种方式能够生成更为高效的代码。同时，这个updater是一个静态的final域，
 * 对于任意数量的record，只需要有一个updater就可以了，并且最重要的是，它现在就是可用的。
 * 除此之外，它还是一个受支持的公开API，它始终应该是优选的策略。
 * 不过，另一方面，我们看一下updater的创建和使用方式，它依然非常丑陋，不是非常易读，坦白说，凭直觉看不出来它是个计数器。
 * @author 976175665
 * @date 2017年4月22日 下午1:01:40
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
