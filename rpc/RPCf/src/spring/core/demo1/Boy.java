package spring.core.demo1;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月24日 上午11:25:03 类说明
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
