package com.eva.rpc.nios.demo.obj;

import java.util.logging.Level;
import java.util.logging.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * demo 输出接收到的对象<br>
 * guihao <br>
 * ghao3@grgbanking.com<br>
 */
public class ObjectServerHandler extends ChannelInboundHandlerAdapter {

	final static Logger LOG = Logger.getGlobal();
	static {
		LOG.setLevel(Level.INFO);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channelActive");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channelInactive");
		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LOG.info("channelRead");
		System.out.println(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channelReadComplete");
		super.channelReadComplete(ctx);
		ctx.channel().close();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channelRegistered");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channelUnregistered");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channelWritabilityChanged");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOG.info("exceptionCaught");
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		LOG.info("userEventTriggered");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		LOG.info("handlerAdded");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		LOG.info("handlerRemoved");
		super.handlerRemoved(ctx);
	}

	@Override
	public boolean isSharable() {
		LOG.info("isSharable");
		return super.isSharable();
	}

}
