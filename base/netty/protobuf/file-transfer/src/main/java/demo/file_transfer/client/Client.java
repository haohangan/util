package demo.file_transfer.client;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import com.google.protobuf.ByteString;

import demo.file_transfer.transfer.MyMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author guihao
 * @date 2016年12月30日下午3:13:54
 * @desc
 */
public class Client {
	final static Logger LOG = Logger.getGlobal();
	String HOST = "127.0.0.1";
	int PORT = 80;

	public Client(String hOST, int pORT) {
		HOST = hOST;
		PORT = pORT;
	}

	public void run(final MyMessage.mymessage mymsg) {
		LOG.info("client starting...");
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
				pipeline.addLast("protobufEncoder", new ProtobufEncoder());
				pipeline.addLast(new MyMessageClientHandler(mymsg));
			}

		});
		try {
			bootstrap.connect(HOST, PORT).channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
		LOG.info("client stop!");
	}

	public static void main(String[] args) {
		MyMessage.mymessage mymsg = MyMessage.mymessage.newBuilder().setName("哈撒给")
				.setData(ByteString.copyFrom("你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子你是啥子".getBytes(StandardCharsets.UTF_8))).build();
		String HOST = "127.0.0.1";
		int PORT = 80;
		new Client(HOST,PORT).run(mymsg);
	}
}
