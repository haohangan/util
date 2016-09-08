package com.eva.rpc.rpcdemo;

import javax.net.ssl.SSLException;

import org.springframework.util.StopWatch;

import com.eva.rpc.nios.demo.obj.ObjectClientStart;
import com.eva.rpc.nios.rpc.demo.DemoIntf;
import com.eva.rpc.nios.rpc.vo.RequestVO;

public class DemoClient {
	static StopWatch sw = new StopWatch();

	public static void main(String[] args) throws SSLException {
		final String HOST = "127.0.0.1";// ,8888
		final int PORT = 8888;
		final RequestVO t = new RequestVO();
		t.setType(DemoIntf.class);
		t.setMethodName("say");
		t.initKVS(String.class, "Tom");
		sw.start();
		for (int i = 0; i < 100; i++) {
			ObjectClientStart.start(HOST, PORT, t);
		}
		sw.stop();
		System.out.println("时间：" + sw.getTotalTimeMillis());
	}
}
