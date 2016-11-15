package jersey.jaspring.conf.errorhandling;

/**
 * 业务错误
 * 
 * @author YKSE
 *
 */
public class CustomReasonPhraseException extends Exception {
	private static final long serialVersionUID = -5185593569980623788L;
	private final int businessCode;

	public CustomReasonPhraseException(int businessCode, String message) {
		super(message);
		this.businessCode = businessCode;
	}

	public int getBusinessCode() {
		return businessCode;
	}
}
