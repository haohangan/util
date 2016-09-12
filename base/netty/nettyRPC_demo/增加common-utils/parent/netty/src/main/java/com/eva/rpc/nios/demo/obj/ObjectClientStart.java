package com.eva.rpc.nios.demo.obj;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLException;

import com.eva.rpc.nios.client.Client;
import com.eva.rpc.nios.rpc.vo.RequestVO;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

public class ObjectClientStart {
	final static Logger LOG = Logger.getGlobal();
	static {
		LOG.setLevel(Level.SEVERE);
	}
	static final boolean SSL = Boolean.FALSE;

	public static void start(final String HOST, final int PORT, final RequestVO t) throws SSLException {
		final SslContext sslCtx;
		if (SSL) {
			LOG.info("enable SSL...");
			sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} else {
			sslCtx = null;
		}
		new Client(HOST, PORT).run(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline cp = ch.pipeline();
				if (SSL) {
					cp.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
				}
				cp.addLast(new ObjectEncoder(), new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
						new ObjectClientHandler(t));
			}
		});
	}
}
