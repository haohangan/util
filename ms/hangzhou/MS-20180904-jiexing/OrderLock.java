package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderLock {
	
	
	
	
	public static void main(String[] args) {
//		demo();
		demo2();
	}
	
	
	
	
	static Lock lock = new ReentrantLock();
	static Condition condition1 = lock.newCondition();
	static Condition condition2 = lock.newCondition();
	static Condition condition3 = lock.newCondition();
	static  int currentCondition = 1;
	
	public static void demo2() {
		new Thread(()->{
			try {
				lock.lock();
				while(true) {
					while(currentCondition!=1) {
						condition1.await();
					}
					System.out.println(i++);
					currentCondition = 1;
					TimeUnit.SECONDS.sleep(1);
					condition2.signal();
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
			
		}).start();
		
		new Thread(()->{
			try {
				lock.lock();
				while(true) {
					while(currentCondition!=2) {
						condition2.await();
					}
					System.out.println(i++);
					currentCondition = 3;
					TimeUnit.SECONDS.sleep(1);
					condition3.signal();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}).start();
		
		new Thread(()->{
			try {
				lock.lock();
				while(true) {
					while(currentCondition!=3) {
						condition3.await();
					}
					System.out.println(i++);
					currentCondition = 1;
					TimeUnit.SECONDS.sleep(1);
					condition1.signal();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}).start();
	}
	
	
	static Object monitor = new Object();
	static volatile int i = 0;
	public static void demo() {
		new Thread(()->{
			synchronized(monitor) {
				while(true) {
					System.out.println(i);
					i++;
					monitor.notifyAll();
					try {
						monitor.wait();
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(()->{
			synchronized(monitor) {
				while(true) {
					System.out.println(i);
					i++;
					monitor.notifyAll();
					try {
						monitor.wait();
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
