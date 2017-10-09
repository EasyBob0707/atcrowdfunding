package com.atguigu.atcrowdfunding.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by SunBo on 2017-07-28.
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    // 为了保证线程安全(使用静态)
    public static ApplicationContext applicationContext ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }
}
