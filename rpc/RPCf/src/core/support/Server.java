package core.support;

import core.protocal.Invocation;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月18日 下午3:46:28 类说明
 */
public interface Server {
	public void stop();

	public void start();

	public void register(Class<?> interfaceDefiner, Class<?> interfaceImpl);

	public void call(Invocation invo);

	public boolean isRunning();

	public int getPort();

}
