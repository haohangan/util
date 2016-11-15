package jersey.jaspring;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jersey.jaspring.conf.JerseyConfig;
import jersey.jaspring.conf.SpringAnnotationConfig;

/**
 * Hello world!
 *
 */
public class App 
{
	static{
		Logger.getGlobal().setLevel(Level.INFO);
	}
	private static final URI BASE_URI = URI.create("http://localhost:80/app1/");

    public static final String ROOT_PATH = "base";
    
    public static void main(String[] args) {
        try {
            System.out.println("\"Hello World\" Jersey-Spring Example App");

            final JerseyConfig resourceConfig = new JerseyConfig();//核心配置
            resourceConfig.property("contextConfig", new AnnotationConfigApplicationContext(SpringAnnotationConfig.class));

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            server.start();

            System.out.println(String.format("Application started.\nTry out %s%s\nStop the application using CTRL+C",
                    BASE_URI, ROOT_PATH));

            Thread.currentThread().join();
		} catch (IOException | InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
