package com.eva.rpc.nios.demo.obj;

import javax.net.ssl.SSLException;

import com.eva.rpc.nios.client.Client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class ObjectClientStart {
	static final boolean SSL = Boolean.FALSE;

	public static void main(String[] args) throws SSLException {
		final SslContext sslCtx;
		if (SSL) {
			sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} else {
			sslCtx = null;
		}
		final String HOST = "127.0.0.1";// ,8888
		final int PORT = 8888;
		for (int i = 0; i < 10; i++) {
			final String t = "exe:" + i;
			new Client(HOST, PORT).run(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline cp = ch.pipeline();
					if (SSL) {
						cp.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
					}
					cp.addLast(new ObjectEncoder(), new ObjectClientHandler(t));
				}
			});
		}
	}
}
