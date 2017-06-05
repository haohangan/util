package simple.rest.express;

import java.util.UUID;

public class ObjController {
	public class Model {
		private UUID id = UUID.randomUUID();
		private String name;
		private String href;

		public Model(String name, String href) {
			super();
			this.name = name;
			this.href = href;
		}
	}

	public Model create(org.restexpress.Request req, org.restexpress.Response resp) {
		resp.setContentType("application/json");
		return new Model("jack", "http://jack.com");
	}
}
