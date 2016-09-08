package impl.demo;

import intfs.demo.HelloWorld;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public void initServer() {
		System.out.println("初始化服务器，不做其他操作");
	}

	@Override
	public void initServer(String name) {
		System.out.println("初始化服务器：" + name);
	}

	@Override
	public void call(int id) {
		System.out.println("打电话给：" + id);
	}

	@Override
	public String value() {
		return "num:" + (int) (Math.random() * 100);
	}

}
