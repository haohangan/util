package com.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月14日 下午3:44:01
 * 类说明
 */
public class BeanFactoryTest implements FactoryBean<BeanFactoryTest>{
    public static void main(String[] args) {
		BeanFactory bf = null;
		FactoryBean fb = null;
	}

	@Override
	public BeanFactoryTest getObject() throws Exception {
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		return BeanFactoryTest.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
