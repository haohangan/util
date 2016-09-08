package server.start;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import com.eva.rpc.nios.demo.obj.ObjectServerStart;

import impl.reader.ClassReader;

public class Start {
    public static void main(String[] args) throws CertificateException, SSLException {
    	final int port = 8888;
		ObjectServerStart.start(port, ClassReader.readAll());
	}
}
