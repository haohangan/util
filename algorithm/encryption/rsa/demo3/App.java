package test.demo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import der.DerReader;
import test.demo.service.OrderService;

/**
 * Hello world!
 *
 */
@Controller
// @ComponentScan(basePackages = {"test.demo"})
@SpringBootApplication
public class App 
{
	// @Autowired
	// OrderService service;
	
	// @RequestMapping("/")
    // @ResponseBody
    // List<OrderInfo> home(@RequestHeader(required=false) String name) {
		// System.out.println("@RequestHeader:"+name);
        // return service.list();
    // }
	
	// @GetMapping(path="/a")
	// String welcome(Map<String, Object> model) {
		// model.put("time", new Date());
		// model.put("message", "1231");
		// return "welcome";
	// }
	
	// @RequestMapping("/b")
	// String welcome2(Map<String, Object> m) {
		// m.put("time", new Date());
		// m.put("message", "111");
		// return "welcome";
	// }
	
	// @RequestMapping("/c")
	// @ResponseBody
	// String welcome3(String tel) {
	    // System.out.println("tel:"+tel);
		// return "welcome";
	// }
	
	// @Bean
    // public FilterRegistrationBean jwtFilter() {
        // final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        // registrationBean.setFilter(new JwtFilter());
        // registrationBean.addUrlPatterns("/*");

        // return registrationBean;
    // }
	
	@CrossOrigin
	@RequestMapping("/decrypt")
	@ResponseBody
	public String decrypt(String encrypt){
		String result =  DerReader.decrypt(encrypt);
		System.out.println("解密后："+result);
		return result;
	}

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args); 
//        DispatcherServlet
    }
}
