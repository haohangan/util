package call;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月20日 上午11:31:10 类说明
 */
public class Test {
	public static void main(String[] args) throws AxisFault {
		RPCServiceClient serviceClient = new RPCServiceClient();

		Options options = serviceClient.getOptions();
		
		// 指定调用WebService的URL
		EndpointReference er = new EndpointReference(
				"http://localhost:8080/axis2/services/TestHello");
		options.setTo(er);
		options.setAction("sayHello");

		QName qname = new QName("http://ws.apache.org/axis2","sayHello");
		Object[] objs = new Object[]{null,null};
		Class<?>[] classs = new Class[]{null};
		System.out.println(serviceClient.invokeBlocking(qname, objs, classs));
	}
}
