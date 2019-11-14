package com.jt.springbootlearn;

import com.jt.springbootlearn.bean.concert.LiudehuaConcert;
import com.jt.springbootlearn.bean.concert.Performance;
import org.junit.Test;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTests {

    @Test
    public void testJDKDynamicProxy() throws IllegalAccessException, InstantiationException {
        Performance performance = new LiudehuaConcert();
        System.out.println("performance type: "+ performance.getClass().toString());
        performance.perform();
        ProxyFactory proxyFactory = new ProxyFactory(performance);
        performance = (Performance) proxyFactory.getProxyInstance();
        System.out.println("proxy performance type: "+ performance.getClass().toString());
        performance.perform();
    }

    static class ProxyFactory{
        //维护一个目标对象
        private Object target;
        public ProxyFactory(Object target){
            this.target=target;
        }

        public Object getProxyInstance(){
            Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println(""+method.getName()+"方法调用前拦截....");
                    Object retVal = method.invoke(target, args);
                    System.out.println(""+method.getName()+"方法调用完成....");
                    return retVal;
                }
            });
            return proxy;
        }
    }
}
