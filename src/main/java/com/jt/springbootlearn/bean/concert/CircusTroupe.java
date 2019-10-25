package com.jt.springbootlearn.bean.concert;

public class CircusTroupe implements Performance {
    @Override
    public void perform() {
        System.out.println("circus troupe perform....");
    }
}
