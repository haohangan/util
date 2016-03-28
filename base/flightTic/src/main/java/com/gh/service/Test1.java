package com.gh.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月23日 下午3:50:47 类说明
 */
@Component(value = "Test1_service")
@Scope("prototype")
public class Test1 {

	public void test1(String name) {
		System.out.println("执行方法test1：参数为：" + name);
	}

	/**
	 * cglib final CGLIB的代理活动应当对用户是透明的。然而，有一些问题需要被考虑： Final方法不可以被通知，因为它们不能被覆盖。
	 * 你需要在你的类路径里有CGLIB 2的库；使用动态代理的话只需要JDK。
	 * 
	 * @param name
	 * @param age
	 * @throws Exception
	 */
	public void test2(String name, String age) throws Exception {
		// if (false) {
		// throw new Exception("测试错误");
		// }
		System.out.println("你好：" + name + ",你" + age + "岁了吧。");
	}

}
