package com.google.guicetest.aoptest;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestIncepctor {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new DemoModule());
		ShowService worker = injector.getInstance(ShowService.class);
		String result1 = worker.execute("aaaaa");
		System.out.println("�ҵĽ���ǣ�"+result1);
		String result2 = worker.execute(null);
		System.out.println("�ҵĽ���ǣ�"+result2);
	}
}
