package com.eva.rpc.nios.demo.obj;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import com.eva.rpc.nios.server.Server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class ObjectServerStart {
	static final boolean SSL = Boolean.FALSE;

	public static void main(String[] args) throws CertificateException, SSLException {
		final SslContext sslCtx;

		if (SSL) {
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
				cp.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)), new ObjectServerHandler());
			}

		});
	}
}
