package com.jt.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 *
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication
public class SpringbootlearnApplication {

    public static void main(String[] args) {
        // Spring应用程序启动
        SpringApplication.run(SpringbootlearnApplication.class, args);

    }

    @Bean
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

    private static class MyViewResolver implements ViewResolver {

        @Nullable
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}
