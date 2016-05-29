package com.eva.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/opt")
public class HelloController {
	 
	@RequestMapping("/name")
	public String getName(){
		return "测试用力1";
	}
}
