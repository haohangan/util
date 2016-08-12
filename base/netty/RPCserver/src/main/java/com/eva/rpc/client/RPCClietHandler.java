package com.eva.rpc.client;

import java.util.UUID;

import com.eva.rpc.io.common.DelimiterUtil;
import com.eva.rpc.io.common.RPCRequestVO;
import com.eva.rpc.io.demo.AddAction;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RPCClietHandler extends ChannelInboundHandlerAdapter{
	private int counter = 0;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		RPCRequestVO reqvo = new RPCRequestVO();
		
		reqvo.setProtocal(AddAction.class);
		reqvo.setMethod("hello");
		reqvo.setParams(new Class<?>[] { String.class });
//		reqvo.setValues(new Object[] { "Tim:"+counter });
//		ctx.writeAndFlush(Unpooled.copiedBuffer(reqvo.serialize()));
		for(int i = 0;i<10;i++){
			reqvo.setNum(UUID.randomUUID().toString());
			reqvo.setValues(new Object[] { "Tim:"+i });
			ctx.writeAndFlush(Unpooled.copiedBuffer(reqvo.serialize(),DelimiterUtil.DELIMITER_BYTE));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.print(msg);
		System.out.println(counter++);
//		ctx.write(msg);
//		ByteBuf resp =(ByteBuf)msg;
//		ResultVO rvo = new ResultVO();
//		byte[] bytes = new byte[resp.readableBytes()];
//		resp.readBytes(bytes);
//		rvo.deserialize(bytes);
//		System.out.println(rvo);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
//		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.close();
	}

}
