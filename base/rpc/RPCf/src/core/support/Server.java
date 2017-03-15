package core.support;

import core.protocal.Invocation;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����3:46:28 ��˵��
 */
public interface Server {
	public void stop();

	public void start();

	public void register(Class<?> interfaceDefiner, Class<?> interfaceImpl);

	public void call(Invocation invo);

	public boolean isRunning();

	public int getPort();

}
