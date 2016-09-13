package server.start;

import java.io.IOException;
import java.security.cert.CertificateException;

import com.eva.rpc.nios.demo.obj.ObjectServerStart;

import impl.ImplUtils;

public class Start {
    public static void main(String[] args) throws CertificateException, IOException {
    	final int port = 8888;
		ObjectServerStart.start(port, ImplUtils.getImpls());
	}
}
