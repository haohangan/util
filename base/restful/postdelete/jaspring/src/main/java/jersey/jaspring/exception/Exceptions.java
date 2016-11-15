package jersey.jaspring.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * 自定义处理应用层错误
 * @author YKSE
 *
 */
@Deprecated
public class Exceptions {
	/**
	 * 自定义的exception
	 * 
	 * @author YKSE
	 *
	 */
	public static class MyException extends RuntimeException {
		private static final long serialVersionUID = -2481222011983515286L;
		private Response response;

		public MyException(Response response) {
			this.response = response;
		}

		public Response getResponse() {
			return response;
		}
	}

	/**
	 * 自定义的exception
	 * 
	 * @author YKSE
	 *
	 */
	public static class MySubException extends MyException {

		private static final long serialVersionUID = 2772732224956168797L;

		public MySubException(Response response) {
			super(response);
		}
	}

	/**
	 * 自定义的exception
	 * 
	 * @author YKSE
	 *
	 */
	public static class MySubSubException extends MySubException {

		private static final long serialVersionUID = 7872263705972943032L;

		public MySubSubException(Response response) {
			super(response);
		}
	}
	/*
	 * =========================================================================
	 */
	// -- Exception Mappers
//    @Provider
//    public static class MyExceptionMapper implements ExceptionMapper<MyException> {
//
//        @Override
//        public Response toResponse(MyException exception) {
//            Response r = exception.getResponse();
//            return Response.status(r.getStatus()).entity(
//                    "Code:" + r.getStatus() + ":" + getClass().getSimpleName()).build();
//        }
//    }
//
//    @Provider
//    public static class MySubExceptionMapper implements ExceptionMapper<MySubException> {
//
//        @Override
//        public Response toResponse(MySubException exception) {
//            Response r = exception.getResponse();
//            return Response.status(r.getStatus()).entity(
//                    "Code:" + r.getStatus() + ":" + getClass().getSimpleName()).build();
//        }
//    }

//    @Provider
    public static class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

        @Override
        public Response toResponse(WebApplicationException exception) {
            Response r = exception.getResponse();
            return Response.status(r.getStatus()).entity("Code:" + r.getStatus() + ":"
                    + getClass().getSimpleName()).build();
        }
    }
}
