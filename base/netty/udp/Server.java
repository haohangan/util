package udp.test1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
	public static void main(String[] args) throws IOException {
		DatagramSocket server = new DatagramSocket(8888);
		System.out.println("开始。。。");
		try {
			byte[] receivedata = new byte[1024];
			byte[] senddata = new byte[1024];
			while (true) {
				DatagramPacket packet = new DatagramPacket(receivedata, receivedata.length);
				server.receive(packet);
				String sentence = new String(packet.getData());
				System.out.println("接收到的数据:" + sentence);
				InetAddress ia = packet.getAddress();
				int port = packet.getPort();
				String capiticalSentence = sentence.toUpperCase();
				senddata = capiticalSentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(senddata, senddata.length, ia, port);
				server.send(sendPacket);
			}
		} finally {
			server.close();
		}
	}

}
