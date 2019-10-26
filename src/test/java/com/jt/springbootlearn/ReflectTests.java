package com.jt.springbootlearn;

import com.jt.springbootlearn.reflect.ClassUtil;
import com.jt.springbootlearn.reflect.Foo;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

public class ReflectTests {
    @Test
    public void testGetMethod(){
        Class<?> fooClass = Foo.class;
        Method[] methods = fooClass.getMethods();
        System.out.println("methods....");
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("interfaces....");
        Class<?>[] interfaces = fooClass.getInterfaces();
        for (Class<?> interf : interfaces){
            System.out.println(interf);
        }
    }

    @Test
    public void testScanClasses(){
        List<Class<?>> allClassByPackageName = ClassUtil.getAllClassByPackageName(SpringbootlearnApplication.class.getPackage());
    }
}
