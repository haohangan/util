package com.eva.rpc.io.demo.impl;

import com.eva.rpc.io.demo.AddAction;

public class AddAtionImpl implements AddAction {

	@Override
	public String hello(String name) {
		return "你好：" + name;
	}

	@Override
	public int add(int a, int b) {
		return a + b;
	}

}
