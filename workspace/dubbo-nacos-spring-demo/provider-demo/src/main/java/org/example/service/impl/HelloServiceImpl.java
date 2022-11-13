package org.example.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.service.IHelloService;

@DubboService
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello() {
        return "hello";
    }
}
