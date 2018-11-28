package com.tssweb.listener;

import com.tssweb.netty.NettyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by xiaodj on 2018/11/27.
 */
public class NettyServiceListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new Thread(new NettyService(8808)).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
