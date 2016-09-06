package com.eva.rpc.nios.demo.obj;

import com.eva.rpc.nios.client.Client;

import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectClientStart {
    public static void main(String[] args) {
		new Client("127.0.0.1",8888).run(new ObjectEncoder(),new ObjectClientHandler());;
	}
}
