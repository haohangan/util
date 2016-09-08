package com.eva.rpc.nios.demo.obj;

import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLException;

import com.eva.rpc.nios.rpc.common.ClassMap;
import com.eva.rpc.nios.rpc.demo.impl.DemoIntfImpl;
import com.eva.rpc.nios.server.Server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class ObjectServerStart {
	final static Logger LOG = Logger.getGlobal();
	static {
		LOG.setLevel(Level.SEVERE);
	}

	static final boolean SSL = Boolean.FALSE;

	public static void main(String[] args) throws CertificateException, SSLException {
		ClassMap.INSTANCE.register(DemoIntfImpl.class);
		
		final SslContext sslCtx;

		if (SSL) {
			LOG.info("enable SSL...");
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		} else {
			sslCtx = null;
		}

		final int port = 8888;
		new Server(port).run(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline cp = ch.pipeline();
				if (SSL) {
					cp.addLast(sslCtx.newHandler(ch.alloc()));
				}
				cp.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)), new ObjectEncoder(),
						new ObjectServerHandler());
			}

		});
	}
}
