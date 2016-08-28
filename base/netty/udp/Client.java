package udp.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket client = new DatagramSocket();
		InetAddress ia = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		try {
			while (true) {
				String sentence = reader.readLine();
				sendData = sentence.getBytes();
				DatagramPacket sendpacket = new DatagramPacket(sendData, sendData.length, ia, 8888);
				client.send(sendpacket);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				client.receive(receivePacket);
				String receiveSentence = new String(receivePacket.getData());
				System.out.println("返回的数据:" + receiveSentence);
			}
		} finally {
			client.close();
		}
	}
}
