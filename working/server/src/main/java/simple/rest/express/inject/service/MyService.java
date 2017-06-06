package simple.rest.express.inject.service;

import javax.inject.Singleton;

@Singleton
public class MyService {
    
	public String service(){
		return "myservice";
	}
}
