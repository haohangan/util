package c.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月31日 下午1:10:29 类说明
 */
public class URLTEst {
	static StopWatch sw = new StopWatch();
	static final String path = "http://localhost:8081/pic/pic.do?pid=";
	static final String zippath = "D:\\img\\a.zip";

	public static void main(String[] args) throws IOException {
		int num = 20;
		sw.start();
		ZipOutputStream os = new ZipOutputStream(new FileOutputStream(new File(
				zippath)));
		try {
			for (int i = 0; i < num; i++) {
				URL url = new URL(path + i);
				download(url, os, i + ".jpg");
			}
		} finally {
			os.finish();
			os.close();
		}
		sw.stop();
		System.out.println(sw.getTime());
	}

	/**
	 * for循環執行20次時，用時爲：73497ms（1分14秒）
	 * 
	 * @param url
	 * @param os
	 * @param entryName
	 * @throws IOException
	 */
	public static void download(URL url, ZipOutputStream os, String entryName)
			throws IOException {
		InputStream is = null;
		try {
			is = url.openStream();
			byte[] buff = new byte[1024];
			int flag = -1;
			os.setComment("test");
			os.putNextEntry(new ZipEntry(entryName));
			while ((flag = is.read(buff)) != -1) {
				os.write(buff, 0, flag);
			}
			os.closeEntry();
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			is.close();
		}
	}

	// ZipOutputStream os = new ZipOutputStream(new FileOutputStream(new File(
	// zippath)));
	// int num1 = 1;
	// int num2 = 2;
	// URL url;
	// InputStream is = null;
	// try {
	// url = new URL(path + num1);
	// is = url.openStream();
	// byte[] buff = new byte[1024];
	// int flag = -1;
	// os.setComment("test");
	// os.putNextEntry(new ZipEntry("num" + num1 + ".jpg"));
	// while ((flag = is.read(buff)) != -1) {
	// // for (int i = 0; i < flag; i++) {
	// // System.out.print(buff[i]);
	// // }
	// os.write(buff, 0, flag);
	// }
	// os.closeEntry();
	// os.flush();
	//
	// url = new URL(path + num2);
	// is = url.openStream();
	// os.setComment("test2");
	// os.putNextEntry(new ZipEntry("num" + num2 + ".jpg"));
	// while ((flag = is.read(buff)) != -1) {
	// os.write(buff, 0, flag);
	// }
	// os.closeEntry();
	// os.flush();
	//
	// os.finish();
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } finally {
	// is.close();
	// os.close();
	// }
}
