package test.client.test;

import test.client.GetNameService;
import test.client.GetNameServiceImplService;

public class TestGetName {
    public static void main(String[] args) {
    	GetNameService service = new GetNameServiceImplService().getGetNameServiceImplPort();
    	System.out.println(service.getRandomName());
    	System.out.println(service.getName(123));
	}
}
