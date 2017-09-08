package com.core.demo1;

import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月26日 上午10:19:53
 * 类说明
 */
public class Demo1 {
   public static void main(String[] args) throws AxisFault {
	RPCServiceClient serviceClient = new RPCServiceClient();
	Options options = serviceClient.getOptions();
	EndpointReference refe = new EndpointReference("http://localhost:8080/axis2/services/myService");//webService Address
	options.setTo(refe);
	
	QName qname = new QName("http://webservice.com","says");
	String[] params = new String[]{};
	OMElement element = serviceClient.invokeBlocking(qname, params);
	System.out.println(element);
//	Iterator<OMElement> it = element.getChildrenWithName(new QName("http://webservice.com", "return"));
	@SuppressWarnings("unchecked")
	Iterator<OMElement> it = element.getChildrenWithName(new QName("return"));
	while(it.hasNext()){
		OMElement o = it.next();
		System.out.println(o.getText());
	}
}
}
