package service.impl;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import service.GetNameService;

@WebService(endpointInterface = "service.GetNameService")
@SOAPBinding(style = Style.RPC)
public class GetNameServiceImpl implements GetNameService {

	static ConcurrentHashMap<String, String> names = new ConcurrentHashMap<String, String>();
	static String perfix = "key_";
	{
		int time = 10000;
		Random r = new Random(time);
		for (int i = 0; i < time; i++) {
			names.put(perfix + i, r.nextDouble() + "");
		}
	}

	@Override
	public String getName(Integer name) {
		System.out.println("查找的key为 :" + name);
		String key = perfix + name;
		return names.get(key);
	}

	@Override
	public String getName() {
		String key = perfix + (int) Math.random() * 10000;
		System.out.println("随机生成的key:" + key);
		return names.get(key);
	}

}
