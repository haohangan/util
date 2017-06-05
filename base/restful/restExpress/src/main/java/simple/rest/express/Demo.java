package simple.rest.express;

import org.restexpress.RestExpress;

import io.netty.handler.codec.http.HttpMethod;
import simple.rest.express.serial.MySerializationProvider;

public class Demo {
    public static void main(String[] args) {
    	RestExpress.setDefaultSerializationProvider(new MySerializationProvider());
//    	RestExpress.setDefaultSerializationProvider(new GsonSerializationProvider());
//    	RestExpress.setSerializationProvider();
    	
    	RestExpress server = new RestExpress()
                .setName("Echo").setPort(8888);
    	
    	
//    	server.uri("/my", new MyResource()).noSerialization();
    	
    	server.uri("/obj", new ObjController()).action("create",HttpMethod.POST).action("read", HttpMethod.GET);
    	
    	server.bind();
    	server.awaitShutdown();
	}
    
    
//    server.uri("/echo", new Object(){
//		@SuppressWarnings("unused")
//		public String read(Request req, Response res)
//        {
//            String value = req.getHeader("echo");
//            res.setContentType("text/xml");
//
//            if (value == null)
//            {
//                return "<http_test><error>no value specified</error></http_test>";
//            }
//            else
//            {
//                return String.format("<http_test><value>%s</value></http_test>", value);
//            }
//        }
//	}).method(HttpMethod.GET).noSerialization();
}
