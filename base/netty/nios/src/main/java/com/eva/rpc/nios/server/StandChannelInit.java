package com.eva.rpc.nios.server;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * 
 * 通用型<br>
 * guihao <br>
 * ghao3@grgbanking.com<br>
 */
public class StandChannelInit extends ChannelInitializer<Channel> {
	static final boolean SSL = Boolean.FALSE;

	List<ChannelHandler> list = new ArrayList<>();

	public StandChannelInit(ChannelHandler...channelHandlers) {
		for(ChannelHandler ch: channelHandlers){
			list.add(ch);
		}
	}

	@Override
	protected void initChannel(Channel arg0) throws Exception {
		ChannelPipeline cp = arg0.pipeline();
		if (SSL) {
			SslContext sslCtx = null;
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
			cp.addLast(sslCtx.newHandler(arg0.alloc()));
		}
		for (ChannelHandler ciha : list) {
			cp.addLast(ciha);
		}
	}

}
