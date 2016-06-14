package com.eva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.vo.DemoVO;
import com.eva.vo.Json;

@RestController
public class ActionController {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<Json> handleMethodArgumentNotValidException(BindException error) {
//		ResponseEntity<String> rtn = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return ResponseEntity.accepted().body(Json.success("001"));
	}

	@RequestMapping("/demo")
	public String demo(@Validated DemoVO vo) {
//		if (bindingResult.hasErrors()) {
//			return "form";, BindException bindingResult
//		}
		return "aaaa";
	}
}
