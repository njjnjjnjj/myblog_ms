package com.njj.proxy.jdk;

import com.njj.proxy.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理类
 *
 * @author 倪佳俊
 * @date 2023/05/12 20:00
 **/
public class JdkProxyServiceImp implements InvocationHandler {

    private Service target;

    public JdkProxyServiceImp(Service target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("I'm " + proxy.getClass());
        System.out.println("I'm jdkProxy !");
        Object invoke = method.invoke(target, args);
        System.out.println("invoked");
        return invoke;
    }
}
