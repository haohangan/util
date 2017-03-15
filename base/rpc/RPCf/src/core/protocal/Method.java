package core.protocal;

import java.io.Serializable;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����3:18:16
 * @desc ��װ�ͻ��˵��õķ������˵ķ������Ͳ���
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
