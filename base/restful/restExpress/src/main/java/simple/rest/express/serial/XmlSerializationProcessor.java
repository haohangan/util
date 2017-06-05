package simple.rest.express.serial;

import org.restexpress.serialization.xml.XstreamXmlProcessor;

public class XmlSerializationProcessor extends XstreamXmlProcessor {
	public XmlSerializationProcessor() {
		super();
		alias("delay_response", XmlResponse.class);
	}
}
