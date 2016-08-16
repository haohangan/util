package com.eva.rpc.hadooprpcserver.impl;

import com.eva.rpc.hadooprpcserver.demo.LoginAction;

public class LoginActionImpl implements LoginAction{

	public String saySth(String input) {
		if(input==null){
			input = "a哈哈";
		}
		return "你好："+input;
	}

}
