package call;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��20�� ����11:31:10 ��˵��
 */
public class Test {
	public static void main(String[] args) throws AxisFault {
		RPCServiceClient serviceClient = new RPCServiceClient();

		Options options = serviceClient.getOptions();
		
		// ָ������WebService��URL
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
