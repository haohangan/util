package test.myclient;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import test.client.GetNameService;

public class MyClient {
	/**
	 * 参照GetNameServiceImplService.java，自己写个简单的
	 * 
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		// wsdl网络路径
		URL url = new URL("http://localhost:9000/count?wsdl");
		// 服务描述中服务端点的限定名称 两个参数分别为 命名空间 服务名
		QName qName = new QName("http://impl.service/", "GetNameServiceImplService");
		// 创建服务对象
		javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qName);
		// 获得GetNameService的实现类对象
		GetNameService hiService = service.getPort(new QName("http://impl.service/", "GetNameServiceImplPort"),
				GetNameService.class);
		// 调用WebService方法
		System.out.println(hiService.getName(123));
		System.out.println(hiService.getRandomName());
	}
}
