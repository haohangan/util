package qiniu;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年4月25日 下午4:13:53
 * 类说明
 */
public class TimeTest {
	static StopWatch sw = new StopWatch();
	
	public static void main(String[] args) {
		sw.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sw.stop();
		System.out.println(sw.getTime());
	}
}
