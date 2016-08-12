package com.eva.rpc.io.handler;

import com.eva.rpc.io.common.RPCRequestVO;
import com.eva.rpc.io.common.ResultVO;
import com.eva.rpc.io.protocols.PtotocalMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("channelRead:"+ctx.name());
		RPCRequestVO reqvo = (RPCRequestVO)msg;
//		ByteBuf in = (ByteBuf)msg;
//		byte[] b = null;
//		if (!in.hasArray()) {
//			int length = in.readableBytes();
//			b = new byte[length];
//			in.getBytes(in.readerIndex(), b);
//		}
//		RPCRequestVO reqvo = new RPCRequestVO();
//		reqvo.desrialize(b);
		Object resultObj = PtotocalMap.INSTANCE.invoke(reqvo);
		System.out.println(resultObj);
		ResultVO resultvo = new ResultVO();
		resultvo.setCode(reqvo.getNum());
		resultvo.setMsg("成功");
		resultvo.setSuccess(Boolean.TRUE);
		resultvo.setResult(resultObj);

//		ctx.writeAndFlush(Unpooled.copiedBuffer(resultvo.serialize()));
		ctx.writeAndFlush(resultvo);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelRegistered:"+ctx.name());
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelUnregistered:"+ctx.name());
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelActive:"+ctx.name());
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive:"+ctx.name());
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete:"+ctx.name());
		super.channelReadComplete(ctx);
		ctx.close();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("userEventTriggered:"+ctx.name());
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelWritabilityChanged:"+ctx.name());
		super.channelWritabilityChanged(ctx);
	}

}
