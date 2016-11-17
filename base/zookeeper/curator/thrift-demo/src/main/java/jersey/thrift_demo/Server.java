package jersey.thrift_demo;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import jersey.thrift_demo.service.HelloService;
import jersey.thrift_demo.service.HelloService.Processor;
import jersey.thrift_demo.service.impl.HelloServiceImpl;

public class Server {
    public static void main(String[] args) {
    	HelloServiceImpl handler = new HelloServiceImpl();
    	HelloService.Processor<HelloServiceImpl> processor = new Processor<HelloServiceImpl>(handler);
    	 try {
    	      TServerTransport serverTransport = new TServerSocket(9090);
    	      TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

    	      // Use this for a multithreaded server
    	      // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

    	      System.out.println("Starting the simple server...");
    	      server.serve();
    	    } catch (Exception e) {
    	      e.printStackTrace();
    	    }
	}
}
