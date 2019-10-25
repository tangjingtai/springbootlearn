package com.jt.springbootlearn.bean.concert;

import org.springframework.stereotype.Component;

@Component
public class LiudehuaConcert implements Performance {
    @Override
    public void perform() {
        System.out.println("我是刘德华，我的老家，就住在这个屯....");
    }
}
