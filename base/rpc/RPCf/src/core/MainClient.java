package core;

import core.intef.Echo;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月18日 下午5:14:06 类说明
 */
public class MainClient {
	public static void main(String[] args) {
		Echo echo = RPC.getProxy(Echo.class, "127.0.0.1", 20382);
		System.out.println(echo.echo("aaaaa"));// 使用代理对象调用服务器的服务.并将结果输出

		// AnotherEchoService otherEcho = RPC.getProxy(AnotherEchoService.class,
		// "127.0.0.1", 20382);
		// System.out.println(otherEcho.anotherEcho("hehhehehhhehehehhehehe"));
	}
}
