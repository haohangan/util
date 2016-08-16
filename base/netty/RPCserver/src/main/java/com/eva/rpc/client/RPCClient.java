package com.eva.rpc.client;

import com.eva.rpc.io.common.DelimiterUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class RPCClient {
	public static void main(String[] args) {
		EventLoopGroup workGroup = new NioEventLoopGroup();
		Bootstrap bootStrap = new Bootstrap();
		bootStrap.group(workGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
								Unpooled.copiedBuffer(DelimiterUtil.DELIMITER_BYTE)));
						ch.pipeline().addLast(new RPCClientDecoder());
						ch.pipeline().addLast(new RPCClietHandler());
					}
				});

		try {
			ChannelFuture f = bootStrap.connect("localhost", 8888).sync();

			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workGroup.shutdownGracefully();
		}
	}

}
