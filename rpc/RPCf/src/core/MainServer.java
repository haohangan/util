package core;

import core.intef.AnotherEchoService;
import core.intef.AnotherEchoServiceImpl;
import core.intef.Echo;
import core.intef.RemoteEcho;
import core.support.Server;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����5:12:19
 * ��˵��
 */
public class MainServer {
  public static void main(String[] args) {
	Server server = new RPC.RPCServer();
	
	server.register(Echo.class, RemoteEcho.class);
	server.register(AnotherEchoService.class, AnotherEchoServiceImpl.class);
	server.start();
}
}
