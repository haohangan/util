package com.google.guicetest.aoptest;

public class SmallWindowShowService implements ShowService{

	@Override
	public String execute(String input) {
		System.out.println("this is small window");
		String result = input==null?"0":"1";
		return result;
	}

}
