package com.jt.springbootlearn.config;

import com.jt.springbootlearn.bean.Dog;
import com.jt.springbootlearn.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class BenaConfig {
    @Bean
    public Person getPerson(){
        Person person = new Person();
        person.setLastName("张三");
        person.setDog(getDog());
        person.setAge(30);
        return person;
    }

    static int dogIndex = 1;

    @Bean
    public Dog getDog(){
        Dog dog = new Dog();
        dog.setName("dog_"+dogIndex);
        dogIndex++;
        dog.setAge(3);
        return dog;
    }
}
