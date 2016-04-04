package publish;

import javax.xml.ws.Endpoint;

import service.impl.GetNameServiceImpl;

public class Publish1 {
    public static void main(String[] args) {
		Endpoint.publish("http://localhost:9000/count", new GetNameServiceImpl());
	}
}
