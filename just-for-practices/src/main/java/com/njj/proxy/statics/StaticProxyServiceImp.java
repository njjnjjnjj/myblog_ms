package com.njj.proxy.statics;

import com.njj.proxy.Service;
import com.njj.proxy.ServiceImp;

/**
 * @author 倪佳俊
 * @date 2023/05/12 19:51
 **/
public class StaticProxyServiceImp implements Service {

    private Service service = new ServiceImp();

    @Override
    public void run() {
        System.out.println("I'm static proxy");
        service.run();
    }
}
