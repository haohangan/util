package com.eva.rpc.io.handler;

import com.eva.rpc.io.common.DelimiterUtil;
import com.eva.rpc.io.common.ResultVO;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ServerEncoder extends MessageToByteEncoder<ResultVO> {

	@Override
	protected void encode(ChannelHandlerContext ctx, ResultVO msg, ByteBuf out) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer(msg.serialize(), DelimiterUtil.DELIMITER_BYTE));
	}

}
