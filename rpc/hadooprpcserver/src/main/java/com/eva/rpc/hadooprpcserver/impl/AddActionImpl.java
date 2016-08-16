package com.eva.rpc.hadooprpcserver.impl;

import com.eva.rpc.hadooprpcserver.demo.AddAction;

public class AddActionImpl implements AddAction{

	@Override
	public int add(int a, int b) {
		return a+b;
	}

}
