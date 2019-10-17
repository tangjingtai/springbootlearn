package com.jt.springbootlearn;

import com.jt.springbootlearn.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootlearnApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService(){
        boolean b = ioc.containsBean("helloService");
        System.out.println("containsBean:"+ b);
    }

	@Test
	public void contextLoads() {
        System.out.println(person);
    }

}
