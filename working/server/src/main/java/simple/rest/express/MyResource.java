package simple.rest.express;

import org.restexpress.Request;
import org.restexpress.Response;

//static final String DELETE_ACTION_NAME = "delete";
//static final String GET_ACTION_NAME = "read";
//static final String POST_ACTION_NAME = "create";
//static final String PUT_ACTION_NAME = "update";
//static final List<HttpMethod> DEFAULT_HTTP_METHODS = Arrays.asList(new HttpMethod[] {GET, POST, PUT, DELETE});
//static final Map<HttpMethod, String> ACTION_MAPPING = new HashMap<HttpMethod, String>();
//
//static
//{
//	ACTION_MAPPING.put(DELETE, DELETE_ACTION_NAME);
//	ACTION_MAPPING.put(GET, GET_ACTION_NAME);
//	ACTION_MAPPING.put(POST, POST_ACTION_NAME);
//	ACTION_MAPPING.put(PUT, PUT_ACTION_NAME);
//}
public class MyResource {
	/**
	 * GET
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String read(Request request, Response response) {
		return "get it";
	}

	/**
	 * POST
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String create(Request request, Response response) {
		return "post it";
	}

	/**
	 * PUT
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String update(Request request, Response response) {
		return "put it";
	}

	/**
	 * DELETE
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String delete(Request request, Response response) {
		return "delete it";
	}

}
