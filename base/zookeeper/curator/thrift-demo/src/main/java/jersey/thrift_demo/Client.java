package jersey.thrift_demo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import jersey.thrift_demo.service.HelloService;

public class Client {
	public static void main(String[] args) throws TException {
		TTransport transport = new TSocket("localhost", 9090);
		transport.open();
		TProtocol protocol = new TBinaryProtocol(transport);
		HelloService.Client client = new HelloService.Client(protocol);

		perform(client);

		transport.close();
	}

	private static void perform(HelloService.Client client) throws TException {
		client.sayVoid();
		client.sayInt(90);
		client.sayString("str");
		client.sayBoolean(true);
	}
}
