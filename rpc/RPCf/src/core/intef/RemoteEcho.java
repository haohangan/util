package core.intef;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��18�� ����3:15:09 ��˵��
 */
public class RemoteEcho implements Echo {

	@Override
	public String echo(String msg) {
		return "from remote:" + msg;
	}

}
