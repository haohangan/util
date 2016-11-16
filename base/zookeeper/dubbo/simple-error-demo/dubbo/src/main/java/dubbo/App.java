package dubbo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	static{
		Logger.getGlobal().setLevel(Level.INFO);
	}
	
	static final Logger logger = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) throws IOException, InterruptedException {
		final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				context.close();
			}
		}));
		context.start();
		logger.info("provider statted....");
		Thread.currentThread().join();
	}
}
