package qiniu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年4月25日 下午4:09:00 类说明
 */
public class EachPic {
	static StopWatch sw = new StopWatch();
	static final String remote_base_url = "http://7xtc8z.com1.z0.glb.clouddn.com/";
	static final String local_base_url = "D:\\img\\";

	public static void main(String[] args) {
		int[] gifs = { 1, 2, 4, 5, 6, 7, 8, 9 };
		int[] jpgs = { 3 };
		long all = 0L;
		for (int i = 0; i < gifs.length; i++) {
			sw.reset();
			sw.start();
			String name = gifs[i] + ".gif";
			String localPath = local_base_url + name;
			String remotePath = remote_base_url + name;
			download(remotePath, localPath);
			sw.stop();
			long time = sw.getTime();
			System.out.println("圖片 " + name + " 耗时为：" + time + "ms");
			all = all + time;
		}

		for (int i = 0; i < jpgs.length; i++) {
			sw.reset();
			sw.start();
			String name = jpgs[i] + ".jpg";
			String localPath = local_base_url + name;
			String remotePath = remote_base_url + name;
			download(remotePath, localPath);
			sw.stop();
			long time = sw.getTime();
			System.out.println("圖片 " + name + " 耗时为：" + time + "ms");
			all = all + time;
		}
		System.out.println("總共耗時：" + all);
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
/*
圖片 1.gif 耗时为：4783ms
圖片 2.gif 耗时为：1105ms
圖片 4.gif 耗时为：7183ms
圖片 5.gif 耗时为：3446ms
圖片 6.gif 耗时为：8923ms
圖片 7.gif 耗时为：13209ms
圖片 8.gif 耗时为：7057ms
圖片 9.gif 耗时为：9071ms
圖片 3.jpg 耗时为：31ms
總共耗時：54808*/
