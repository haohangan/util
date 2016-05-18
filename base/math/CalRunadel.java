package math;

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

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年5月18日 下午1:45:53 类说明
 */
public class CalRunadel implements Callable<List<Long>> {

	static StopWatch sw = new StopWatch();
	// List<Long> arr = new ArrayList<Long>();
	final long start;
	final long end;

	public CalRunadel(final long start, final long end) throws Exception {
		if (end <= start) {
			throw new Exception("end <= start ,不合理！");
		}
		this.start = start;
		this.end = end;
	}

	@Override
	public List<Long> call() throws Exception {
		List<Long> arr = new ArrayList<Long>();
		for (long i = start; i <= end; i++) {
			long rtn = find(i);
			if (rtn == 1) {
				arr.add(rtn);
			}
		}
		return arr;
	}

	// public void run() {
	// for(long i = start;i<=end;i++){
	// long rtn = find(i);
	// if(rtn==1){
	// arr.add(rtn);
	// }
	// }
	// }

	/**
	 * 查找可以被num整除的数字
	 * 
	 * @param num
	 * @return
	 */
	long find(long num) {
		long zjs = squrt(num);
		for (long i = zjs; i > 0; i--) {
			if (num % i == 0) {
				return i;
			}
		}
		return 1L;
	}

	private long squrt(long num) {
		return (long) Math.sqrt((double) num);
	}

	public static long[] split(long max, int part) {
		long[] arr = new long[part + 1];
		long stepSize = max / part;
		// System.out.println("stepSize:"+stepSize);
		arr[0] = 0;
		for (int i = 1; i < part; i++) {
			arr[i] = arr[i - 1] + stepSize;
		}
		arr[part] = max;
		return arr;
	}
/**
 * 1000000以内的素数个数为：78499 消耗时间为：1515 ms
 * 
 * 10000000以内的素数个数为：664580 消耗时间为：48859 ms
 * @param args
 * @throws InterruptedException
 * @throws ExecutionException
 * @throws TimeoutException
 */
	public static void main(String[] args) throws InterruptedException,
			ExecutionException, TimeoutException {
		sw.start();
		int cores = Runtime.getRuntime().availableProcessors();
		long max = 10000000L;
		long[] arr = split(max, cores);
		for (long a : arr) {
			System.out.print(a + "\t");
		}
		System.out.println();
		ExecutorService pool = Executors.newFixedThreadPool(cores);
		List<Callable<List<Long>>> callList = new ArrayList<Callable<List<Long>>>();
		for (int i = 1; i < arr.length; i++) {
			long start = arr[i - 1] + 1;
			long end = arr[i];
			try {
				// list.add(pool.submit(new CalRunadel(start, end)));
				callList.add(new CalRunadel(start, end));
			} catch (Exception e) {
			}
		}
		List<Future<List<Long>>> list = pool.invokeAll(callList);
		List<Long> ll = new ArrayList<Long>();
		for (Future<List<Long>> f : list) {
			ll.addAll(f.get(10000, TimeUnit.SECONDS));
		}
		pool.shutdown();
		sw.stop();
		System.out.println(max + "以内的素数个数为：" + ll.size());
		System.out.println("\t\t消耗时间为：" + sw.getTime() + " ms");
	}

}
