package com.tssweb.listener;

import com.tssweb.netty.DataEngine;
import com.tssweb.netty.NettyService;
import com.tssweb.timer.OutTask;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * Created by xiaodj on 2018/11/27.
 */
@Component
public class NettyServiceListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //开启定时任务
        Timer timer = new Timer();
        OutTask outTask = new OutTask();
        timer.scheduleAtFixedRate(outTask, 0, 5*1000);
        new Thread(new DataEngine()).start();
        new Thread(new NettyService(10240)).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
