package ti2.blocktest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EnergyBarrier {
	private BlockingQueue<Resource> queue = new ArrayBlockingQueue<Resource>(10);

	public void produce(final Resource r) {
		try {
			queue.put(r);
			System.out.println("生产-" + r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Resource consume() {
		if(queue.isEmpty()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Resource r = queue.poll();
		System.out.println("消费-" + r);
		return r;
	}
}
