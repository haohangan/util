package jersey.thrift_demo.service.impl;

import org.apache.thrift.TException;

import jersey.thrift_demo.service.HelloService;

public class HelloServiceImpl implements HelloService.Iface {

	@Override
	public int sayInt(int param) throws TException {
		System.out.println("say int :" + param);
		return param + 10;
	}

	@Override
	public String sayString(String param) throws TException {
		System.out.println("say String :" + param);
		return "param:" + param;
	}

	@Override
	public boolean sayBoolean(boolean param) throws TException {
		System.out.println("say boolean :" + param);
		return param;
	}

	@Override
	public void sayVoid() throws TException {
		System.out.println("doNothing");
	}

}
