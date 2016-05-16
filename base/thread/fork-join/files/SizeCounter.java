package algorithm.files;

import java.io.File;

import org.apache.commons.lang3.time.StopWatch;

public class SizeCounter {
	static final String path = "/home";
	static StopWatch sw = new StopWatch();

	public static long count(File file) {
		if (file.isFile()) {
			return file.length();
		}
		File[] fs = file.listFiles();
		if (fs != null) {
			long sum = 0;
			for (File f : fs) {
				sum += count(f);
			}
			return sum;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		sw.start();
		System.out.println(count(new File(path)));//173945233408
		sw.stop();
		System.out.println("耗时："+sw.getTime());
		// home
		// 101414870899310592
		// 耗时：2434
	}
}
