package core;

import core.intef.Echo;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����5:14:06 ��˵��
 */
public class MainClient {
	public static void main(String[] args) {
		Echo echo = RPC.getProxy(Echo.class, "127.0.0.1", 20382);
		System.out.println(echo.echo("aaaaa"));// ʹ�ô��������÷������ķ���.����������

		// AnotherEchoService otherEcho = RPC.getProxy(AnotherEchoService.class,
		// "127.0.0.1", 20382);
		// System.out.println(otherEcho.anotherEcho("hehhehehhhehehehhehehe"));
	}
}
