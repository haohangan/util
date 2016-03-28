package core.support;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import core.protocal.Invocation;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����3:50:26 ��˵��
 */
public class Listener extends Thread {
	private ServerSocket serverSocket;
	private Server server;

	public Listener(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		System.out.println("�����������У��򿪶˿�" + server.getPort());
		try {
			serverSocket = new ServerSocket(server.getPort());
		} catch (IOException e) {
			e.printStackTrace();
			return;// ֱ���˳�
		}
		while (server.isRunning()) {
			System.out.println("�ȴ�����");
			try {
				Socket client = serverSocket.accept();// ����һ��TCP����
				System.out.println("������");
				ObjectInputStream ois = new ObjectInputStream(
						client.getInputStream());
				Invocation invo = (Invocation) ois.readObject();

				System.out.println("Զ�̵���:" + invo);

				server.call(invo);

				ObjectOutputStream oos = new ObjectOutputStream(
						client.getOutputStream());// ׼��д������
				oos.writeObject(invo);
				oos.flush();
				oos.close();
				ois.close();

			} catch (Throwable th) {
				th.printStackTrace();
			}
		}

		// �رշ���
		if (serverSocket != null && !serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
