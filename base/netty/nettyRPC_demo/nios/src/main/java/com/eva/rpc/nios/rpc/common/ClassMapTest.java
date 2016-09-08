package com.eva.rpc.nios.rpc.common;

import com.eva.rpc.nios.rpc.demo.DemoIntf;
import com.eva.rpc.nios.rpc.demo.impl.DemoIntfImpl;

public class ClassMapTest {
	public static void main(String[] args) {
		ClassMap.INSTANCE.register(DemoIntfImpl.class);
		System.out.println(ClassMap.INSTANCE.getContext().getBean(DemoIntf.class).say("nihao"));
	}
}
