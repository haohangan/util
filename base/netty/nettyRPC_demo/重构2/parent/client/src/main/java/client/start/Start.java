package client.start;

import javax.net.ssl.SSLException;

import org.springframework.util.StopWatch;

import com.eva.rpc.nios.demo.obj.ObjectClientStart;
import com.eva.rpc.nios.rpc.vo.RequestVO;

import intfs.demo.HelloWorld;

public class Start {
	static StopWatch sw = new StopWatch();

	public static void main(String[] args) throws SSLException {
		final String HOST = "127.0.0.1";// ,8888
		final int PORT = 8888;
		final RequestVO t = new RequestVO();
		t.setType(HelloWorld.class);
		t.setMethodName("value");
//		t.initKVS(String.class, "Tom");
		sw.start();
		for (int i = 0; i < 1; i++) {
			ObjectClientStart.start(HOST, PORT, t);
		}
		sw.stop();
		System.out.println("时间：" + sw.getTotalTimeMillis());
	}
}
