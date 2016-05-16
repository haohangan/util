package algorithm.files;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Je7Demo {
	static class Sum implements Callable<Long> {
		private final long from;
		private final long to;

		public Sum(long from, long to) {
			super();
			this.from = from;
			this.to = to;
		}

		@Override
		public Long call() throws Exception {
			long gcc = 0;
			for (long i = from; i <= to; i++) {
				gcc += i;
			}
			return gcc;
		}

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		List<Future<Long>> list = pool
				.invokeAll(Arrays.asList(new Sum(0, 10), new Sum(100, 1000), new Sum(10000, 100000)));
		Long sum = 0L;
		for(Future<Long> f : list){
			long count = f.get(10, TimeUnit.SECONDS);
			sum += count;
			System.out.println(count);
		}
		pool.shutdown();
		System.out.println("sum:"+sum);
	}
}
