package com.eva.rpc.rpcdemo;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import com.eva.rpc.nios.demo.obj.ObjectServerStart;
import com.eva.rpc.nios.rpc.demo.impl.DemoIntfImpl;

public class DemoServer {
	public static void main(String[] args) throws CertificateException, SSLException {
		final int port = 8888;
		ObjectServerStart.start(port, DemoIntfImpl.class);
	}
}
