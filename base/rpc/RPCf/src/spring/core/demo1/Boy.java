package spring.core.demo1;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��24�� ����11:25:03 ��˵��
 */
public class Boy implements Person {

	private String name;

	public Boy() {
	}

	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void self() {
		System.out.println("Boy's name is " + name);
	}

}
