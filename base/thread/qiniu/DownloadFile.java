package qiniu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年4月25日 下午3:30:26 类说明
 */
public class DownloadFile {
	static StopWatch sw = new StopWatch();
	static final String remote_base_url = "http://7xtc8z.com1.z0.glb.clouddn.com/";
	static final String local_base_url = "D:\\img\\";

	/*
	 * 第一次運行：耗时为：60786ms 第二次運行：耗时为：61310ms
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			run();
		}
	}

	public static void run() {
		int[] gifs = { 1, 2, 4, 5, 6, 7, 8, 9 };
		int[] jpgs = { 3 };
		sw.reset();
		sw.start();
		for (int i = 0; i < gifs.length; i++) {
			String name = gifs[i] + ".gif";
			String localPath = local_base_url + name;
			String remotePath = remote_base_url + name;
			download(remotePath, localPath);
		}

		for (int i = 0; i < jpgs.length; i++) {
			String name = jpgs[i] + ".jpg";
			String localPath = local_base_url + name;
			String remotePath = remote_base_url + name;
			download(remotePath, localPath);
		}
		sw.stop();
		System.out.println("耗时为：" + sw.getTime() + "ms");
		try {
			File local = new File(local_base_url);
			FileUtils.deleteDirectory(local);
			if (!local.exists()) {
				local.mkdirs();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
