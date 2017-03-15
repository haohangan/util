package core.support;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import core.protocal.Invocation;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月18日 下午3:50:26 类说明
 */
public class Listener extends Thread {
	private ServerSocket serverSocket;
	private Server server;

	public Listener(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		System.out.println("启动服务器中，打开端口" + server.getPort());
		try {
			serverSocket = new ServerSocket(server.getPort());
		} catch (IOException e) {
			e.printStackTrace();
			return;// 直接退出
		}
		while (server.isRunning()) {
			System.out.println("等待请求");
			try {
				Socket client = serverSocket.accept();// 建立一条TCP连接
				System.out.println("请求到来");
				ObjectInputStream ois = new ObjectInputStream(
						client.getInputStream());
				Invocation invo = (Invocation) ois.readObject();

				System.out.println("远程调用:" + invo);

				server.call(invo);

				ObjectOutputStream oos = new ObjectOutputStream(
						client.getOutputStream());// 准备写回数据
				oos.writeObject(invo);
				oos.flush();
				oos.close();
				ois.close();

			} catch (Throwable th) {
				th.printStackTrace();
			}
		}

		// 关闭服务
		if (serverSocket != null && !serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
