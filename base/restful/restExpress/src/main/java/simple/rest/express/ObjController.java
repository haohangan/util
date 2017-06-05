package simple.rest.express;

import java.util.UUID;

import simple.rest.express.serial.XmlResponse;

public class ObjController {
	public class Model {
		@SuppressWarnings("unused")
		private UUID id = UUID.randomUUID();
		@SuppressWarnings("unused")
		private String name;
		@SuppressWarnings("unused")
		private String href;

		public Model(String name, String href) {
			super();
			this.name = name;
			this.href = href;
		}
	}

	public Model create(org.restexpress.Request req, org.restexpress.Response resp) {
		return new Model("jack", "http://jack.com");
	}

	public XmlResponse read(org.restexpress.Request req, org.restexpress.Response resp) {
		XmlResponse xr = new XmlResponse("ri", 1000, "hello");
		return xr;
	}
}
