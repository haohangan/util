package com.gh.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月23日 下午3:50:47 类说明
 */
@Component(value = "Test2")
@Scope("prototype")
public class Test2 {

	public void test2(String name, String age) throws Exception {
		System.out.println("方法二");
	}
	
	public void aTest(){
		System.out.println("aaaaa");
	}

}
