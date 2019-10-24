package com.jt.springbootlearn.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold")
@Primary
public class IceCream implements Dessert {
}
