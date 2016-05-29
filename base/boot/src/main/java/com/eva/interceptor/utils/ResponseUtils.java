package com.eva.interceptor.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import flexjson.JSONSerializer;

public class ResponseUtils {
	public static void writeObject(AuthenticationRtnVO obj, HttpServletResponse resp) {
		resp.setCharacterEncoding("utf-8");
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("class");
		try {
			serializer.serialize(obj, resp.getWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeObject(String message,String url, HttpServletResponse resp) {
		resp.setContentType("application/json;charset=utf-8");
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("class");
		AuthenticationRtnVO obj = new AuthenticationRtnVO(message,url);
		try {
			serializer.serialize(obj, resp.getWriter());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		AuthenticationRtnVO vo = new AuthenticationRtnVO("过期","/sdad");
//		JSONSerializer serializer = new JSONSerializer();
//		serializer.exclude("class");
//		System.out.println(serializer.serialize(vo));
//	}
}
