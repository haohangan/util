package dynamic.jdk;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月24日 上午10:58:56 类说明
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
