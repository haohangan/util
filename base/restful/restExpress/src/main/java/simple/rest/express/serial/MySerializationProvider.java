package simple.rest.express.serial;

import org.restexpress.response.ErrorResponseWrapper;
import org.restexpress.response.ResponseWrapper;
import org.restexpress.serialization.AbstractSerializationProvider;
import org.restexpress.serialization.SerializationProcessor;

public class MySerializationProvider extends AbstractSerializationProvider {
	private static final SerializationProcessor JSON_SERIALIZER = new JsonSerializationProcessor();
	private static final ResponseWrapper RESPONSE_WRAPPER = new ErrorResponseWrapper();

	public MySerializationProvider() {
		super();
		add(JSON_SERIALIZER, RESPONSE_WRAPPER, true);
		// add(XML_SERIALIZER, RESPONSE_WRAPPER);
	}

	public static SerializationProcessor json() {
		return JSON_SERIALIZER;
	}

}
