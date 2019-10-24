package com.jt.springbootlearn.bean;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fruity")
public class Popsicle implements Dessert {
}
