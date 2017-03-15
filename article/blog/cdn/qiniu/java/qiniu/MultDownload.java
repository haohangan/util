package qiniu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年4月25日 下午3:50:29 类说明
 */
public class MultDownload {
	static StopWatch sw = new StopWatch();
	static final String remote_base_url = "http://7xtc8z.com1.z0.glb.clouddn.com/";
	static final String local_base_url = "D:\\img\\";

	/**
	 * @第一次 ：耗时为：43482ms
	 * @第二次 ：耗时为：44065ms
	 * @第三次 ：耗时为：43537ms
	 * @第四次 ：耗时为：43651ms，開了三個綫程
	 * @第五次 ： 耗时为：42067ms，開了兩個綫程
	 * @第六次 ： 耗时为：40983ms，開了一個綫程
	 * @param args
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		int[] gifs = { 1, 2, 4, 5, 6, 7, 8, 9 };
		int[] jpgs = { 3 };
		sw.start();
		// int core = Runtime.getRuntime().availableProcessors();
		// double cs = 0.9D;
		// int threadNum = (int) (core/(1-cs));
		ExecutorService pool = Executors.newFixedThreadPool(1);
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		for (int i = 0; i < gifs.length; i++) {
			String name = gifs[i] + ".gif";
			final String localPath = local_base_url + name;
			final String remotePath = remote_base_url + name;
			list.add(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return IOUtils.copy(new URL(remotePath).openStream(),
							new FileOutputStream(new File(localPath)));
				}
			});
		}

		for (int i = 0; i < jpgs.length; i++) {
			String name = jpgs[i] + ".jpg";
			final String localPath = local_base_url + name;
			final String remotePath = remote_base_url + name;
			list.add(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return IOUtils.copy(new URL(remotePath).openStream(),
							new FileOutputStream(new File(localPath)));
				}
			});
		}
		List<Future<Integer>> flist = pool.invokeAll(list);
		for (Future<Integer> f : flist) {
			System.out.println(f.get());
		}
		pool.shutdown();
		sw.stop();
		System.out.println("耗时为：" + sw.getTime() + "ms");
	}

	public static void download(String url, String localPath) {
		try {
			IOUtils.copy(new URL(url).openStream(), new FileOutputStream(
					new File(localPath)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
