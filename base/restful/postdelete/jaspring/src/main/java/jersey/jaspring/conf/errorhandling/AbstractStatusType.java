package jersey.jaspring.conf.errorhandling;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.core.Response.StatusType;

/**
 * Class used to provide custom StatusTypes, especially for the the Reason
 * Phrase that appears in the HTTP Status Response
 */
public abstract class AbstractStatusType implements StatusType {

	private int statusCode;
	private Family family;
	private String reasonPhrase;

	public AbstractStatusType(final Family family, final int statusCode, String reasonPhrase) {
		super();
		this.statusCode = statusCode;
		this.family = family;
		this.reasonPhrase = reasonPhrase;
	}

	protected AbstractStatusType(final Status status, final String reasonPhrase) {
		this(status.getFamily(), status.getStatusCode(), reasonPhrase);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Family getFamily() {
		return family;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

}
