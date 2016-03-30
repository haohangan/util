package request;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月30日 下午2:40:41 类说明
 */
public class SyncRequest {
	public static StopWatch sw = new StopWatch();

	public static void main(String[] args) {

	}

	public static void getPic(int picnum) {
		sw.start();
		for (int i = 0; i < picnum; i++) {
          
		}
		sw.stop();
		System.out.println("耗时为：" + sw.getTime());
	}
}
