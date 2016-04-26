package qiniu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年4月26日 下午3:21:28 类说明
 */
public class MyThread implements Runnable {

	StopWatch sw = new StopWatch();
	static final String remote_url = "http://7xtc8z.com1.z0.glb.clouddn.com/";
	static final String local_url = "D:\\img\\";

	private String name;

	public MyThread(final String name) {
		this.name = name;
	}

	@Override
	public void run() {
		sw.start();
		try {
			URL url = new URL(remote_url + name);
			IOUtils.copy(url.openStream(), new FileOutputStream(new File(
					local_url + name)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sw.stop();
		System.out.println(name + " : " + sw.getTime());
	}

	public static void main(String[] args) {
		StopWatch sw = new StopWatch();
		sw.start();
		List<Runnable> rl = new ArrayList<Runnable>(9);
		for (int i = 1; i < 10; i++) {
			if (i == 3) {
				rl.add(new MyThread(i + ".jpg"));
				continue;
			}
			rl.add(new MyThread(i + ".gif"));
		}
		sw.stop();
		System.out.println("准备时间："+sw.getTime());
		for(int i = 0;i<rl.size();i++){
			new Thread(rl.get(i)).start();
		}
	}
}
