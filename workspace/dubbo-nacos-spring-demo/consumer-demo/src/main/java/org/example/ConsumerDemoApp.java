package org.example;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.example.service.IHelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDubbo
public class ConsumerDemoApp {
    @DubboReference
    private IHelloService iHelloService;

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ConsumerDemoApp.class, args);
        IHelloService service = app.getBean(IHelloService.class);
        System.out.println(service.sayHello());
    }
}
