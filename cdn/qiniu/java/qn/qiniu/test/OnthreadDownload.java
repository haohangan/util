package qn.qiniu.test;

import org.apache.commons.lang3.time.StopWatch;

import qn.qiniu.config.ConfigAuth;
import qn.qiniu.opt.DownloadUtils;

public class OnthreadDownload {// http://7xtc8z.com1.z0.glb.clouddn.com/9.gif
	static StopWatch sw = new StopWatch();
	static String baseURL = "http://7xtc8z.com1.z0.glb.clouddn.com/";

	public static void main(String[] args) {// 耗時爲：33346
		int[] gifs = { 1, 2, 4, 5, 6, 7, 8, 9 };
		int[] jpgs = { 3 };
		String path = "/home/ghao/download/";
		sw.start();
		for (int i = 0; i < gifs.length; i++) {
			String name = gifs[i] + ".gif";
			String url = ConfigAuth.createPrivateUrl(name);
			// String url = baseURL + name;
			DownloadUtils.download(url, path + name);
		}
		for (int i = 0; i < jpgs.length; i++) {
			String name = jpgs[i] + ".jpg";
			String url = ConfigAuth.createPrivateUrl(name);
			// String url = baseURL + name;
			DownloadUtils.download(url, path + name);
		}
		sw.stop();
		System.out.println("耗時爲：" + sw.getTime());
	}
}
