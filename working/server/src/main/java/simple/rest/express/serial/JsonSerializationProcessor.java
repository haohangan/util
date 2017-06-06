package simple.rest.express.serial;

import org.restexpress.serialization.json.JacksonJsonProcessor;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonSerializationProcessor extends JacksonJsonProcessor{
	public JsonSerializationProcessor()
    {
    }

	@Override
	protected void initializeModule(SimpleModule module) {
		super.initializeModule(module);
	}
	
	
}
