package com.core.demo1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月26日 下午3:52:44 类说明
 */
public class Demo2 {

	public static void main(String[] args) {
		String servicePath = "http://localhost:8080/axis2/services/myService";
		QName qname1 = new QName("http://webservice.com", "say");
		QName qname2 = new QName("http://webservice.com", "says");
		QName res = new QName("http://webservice.com", "return");
		try {
			OMElement om = call(servicePath, qname1, new String[] {});
			System.out.println(parseToObject(om, res));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			OMElement om = call(servicePath, qname2, new String[] {});
			for(Object obj:parseTOList(om, res)){
				System.out.println(obj);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static OMElement call(String servicePath, QName qname,
			String[] params) throws IOException {
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = new Options();
		options.setTo(new EndpointReference(servicePath));
		serviceClient.setOptions(options);
		return serviceClient.invokeBlocking(qname, params);
	}

	public static String parseToObject(OMElement element, QName qname) {
		OMElement it = element.getFirstElement();
		return it.getText();
	}

	public static List<?> parseTOList(OMElement element, QName qname) {
		@SuppressWarnings("unchecked")
		Iterator<OMElement> it = element.getChildrenWithName(qname);
		List<Object> list = new ArrayList<Object>();
		while (it.hasNext()) {
			OMElement om = it.next();
			list.add(om.getText());
		}
		return list;
	}
}
