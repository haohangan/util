package core.intef;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����3:16:51 ��˵��
 */
public class AnotherEchoServiceImpl implements AnotherEchoService {

	@Override
	public String anotherEcho(String msg) {
		return "from remote impl:" + msg;
	}

}
