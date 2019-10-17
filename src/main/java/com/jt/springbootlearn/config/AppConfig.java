package com.jt.springbootlearn.config;

import com.jt.springbootlearn.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 表示当前类为一个配置类
 */
@Configuration
public class AppConfig {

    /**
     * 将方法返回的对象添加到容器中
     * @return
     */
    @Bean
    public HelloService helloService(){
        System.out.println("配置类@Bean给容器中添加了组件");
        return new HelloService();
    }
}
