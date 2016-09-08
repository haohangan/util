package com.eva.rpc.nios.rpc.demo.impl;

import org.springframework.stereotype.Component;

import com.eva.rpc.nios.rpc.demo.DemoIntf;

@Component
public class DemoIntfImpl implements DemoIntf {

	@Override
	public String say(String word) {
		System.out.println("你说你这是弄啥！");
		return "DEMO:" + word;
	}

	@Override
	public String toString() {
		return "DemoIntfImpl []";
	}

}
