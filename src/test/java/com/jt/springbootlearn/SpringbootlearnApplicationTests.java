package com.jt.springbootlearn;

import com.jt.springbootlearn.bean.Dessert;
import com.jt.springbootlearn.bean.Dog;
import com.jt.springbootlearn.bean.Foo;
import com.jt.springbootlearn.bean.Person;
import com.jt.springbootlearn.bean.concert.*;
import com.jt.springbootlearn.bean.disc.CompactDisc;
import com.jt.springbootlearn.bean.disc.TrackCounter;
import com.jt.springbootlearn.config.BenaConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * SpringBoot 单元测试
 */
@RunWith(SpringRunner.class)
@ComponentScan
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
    private Logger logger = LoggerFactory.getLogger(getClass());

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
        System.out.println("data source class:" + dataSource.getClass());
        System.out.println("connection:" + dataSource.getConnection());
    }

    @Test
    public void testXMLConfigure() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans.xml");
        Person person2 = (Person) applicationContext.getBean("person2");
        System.out.println(person2);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BenaConfig.class);
        Person person = annotationConfigApplicationContext.getBean(Person.class);
        System.out.println(person);
    }

    @Autowired
//    @Qualifier("fruity")
    private Dessert dessert;

    @Test
    public void testQualifire() {
        System.out.println(dessert.getClass());
    }

    @Test
    public void testSingleton() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BenaConfig.class);
        Dog dog = annotationConfigApplicationContext.getBean(Dog.class);
        System.out.println(dog);

        Person person = annotationConfigApplicationContext.getBean(Person.class);
        System.out.println(person);
    }

    @Autowired
    Performance performance;

    @Autowired
    Audience audience;

    @Autowired
    CompactDisc disc;

    @Autowired
    TrackCounter trackCounter;

    @Test
    public void testAOP() {
        performance.perform();

        disc.playTrack(2);
        disc.playTrack(3);
        disc.playTrack(1);
        disc.playTrack(0);
        disc.playTrack(2);
        disc.playTrack(0);
        disc.playTrack(2);
        disc.playTrack(1);

        Assert.assertEquals(2, trackCounter.getTrackCount(0));
        Assert.assertEquals(2, trackCounter.getTrackCount(1));
        Assert.assertEquals(3, trackCounter.getTrackCount(2));
        Assert.assertEquals(1, trackCounter.getTrackCount(3));
    }

    @Autowired
    CriticAspect criticAspect;

    @Test
    public void testAspectJ() {
        performance.perform();
        System.out.println("performance class: " + performance.getClass());
        System.out.println("criticAspect class: " + criticAspect.getClass());
        System.out.println("CriticismEngine class: " + criticAspect.getCriticismEngine().getClass());

        Performance performance2 = new LiudehuaConcert();
        System.out.println("performances2 class: " + performance2.getClass());

        Foo foo = new Foo();
        System.out.println("foo class: " + foo.getClass());

    }

    @Test
    public void testInjectNewInterface() {
        performance.perform();
        if (performance instanceof Encoreable) {
            System.out.println("执行新增功能");
            ((Encoreable) performance).performancore();
        }
    }

}
