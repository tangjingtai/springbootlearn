package com.jt.springbootlearn.bean.concert;

public class DefaultEncoreable implements Encoreable {
    @Override
    public void performancore() {
        System.out.println("这是新加入的performancore...");
    }
}
