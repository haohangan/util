package algorithm.zs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;

public class BasicFinder {
	static StopWatch sw = new StopWatch();

	final long max;

	public BasicFinder(final long max) {
		this.max = max;
	}

	private long find(long num) {
		long zjs = squrt(num);
		for (long i = zjs; i > 0; i--) {
			if (num % i == 0) {
				return i;
			}
		}
		return 1;
	}

	private long squrt(long num) {
		return (long) Math.sqrt((double) num);
	}

	public List<Long> listZSinMax() {
		List<Long> list = new ArrayList<Long>();
		for (long i = 1; i <= max; i++) {
			long rtn = find(i);
			if (rtn == 1) {
				list.add(i);
			}
		}
		return list;
	}

	/**
	 * 1000000以内的素数个数为：78499 消耗时间为：4617 ms
	 * 
	 * 10000000以内的素数个数为：664580 消耗时间为：153108 ms
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long max = 10000000;
		BasicFinder bf = new BasicFinder(max);
		sw.start();
		List<Long> list = bf.listZSinMax();
		sw.stop();
		System.out.println(max + "以内的素数个数为：" + list.size());
		System.out.println("\t\t消耗时间为：" + sw.getTime() + " ms");
		// writeToFile(list,max,sw.getTime());
	}

	public static void writeToFile(List<Long> list, long max, long time) {
		File file = new File("/home/zs");
		Writer writer = null;
		try {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				Long l = list.get(i);
				sb.append(l);
				sb.append("\t\t");
				if (i % 10 == 0) {
					sb.append("\r\n");
				}
			}
			sb.append("\r\n");
			sb.append(max + "以内的素数个数为：" + list.size());
			sb.append("\t\t消耗时间为：" + time + " ms");
			writer = new FileWriter(file);
			writer.write(sb.toString());
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
			}
		}
	}
}
