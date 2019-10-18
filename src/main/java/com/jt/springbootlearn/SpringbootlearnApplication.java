package com.jt.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
