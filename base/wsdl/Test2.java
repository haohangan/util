package call;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月20日 下午12:41:02 类说明
 */
public class Test2 {
	public static void main(String[] args) throws AxisFault {
		 //  使用RPC方式调用WebService        
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        //  指定调用WebService的URL
        EndpointReference targetEPR = new EndpointReference(
                "http://localhost:8080/axis2/services/Hello");
        options.setTo(targetEPR);
        //  指定getGreeting方法的参数值
        Object[] opAddEntryArgs = new Object[] {"man"};
        //  指定getGreeting方法返回值的数据类型的Class对象
        Class<?>[] classes = new Class[] {String.class};
        //  指定要调用的getGreeting方法及WSDL文件的命名空间
        QName opAddEntry = new QName("http://ws.apache.org/axis2", "sayHello");
        //  调用getGreeting方法并输出该方法的返回值
        System.out.println(serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0]);
		// // 下面是调用getPrice方法的代码，这些代码与调用getGreeting方法的代码类似
		// classes = new Class[] {int.class};
		// opAddEntry = new QName("http://ws.apache.org/axis2", "getPrice");
		// System.out.println(serviceClient.invokeBlocking(opAddEntry, new
		// Object[]{}, classes)[0]);
	}
}
