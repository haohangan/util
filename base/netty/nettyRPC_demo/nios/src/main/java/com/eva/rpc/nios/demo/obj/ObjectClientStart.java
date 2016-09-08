package com.eva.rpc.nios.demo.obj;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLException;

import org.springframework.util.StopWatch;

import com.eva.rpc.nios.client.Client;
import com.eva.rpc.nios.rpc.demo.DemoIntf;
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
	static StopWatch sw = new StopWatch();

	public static void main(String[] args) throws SSLException {
		final SslContext sslCtx;
		if (SSL) {
			LOG.info("enable SSL...");
			sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} else {
			sslCtx = null;
		}
		final String HOST = "127.0.0.1";// ,8888
		final int PORT = 8888;
		sw.start();
//		int i = 0;
//		for (; i < 10; i++) {
//			final String t = "exe:" + i;
		final RequestVO t= new RequestVO();
		t.setType(DemoIntf.class);
		t.setMethodName("say");
		t.initKVS(String.class, "Tom");
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
//		}
		sw.stop();
		System.out.println("时间：" + sw.getTotalTimeMillis());
	}
}
