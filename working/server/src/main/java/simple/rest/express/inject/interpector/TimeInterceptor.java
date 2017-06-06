package simple.rest.express.inject.interpector;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import simple.rest.express.utils.CommonSchedule;
import simple.rest.express.utils.TimeCounter;

public class TimeInterceptor implements MethodInterceptor {

	TimeCounter counter;

	public TimeInterceptor() {
		counter = new TimeCounter();
		CommonSchedule.INSTANCE.add(new Runnable() {

			@Override
			public void run() {
				System.out.println("开始统计：" + new Date());
				counter.start();
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				counter.stop();
				long times = counter.get();
				System.out.println(new Date() + "-3秒内接受的请求数为：" + times);
				counter.reset();
			}
		}, 10, 10);
	}

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		boolean status = counter.status();
		System.out.println(status);
		if (status) {
			counter.update();
		}
		System.out.println("拦截器");
		return arg0.proceed();
	}

}
