package dynamic.jdk;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��24�� ����10:58:56 ��˵��
 */
public class Person implements Speak {

	private String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public void self() {
		System.out.println("my name is " + name);
	}
}
