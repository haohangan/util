package com.eva.rpc.io;

import com.eva.rpc.io.demo.AddAction;
import com.eva.rpc.io.demo.impl.AddAtionImpl;
import com.eva.rpc.io.protocols.PtotocalMap;

public class ServerStart {
	static int port = 8888;

	public static void main(String[] args) {
		try {
			PtotocalMap.INSTANCE.addProtocol(AddAction.class, new AddAtionImpl());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new RPCServer(port).run();
	}
}
