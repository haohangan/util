package com.eva.rpc.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

import com.eva.rpc.io.common.RPCRequestVO;
import com.eva.rpc.io.demo.AddAction;

public class Demo {
	static final String host = "localhost";
	static final int port = 8888;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket(host, port);
		RPCRequestVO reqvo = new RPCRequestVO();
		reqvo.setNum(UUID.randomUUID().toString());
		reqvo.setProtocal(AddAction.class);
		reqvo.setMethod("hello");
		reqvo.setParams(new Class<?>[] { String.class });
		reqvo.setValues(new Object[] { "Tim" });
		OutputStream os = s.getOutputStream();
		os.write(reqvo.serialize());
		
		InputStream is = s.getInputStream();
		
		
		byte[] bytes = IOUtils.toByteArray(is);
		System.out.println(bytes.length);
//		List<Byte> list = new ArrayList<Byte>();
//		
//		int flag = -1;
//		byte[] buff = new byte[32];
//		while((flag=is.read(buff))!=-1){
////			list.addAll(Arrays.asList(buff));
//		}
//		while((flag = is.read())!=-1){
//			list.add((byte) flag);
//		}
//		is.close();
//		System.out.println(list.size());
//		ResultVO requltvo = new ResultVO();
//		requltvo.deserialize(bytes);
		os.close();
//		is.close();
		s.close();
//		System.out.println(requltvo);
	}
}
