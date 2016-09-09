package com.eva.rpc.nios.client;

import java.util.logging.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	final static Logger LOG = Logger.getGlobal();
	String HOST = "127.0.0.1";
	int PORT = 8888;

	public Client(String hOST, int pORT) {
		HOST = hOST;
		PORT = pORT;
	}

	public void run(ChannelInitializer<Channel> ci) {
		LOG.info("client starting...");
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class).handler(ci);
		try {
			bootstrap.connect(HOST, PORT).channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
		LOG.info("client stop!");
	}

}
