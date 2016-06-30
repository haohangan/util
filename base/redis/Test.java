//package com.grgbanking.aptoto.config.database.redis;
//
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import org.eclipse.jetty.util.StringUtil;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//import com.grgbanking.aptoto.cometd.BayeuxJavaClient;
//import com.grgbanking.aptoto.config.database.mongodb.MongoDBConfig;
//
//public class Test {
//	static ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
//	static int a = 0;
//
//	public static void main(String[] args) {
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisTestSpring.class, MongoDBConfig.class,
//				BayeuxJavaClient.class);
//		System.out.println(ctx);
//		final StringRedisTemplate srt = ctx.getBean(StringRedisTemplate.class);
//		final ValueOperations<String, String> sops = srt.opsForValue();
////		sops.set("a", "");
//		String val = sops.get("a");
//		System.out.println(StringUtil.isBlank(val));
//		System.out.println(val==null);
//		System.out.println("a:"+val);
//		String valc = sops.get("c");
//		System.out.println(StringUtil.isBlank(valc));
//		System.out.println(valc==null);
//		System.out.println("c:"+valc);
//	}
//}
//
////final ValueOperations<String, String> sops = srt.opsForValue();
//// sops.set("a", "zxcv");
////
////pool.scheduleAtFixedRate(new Runnable() {
////
////	@Override
////	public void run() {
////
////		System.out.println(a);
////		sops.set(Integer.toString(a++), "asdad", 2, TimeUnit.SECONDS);
////
////	}
////}, 4, 4, TimeUnit.SECONDS);
////sops.set("aaa", "nimei");