package ti2.producer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceCache {
	private final static int MAX = 6;
	private LinkedList<Resource> cache = new LinkedList<Resource>();
	private Lock monitor = new ReentrantLock();
	private Condition full = monitor.newCondition();
	private Condition empty = monitor.newCondition();

	public void push(Resource resource) {
		monitor.lock();
		try {
			while (cache.size() == MAX) {
				System.out.println("缓冲区是已满：稍等再添加");
				full.await();
			}
			cache.add(resource);
			empty.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			monitor.unlock();
		}
	}

	public Resource pop() {
		monitor.lock();
		Resource rtn = null;
		try {
			while (cache.size() == 0) {
				System.out.println("缓冲区是为空：稍等再取");
				empty.await();
			}
			rtn = cache.pop();
			full.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			monitor.unlock();
		}
		return rtn;
	}
}
