package com.tssweb.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        //assertContextInjected();
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static Object getBean(String name) {
        //assertContextInjected();
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        //assertContextInjected();
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

    public static void clearHolder() {
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

//    private static void assertContextInjected() {
//        Validate.setVisibility(View.VISIBLE);.validState(applicationContext != null,	"applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
//    }

}
