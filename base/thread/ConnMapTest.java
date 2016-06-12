import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnMapTest {

	static ConcurrentMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

	static Map<String, Integer> hashMap = new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		timeTest(map);
		timeTest(hashMap);
	}
	
	public static void timeTest(final Map<String,Integer> map){
		long startTime = System.nanoTime();
		test(map);
		long entTime = System.nanoTime();
		long totalTime = (entTime - startTime) / 1000000L;
		System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
	}
	
	public static void test(final Map<String,Integer> map){
		ExecutorService pool = Executors.newFixedThreadPool(5);
		for(int i = 0;i<500000;i++){
			pool.submit(new Runnable() {
				Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);
				 
				@Override
				public void run() {
					map.put(String.valueOf(crunchifyRandomNumber), new Integer(crunchifyRandomNumber));
				}
			});
		}
		pool.shutdown();
	}
}
