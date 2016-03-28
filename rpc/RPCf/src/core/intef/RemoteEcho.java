package core.intef;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月18日 下午3:15:09 类说明
 */
public class RemoteEcho implements Echo {

	@Override
	public String echo(String msg) {
		return "from remote:" + msg;
	}

}
