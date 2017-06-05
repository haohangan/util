package simple.rest.express.serial;

public class XmlResponse {
	@SuppressWarnings("unused")
	private String action;
	@SuppressWarnings("unused")
	private long delayMs;
	@SuppressWarnings("unused")
	private String message;

	public XmlResponse(String action, long delayMs, String message) {
		super();
		this.action = action;
		this.delayMs = delayMs;
		this.message = message;
	}
}
