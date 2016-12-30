package demo.file_transfer.server;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import demo.file_transfer.transfer.MyMessage;
import demo.file_transfer.transfer.MyMessage.mymessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author guihao
 * @date 2016年12月30日下午3:03:29
 * @desc
 */
public class MyMessageServerHandler extends ChannelInboundHandlerAdapter {
	static Logger LOG = Logger.getLogger(MyMessageServerHandler.class.getName());

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
		MyMessage.mymessage mymsg = (mymessage) msg;
		System.out.println(mymsg.getName());
		System.out.println(new String(mymsg.getData().toByteArray(), StandardCharsets.UTF_8));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channelReadComplete");
		ctx.channel().close();//
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		LOG.info("userEventTriggered");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		LOG.info("channelWritabilityChanged");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public boolean isSharable() {
		LOG.info("isSharable:" + super.isSharable());
		return super.isSharable();
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

}
