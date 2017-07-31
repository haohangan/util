package test.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello world!
 *
 */
@Controller
@EnableAutoConfiguration
@ComponentScan(basePackages = {"test.demo"})
public class App 
{
	@Autowired
	OrderService service;
	
	@RequestMapping("/")
    @ResponseBody
    List<OrderInfo> home() {
        return service.list();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
