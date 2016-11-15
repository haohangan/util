package jersey.jaspring.conf.errorhandling;

import javax.ws.rs.core.Response.Status;

public class CustomReasonPhraseExceptionStatusType extends AbstractStatusType {
	private static final String CUSTOM_EXCEPTION_REASON_PHRASE = "Custom error message";

	public CustomReasonPhraseExceptionStatusType(Status httpStatus) {
		super(httpStatus, CUSTOM_EXCEPTION_REASON_PHRASE);
	}
}
