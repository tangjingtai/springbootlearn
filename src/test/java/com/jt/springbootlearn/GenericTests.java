package com.jt.springbootlearn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericTests {

    @Test
    public void testList(){
        List<Integer> list = new ArrayList();
        list.add(1);
        Integer i = list.get(0);
        System.out.println(i);

        ArrayList list2 = new ArrayList();
        list2.add("str");
        list = list2;
        Object o = list.get(0);
        System.out.println(o);
    }
}
