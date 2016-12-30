package demo.file_transfer.client;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.lang3.time.StopWatch;

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
	final static StopWatch watch = new StopWatch();
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
     static int num = 99999999;
	 final static CountDownLatch latch = new CountDownLatch(num);
	
	public static void main(String[] args) throws InterruptedException {
		watch.start();
		
		ExecutorService service = Executors.newScheduledThreadPool(100);
		final String HOST = "127.0.0.1";
		final int PORT = 80;
		for(int i = 0;i<num;i++){
			final MyMessage.mymessage mymsg = MyMessage.mymessage.newBuilder().setName("哈撒给"+i)
					.setData(ByteString.copyFrom((":"+i).getBytes(StandardCharsets.UTF_8))).build();
			service.execute(new Runnable() {
				@Override
				public void run() {
					new Client(HOST,PORT).run(mymsg);
					latch.countDown();
				}
			});
		}
		service.shutdown();
		latch.await();
		watch.stop();
		System.out.println("time(s):"+watch.getTime(TimeUnit.SECONDS));
	}
}
