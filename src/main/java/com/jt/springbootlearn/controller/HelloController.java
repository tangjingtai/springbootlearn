package com.jt.springbootlearn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public  String hello() {
        return "Hello world";
    }

    @Value("${person.last-name}")
    private String name;

    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHelo(){
        return "hello "+name;
    }
}
