package org.eva.websocket.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author guihao
 * @date 2017年1月6日下午5:33:29
 * @desc 核心，参考了git上面的c1000-server的代码
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

	static Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());

	static {
		logger.setLevel(Level.INFO);
	}

	final static String WEBSOCKET_PATH = "/websocket";
	final static String SUB_PROTOCOL = "zookeeperWS";

	final static ConcurrentHashMap<String, Channel> members = new ConcurrentHashMap<>();
	final static ConcurrentHashMap<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

	private WebSocketServerHandshaker handshaker;// 第一次握手时获取实例

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof FullHttpRequest) {
			System.out.println("handleHttpRequest 查看");
			handleHttpRequest(ctx, (FullHttpRequest) msg);// 仅仅第一次走这个分支
		} else if (msg instanceof WebSocketFrame) {
			System.out.println("WebSocketFrame 查看");
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		} else {
			// TODO
			throw new Exception("真的是无法理解");
		}
	}

	/**
	 * 解析握手请求
	 * 
	 * @param ctx
	 * @param request
	 */
	public void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
		QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
		Map<String, List<String>> parameters = queryStringDecoder.parameters();// 解析uri上带的参数
		List<String> list = parameters.get("uname");
		if (list != null && list.size() > 0) {
			System.out.println("用户名：" + list.get(0));
		}
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
				"ws://" + request.headers().get(HttpHeaderNames.HOST) + WEBSOCKET_PATH, SUB_PROTOCOL, true);
		handshaker = wsFactory.newHandshaker(request);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), request).addListener(new GenericFutureListener<Future<? super Void>>() {

				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					if (future.isSuccess()) {
						members.put(ctx.channel().id().asLongText(), ctx.channel());
						logger.severe("added channel:" + ctx.channel().remoteAddress());
					}
				}
			});
		}
	}

	public void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(
					String.format("%s frame types not supported", frame.getClass().getName()));
		}
		String text = ((TextWebSocketFrame) frame).text();
		System.out.println(" 收到 " + ctx.channel() + text);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("收到" + incoming.remoteAddress() + " 握手请求");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		String key = ctx.channel().id().asLongText();
		if (members.containsKey(key)) {
			members.remove(key);
		}
		System.out.println("移除channel:" + key);
		System.out.println("剩余用户");
		for (Map.Entry<String, Channel> entry : members.entrySet()) {
			System.out.println(entry.getKey() + "\t:\t" + entry.getValue());
		}
		System.out.println("剩余用户——————————————————————————");
	}

}
