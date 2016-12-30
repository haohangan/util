package demo.file_transfer.client;

import demo.file_transfer.transfer.MyMessage;
import demo.file_transfer.transfer.MyMessage.mymessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author guihao
 * @date 2016年12月30日下午3:18:25
 * @desc
 */
public class MyMessageClientHandler extends ChannelInboundHandlerAdapter {
	MyMessage.mymessage mymsg;

	public MyMessageClientHandler(mymessage mymsg) {
		super();
		this.mymsg = mymsg;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(mymsg);
//		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	
}
