package springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月22日 下午12:52:12 类说明
 */
@Controller("Url_Controller")
@RequestMapping("/url")
public class UrlController {

	@RequestMapping(value = "/index/{name}/{value}", method = RequestMethod.GET)
	public String toIndex(@PathVariable("name") String name1,
			@PathVariable String value, @RequestHeader String Accept,
			@CookieValue("JSESSIONID") String cookie,
			@RequestParam("tid") String tid) {
		System.out.println("index");
		System.out.println("name：" + name1);
		System.out.println("value：" + value);
		System.out.println("Accept：" + Accept);
		System.out.println("cookie：" + cookie);
		System.out.println("tid：" + tid);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/json")
	public Map<String,Object> tojson(){
		Map<String, Object> modelMap = new HashMap<String, Object>();  
        modelMap.put("name", "lfd");  
        modelMap.put("age", "20");  
		return modelMap;
	}
}
