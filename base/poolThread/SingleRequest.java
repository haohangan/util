package ti.thread;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import flexjson.JSONDeserializer;

public class SingleRequest {

	static String req_url = "http://localhost:8088/knowMan/get?id=";
	static StopWatch sw = new StopWatch();

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		sw.start();
		multiRun();
		sw.stop();
		System.out.println("时间为：" + sw.getTime() / 1000 + " s");
	}

	public static void shunxu() throws IOException {// 顺序执行需要(i=10,30s)(i=100,时间为：301
													// s)
		List<JsonVO> list = new ArrayList<JsonVO>();

		SingleRequest sq = new SingleRequest();
		for (int i = 0; i < 10; i++) {
			list.add(sq.getVO((int) (Math.random() * 10) + ""));
		}
		for (JsonVO vo : list) {
			System.out.println(vo);
		}
	}

	public static void multiRun() throws InterruptedException, ExecutionException {
		final int core = Runtime.getRuntime().availableProcessors();
		double blockingCoefficent = 0.9;
		final int poolSize = (int) (core / (1 - blockingCoefficent));
		System.out.println("core:" + core + "\tpoolSize:" + poolSize);
		// java.lang.management.
		// ExecutorService service = null; //替代Thread類
		// Lock
		final List<Callable<JsonVO>> list = new ArrayList<Callable<JsonVO>>();
		for (int i = 0; i < 10; i++) {
			list.add(new Callable<JsonVO>() {
				@Override
				public JsonVO call() throws Exception {
					String id = (int) (Math.random() * 10) + "";
					return new SingleRequest().getVO(id);
				}
			});
		}

		final ExecutorService executorPool = Executors.newFixedThreadPool(poolSize);
		final List<Future<JsonVO>> flist = executorPool.invokeAll(list, 10000, TimeUnit.SECONDS);
		for (final Future<JsonVO> vo : flist) {
			System.out.println(vo.get());
		}
		executorPool.shutdown();
	}

	public JsonVO getVO(String id) throws IOException {
		URL url;
		JsonVO vo;
		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		try {
			url = new URL(req_url + id);
			URLConnection conn = url.openConnection();
			is = conn.getInputStream();
			byte[] buff = new byte[512];
			int flag = -1;
			while ((flag = is.read(buff)) != -1) {
				sb.append(new String(buff, 0, flag));
			}

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			is.close();
		}
		String jsonStr = sb.toString();
		JSONDeserializer<JsonVO> de = new JSONDeserializer<JsonVO>();
		vo = de.use(null, JsonVO.class).use("obj", JsonVO.Code.class).deserialize(jsonStr);
		return vo;
	}
}
