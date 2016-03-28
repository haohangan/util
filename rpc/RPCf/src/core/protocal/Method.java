package core.protocal;

import java.io.Serializable;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月18日 下午3:18:16
 * @desc 封装客户端调用的服务器端的方法名和参数
 */
public class Method implements Serializable {

	private static final long serialVersionUID = 1L;

	private String methodName;
	private Class<?>[] params;

	public Method(String methodName, Class<?>[] params) {
		this.methodName = methodName;
		this.params = params;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParams() {
		return params;
	}

	public void setParams(Class<?>[] params) {
		this.params = params;
	}

}
