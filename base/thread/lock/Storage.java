package current.cp;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
	private final static int MAX = 10;
	private LinkedList<Resource> list = new LinkedList<>();
	private Lock lock = new ReentrantLock();
	private Condition full = lock.newCondition();// 空条件
	private Condition empty = lock.newCondition();// 满条件

	public void push(Resource r) {
		lock.lock();
		try {
			while (list.size() == MAX) {
				System.out.println("缓冲池满，稍等再生产！");
				full.await();
			}
			list.add(r);
			empty.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			lock.unlock();
		}

	}

	public Resource pop() {
		Resource r = null;
		lock.lock();
		try {
			while (list.size() == 0) {
				System.out.println("缓存池空，等待生产");
				empty.await();
			}
			r = list.pop();
			full.signalAll();//消耗了一个资源，可以继续从事生产了
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return r;
	}
}
