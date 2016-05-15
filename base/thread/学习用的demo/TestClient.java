package asyc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.time.StopWatch;

public class TestClient {
	static StopWatch sw = new StopWatch();
	
	/**
	 * 阻塞？同步？
	 * @param num
	 * @return
	 */
	public static int getSumData(int num) {
		int sum = 0;
		for (int i = 0; i < num; i++) {
			int data = TestServer.getData();
			System.out.println("获得data：" + data);
			sum += data;
		}
		return sum;
	}
	
	/**
	 * 模拟服务器
	 * @param args
	 */
	static class TestServer{
		public static int getData() {
			int time = (int) (Math.random() * 10);
			try {
				Thread.sleep(time * 1000);
			} catch (InterruptedException e) {
				System.out.println("服务器发生错误");
			}
			return time;
		}
	}

	public static void main(String[] args) {
		sw.start();
		// System.out.println(TestClient.getSumData(4));
		System.out.println("取数结果为："+TestClient.getSumData2(4));
		sw.stop();
		System.out.println("总共消耗时间为："+sw.getTime()/1000.0);
	}

	/**
	 * 异步？
	 * @param num
	 * @return
	 */
	public static int getSumData2(int num) {
		ExecutorService pool = Executors.newFixedThreadPool(num);
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		for (int i = 0; i < num; i++) {
			list.add(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int data = TestServer.getData();
					System.out.println("获得data：" + data);
					return data;
				}
			});
		}
		List<Future<Integer>> flist = null;
		try {
			flist = pool.invokeAll(list);
		} catch (InterruptedException e) {
		}
		int sum = 0;
		try {
			for (Future<Integer> f : flist) {
				sum += f.get(10, TimeUnit.SECONDS);
			}
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
		}
		pool.shutdown();
		return sum;
	}
}
