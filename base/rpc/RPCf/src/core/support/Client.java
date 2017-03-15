package core.support;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import core.protocal.Invocation;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����3:26:44
 * @desc �൱��"�ͻ����",����װRPC�ĵײ㴫�䣺�������ӵĽ��������л�(��������л�ͨ��Socket���ӷ�װ��) Client.java
 *       ��invoke�������ڴ����б�����,����Invocation ���󱣴���Ҫ���õ�Զ�̷������Ͳ���
 */
public class Client {
	private String host;
	private int port;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public Client(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}

	/**
	 * ���� client --- server ������
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void init() throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		oos = new ObjectOutputStream(socket.getOutputStream());
	}

	public void invoke(Invocation invo) throws UnknownHostException,
			IOException, ClassNotFoundException {
		init();
		System.out.println("д������");
		oos.writeObject(invo);// ��Client ��Ҫ���õ�Server�� �ӿڡ����������� ��װ���� ����������
		oos.flush();
		ois = new ObjectInputStream(socket.getInputStream());// �������մ� server ����
																// ������ִ�н�� ��������
		Invocation result = (Invocation) ois.readObject();
		invo.setResult(result.getResult());// ����� ���浽 Invocation result������
	}
}
