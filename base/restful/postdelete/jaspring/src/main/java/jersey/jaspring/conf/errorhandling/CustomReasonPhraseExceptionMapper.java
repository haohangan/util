package jersey.jaspring.conf.errorhandling;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class CustomReasonPhraseExceptionMapper implements ExceptionMapper<CustomReasonPhraseException> {

	@Override
	public Response toResponse(CustomReasonPhraseException exception) {
		return Response.status(new CustomReasonPhraseExceptionStatusType(Status.BAD_REQUEST))
				.entity("Custom Reason Phrase exception occured : " + exception.getMessage()).build();
	}

}
