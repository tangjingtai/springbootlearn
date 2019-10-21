package com.jt.springbootlearn;

import com.jt.springbootlearn.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

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
    public void testHelloService() {
        boolean b = ioc.containsBean("helloService");
        System.out.println("containsBean:" + b);
    }

    @Test
    public void contextLoads() {
        System.out.println(person);
    }

    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testLogging() {
        //日志的级别；
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }

    @Autowired
    DataSource dataSource;

    @Test
    public void testJDBC() throws SQLException {
        System.out.println("data source class:"+dataSource.getClass());
        System.out.println("connection:"+dataSource.getConnection());
    }

}
