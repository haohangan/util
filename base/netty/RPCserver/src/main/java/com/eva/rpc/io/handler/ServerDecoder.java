package com.eva.rpc.io.handler;

import java.util.List;

import com.eva.rpc.io.common.RPCRequestVO;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ServerDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] b = new byte[in.readableBytes()];
		in.readBytes(b);
		out.add(new RPCRequestVO().desrialize(b));
	}

}
