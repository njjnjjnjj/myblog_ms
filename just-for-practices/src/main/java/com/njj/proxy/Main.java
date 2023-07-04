package com.njj.proxy;

import com.njj.proxy.cglib.CglibProxy;
import com.njj.proxy.jdk.JdkProxyServiceImp;
import com.njj.proxy.statics.StaticProxyServiceImp;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 代理模式 静态代理 动态代理 主方法
 *
 * @author 倪佳俊
 * @date 2023/05/12 19:52
 **/
public class Main {
    public static void main(String[] args) {
        // 静态代理，使用 设计模式——代理模式
//        staticProxy();
        // jdk动态代理，被代理的类需要实现接口
//        jdkProxy();
        // cglib动态代理，被代理的类无需实现任何接口，需要引入第三方库，通过字节码生成技术实现代理，其本质是生成被代理类的子类，以扩展父类接口功能
//        cglibProxy();
    }

    private static void staticProxy(){
        Service service = new StaticProxyServiceImp();
        service.run();
    }

    private static void jdkProxy(){
        InvocationHandler invocationHandler = new JdkProxyServiceImp(new ServiceImp());
        Service service = (Service) Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{Service.class}, invocationHandler);
        service.run();
    }

    private static void cglibProxy(){
        // 创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(ServiceNoImp.class.getClassLoader());
        enhancer.setSuperclass(ServiceNoImp.class);
        enhancer.setCallback(new CglibProxy());
        ServiceNoImp noImp = (ServiceNoImp) enhancer.create();
        noImp.run();
    }
}
