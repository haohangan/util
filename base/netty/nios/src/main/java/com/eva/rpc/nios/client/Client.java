package com.eva.rpc.nios.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.eva.rpc.nios.server.StandChannelInit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	final static Logger LOG = Logger.getGlobal();
	static {
		LOG.setLevel(Level.INFO);
	}
	
	String HOST = "127.0.0.1";
	int PORT = 8888;

	public Client(String hOST, int pORT) {
		HOST = hOST;
		PORT = pORT;
	}

	public Client() {
	}

	public void run(ChannelHandler...ciha) {
		LOG.info("client starting...");
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class).handler(new StandChannelInit(ciha));
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
