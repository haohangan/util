package com.eva.core.string;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
    	String str = UUID.randomUUID().toString();
    	str = str.replaceAll("-", "");
		System.out.println(str);
	}
}
