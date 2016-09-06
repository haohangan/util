package com.eva.rpc.nios.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 
 * 通用的server for netty<br>
 * guihao <br>
 * ghao3@grgbanking.com<br>
 */
public class Server {
	final static Logger LOG = Logger.getGlobal();
	static {
		LOG.setLevel(Level.INFO);
	}
	int port;

	public Server(int port) {
		super();
		this.port = port;
	}

	public void run(ChannelInitializer<Channel> ci) {
		LOG.info("Server starting...");
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap bootStrap = new ServerBootstrap();
		bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
				.handler(new LoggingHandler(LogLevel.INFO)).childHandler(ci);
		try {
			ChannelFuture cf = bootStrap.bind(port).sync();
			cf.channel().closeFuture().sync();
			LOG.info("Server start success！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public void run(ChannelHandler...ciha) {
		LOG.info("Server starting...2");
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap bootStrap = new ServerBootstrap();
		bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
				.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new StandChannelInit(ciha));
		try {
			ChannelFuture cf = bootStrap.bind(port).sync();
			cf.channel().closeFuture().sync();
			LOG.info("Server start success！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
