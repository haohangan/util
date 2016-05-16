package algorithm.files;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.time.StopWatch;

public class ForkJoin {
	// if (my portion of the work is small enough)
	// do the work directly
	// else
	// split my work into two pieces
	// invoke the two pieces and wait for the results
	static final ForkJoinPool pool = new ForkJoinPool();
	static StopWatch sw = new StopWatch();
	// static List<Future<Long>> list = new ArrayList<Future<Long>>();

	static class Cal extends RecursiveTask<Long> {
		private static final long serialVersionUID = 1L;
		File file;

		public Cal(File file) {
			this.file = file;
		}

		@Override
		protected Long compute() {
			if (file.isFile()) {
				return file.getTotalSpace();
			}
			File[] fs = file.listFiles();
			long sum = 0;
			if (fs != null) {
				List<RecursiveTask<Long>> forks = new LinkedList<RecursiveTask<Long>>();
				for (File f : fs) {
					if (f.isFile()) {
						sum += f.getTotalSpace();
					} else {
						Cal rt = new Cal(f);
						forks.add(rt);
						rt.fork();
					}
				}
				for (RecursiveTask<Long> rt : forks) {
					try {
						sum += rt.get(10, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
					} catch (ExecutionException e) {
					} catch (TimeoutException e) {
					}
				}
			}
			return sum;
		}

	}

	public static void main(String[] args) {
		sw.start();
		long sum = pool.invoke(new Cal(new File(SizeCounter.path)));
		System.out.println("sum:" + sum);
		sw.stop();
		System.out.println("耗时：" + sw.getTime());
		// home
		// sum:101415362374250496
		// 耗时：1617
	}
}
