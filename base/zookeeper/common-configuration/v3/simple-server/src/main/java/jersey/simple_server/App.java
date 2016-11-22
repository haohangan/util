package jersey.simple_server;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Hello world!
 *
 */
public class App {
	static {
		Logger.getGlobal().setLevel(Level.INFO);
	}
	private static final URI BASE_URI = URI.create("http://localhost:90/simple1/");

	public static final String ROOT_PATH = "base";

	public static void main(String[] args) throws Exception {
		try {
			System.out.println("\"Hello World\" Jersey-Spring Example App");

			ResourceConfig conf = new ResourceConfig();
			conf.packages("jersey.simple_server.resource");

			final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, conf, false);
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					server.shutdownNow();
					try {
						ServiceStart.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}));
			server.start();
			ServiceStart.initService();
			System.out.println(String.format("Application started.\nTry out %s%s\nStop the application using CTRL+C",
					BASE_URI, ROOT_PATH));

			Thread.currentThread().join();
		} catch (IOException | InterruptedException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
