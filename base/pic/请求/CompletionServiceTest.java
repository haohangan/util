package ti2.readpic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.time.StopWatch;

public class CompletionServiceTest {
	public static final int BUFFER = 1024;
	static StopWatch sw = new StopWatch();
	public static final String toPath = "D:\\images\\download\\";
	public static final String subfix = ".jpg";
	public static final String zipPath = "D:\\images\\a.zip";
	public static final String reqURL = "http://localhost:8088/pic/pic.do?pid=";

	class ReqFile implements Callable<File> {
		Integer num;

		public ReqFile(Integer num) {
			this.num = num;
		}

		@Override
		public File call() throws Exception {
			URL url = new URL(reqURL + num);
			File file = new File(toPath + num + subfix);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			InputStream is = url.openStream();
			OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
			try {
				byte[] buff = new byte[BUFFER];
				int flag = -1;
				while ((flag = is.read(buff)) != -1) {
					os.write(buff, 0, flag);
				}
				os.flush();
			} finally {
				is.close();
				os.close();
			}
			return file;
		}
	}

	public void zip(int num) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(num);
		CompletionService<File> comService = new ExecutorCompletionService<File>(pool);
		for (int i = 0; i < num; i++) {
			comService.submit(new ReqFile(i));
		}
		for (int i = 0; i < num; i++) {
			System.out.println(comService.take().get().getName());//3065
//			System.out.println(comService.poll().get().getName());
		}
		pool.shutdown();
	}

	public static void main(String[] args) {
		sw.start();
		try {
			new CompletionServiceTest().zip(20);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		sw.stop();
		System.out.println(sw.getTime());
	}
}
