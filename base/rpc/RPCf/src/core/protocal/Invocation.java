package core.protocal;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����3:20:15
 * @desc ��JAVA����ʹ�ã���װ��������͡�����������
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
