package core.intef;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月18日 下午3:16:51 类说明
 */
public class AnotherEchoServiceImpl implements AnotherEchoService {

	@Override
	public String anotherEcho(String msg) {
		return "from remote impl:" + msg;
	}

}
