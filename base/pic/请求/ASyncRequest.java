package c.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月31日 下午12:22:17 类说明
 */
public class ASyncRequest {
	static StopWatch sw = new StopWatch();
	static final String path = "http://localhost:8081/pic/pic.do?pid=";

	static final String zippath = "D:\\img\\a.zip";

	static class Req implements Callable<Integer> {
		private Integer num;
		private ZipOutputStream os;

		public Req(Integer num, ZipOutputStream os) {
			this.num = num;
			// try {
			// os = new ZipOutputStream(new FileOutputStream(new
			// File(zippath)));
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// }
			this.os = os;
		}

		@Override
		public Integer call() throws Exception {
			URL url = new URL(path + num);
			InputStream is = url.openStream();
			os.putNextEntry(new ZipEntry(num + ".jpg"));
			byte[] buff = new byte[1024];
			int flag = -1;
			while ((flag = is.read(buff)) != -1) {
				os.write(buff, 0, flag);
			}
			os.closeEntry();
			os.flush();
			is.close();
			return num;
		}
	}

	public void download() throws InterruptedException, ExecutionException,
			IOException {
		ExecutorService pool = Executors.newFixedThreadPool(20);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(
				pool);
		ZipOutputStream os = new ZipOutputStream(new FileOutputStream(new File(
				zippath)));
		for (int i = 0; i < 20; i++) {
			completionService.submit(new Req(i, os));
		}
		for (int i = 0; i < 20; i++) {
			// take 方法等待下一个结果并返回 Future 对象。
			// poll 不等待，有结果就返回一个 Future 对象，否则返回 null。
			System.out.println(completionService.take().get());
		}
		pool.shutdown();
		os.finish();
		os.close();
	}

	public static void main(String[] args) {
		sw.start();
		try {
			new ASyncRequest().download();
		} catch (InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
		}
		sw.stop();
		System.out.println(sw.getTime());
	}
}
