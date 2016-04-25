package qn.qiniu.config;

import com.qiniu.util.Auth;

public class ConfigAuth {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	public static final String ACCESS_KEY = "l4nFQ0ecpvdEWsG5aE-fTEv--ks7mVhk1IMqT1_8";

	public static final String SECRET_KEY = "2Y_JzN1Pjo8TEEq41n4nvR1FYLqq4mRLXoHk73RM";
	// 要空间
	public static final String bucketname = "demo";

	// 构造私有空间的需要生成的下载的链接
	public static final String BASE_URL = "http://7xtc8z.com1.z0.glb.clouddn.com/";

	static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

	/**
	 * 调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
	 * 
	 * @param key
	 * @return
	 */
	public static String createPrivateUrl(String key) {
		return auth.privateDownloadUrl(BASE_URL + key, 3600);
	}

	/**
	 * 调用privateDownloadUrl方法生成下载链接
	 * 
	 * @param key
	 * @param time
	 *            设置Token的过期时间
	 * @return
	 */
	public static String createPrivateUrl(String key, long time) {
		return auth.privateDownloadUrl(BASE_URL + key, time);
	}
}
