package core.protocal;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月18日 下午3:20:15
 * @desc 供JAVA反射使用，封装服务的类型、方法、参数
 */
public class Invocation implements Serializable {
	private static final long serialVersionUID = 1L;

	private Class<?> interfaces;
	private Method method;
	private Object[] params;
	private Object result;

	public Class<?> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(Class<?> interfaces) {
		this.interfaces = interfaces;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Invocation [interfaces=" + interfaces + ", method=" + method
				+ ", params=" + Arrays.toString(params) + ", result=" + result
				+ "]";
	}

}
