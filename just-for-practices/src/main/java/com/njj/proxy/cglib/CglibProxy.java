package com.njj.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 倪佳俊
 * @date 2023/05/12 20:38
 **/
public class CglibProxy implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("I'm cglib proxy");
//        methodProxy.invoke(o,objects);
        methodProxy.invokeSuper(o,objects);
        System.out.println("cglib end");
        return o;
    }
}
