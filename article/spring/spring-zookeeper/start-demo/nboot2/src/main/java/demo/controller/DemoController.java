package demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guihao
 * @date 2016年12月2日上午11:41:27
 * @desc 
 */
@RestController
@RequestMapping(path="demo")
public class DemoController {
   
    @RequestMapping(method=RequestMethod.GET)
	public String get(){
		return "demo2";
	}
}
