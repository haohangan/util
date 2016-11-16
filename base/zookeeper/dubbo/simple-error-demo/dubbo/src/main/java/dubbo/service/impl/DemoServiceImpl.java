package dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;

import dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {

	@Override
	public String say() {

		return "hello ok";
	}

	@Override
	public List<String> getUsers() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add("name:" + i);
		}
		return list;
	}

}
