package com.eva.rpc.nios.demo.obj;

import com.eva.rpc.nios.server.Server;

import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;

public class ObjectServerStart {
	public static void main(String[] args) {
		int port = 8888;
		new Server(port).run(new ObjectDecoder(ClassResolvers.cacheDisabled(null)),new ObjectServerHandler());
	}
}
