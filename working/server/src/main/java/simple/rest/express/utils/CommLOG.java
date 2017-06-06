package simple.rest.express.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum CommLOG {
    INSTANCE;
	
	ExecutorService pool = Executors.newCachedThreadPool();
	
	public void log(String log){
		pool.submit(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
	}
}
