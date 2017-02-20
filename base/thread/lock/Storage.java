package current.cp;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
	private final static int MAX = 10;
	private LinkedList<Resource> list = new LinkedList<>();
	private Lock lock = new ReentrantLock();
	private Condition full = lock.newCondition();// ������
	private Condition empty = lock.newCondition();// ������

	public void push(Resource r) {
		lock.lock();
		try {
			while (list.size() == MAX) {
				System.out.println("����������Ե���������");
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
				System.out.println("����ؿգ��ȴ�����");
				empty.await();
			}
			r = list.pop();
			full.signalAll();//������һ����Դ�����Լ�������������
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return r;
	}
}
