package com.eva.rpc.io;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.eva.rpc.io.common.DelimiterUtil;
import com.eva.rpc.io.handler.ServerDecoder;
import com.eva.rpc.io.handler.ServerEncoder;
import com.eva.rpc.io.handler.ServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class RPCServer {
	static Logger logger = Logger.getGlobal();
	static {
		logger.setLevel(Level.INFO);
	}

	private int port;

	public RPCServer(int port) {
		super();
		this.port = port;
	}

	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap bootStrap = new ServerBootstrap();
		bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						// ch.pipeline().addLast(new ServerDecoder(), new
						// ServerHandler(), new ServerEncoder());
						// ch.pipeline().addLast(new ServerHandler(), new
						// ServerEncoder());
						// ch.pipeline().addLast("encoder", new
						// ServerEncoder()).addLast(new ServerHandler());
						ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
								Unpooled.copiedBuffer(DelimiterUtil.DELIMITER_BYTE)));
						ch.pipeline().addLast(new ServerDecoder());
						ch.pipeline().addLast(new ServerEncoder());
						ch.pipeline().addLast(new ServerHandler());

					}
				}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
		try {
			ChannelFuture cf = bootStrap.bind(port).sync();
			logger.info("启动成功");
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	// public static void main(String[] args) {
	// int port = 8888;
	// new RPCServer(port).run();
	// }
}
