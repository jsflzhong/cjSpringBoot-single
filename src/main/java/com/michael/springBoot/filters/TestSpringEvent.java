package com.michael.springBoot.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

/**
 * Created by jsflz on 2018/8/22.
 * 测试在Spring中定义了的五个标准事件
 */
//@Component  //Terminated for now
public class TestSpringEvent implements ApplicationListener{

    private static final Logger log = LoggerFactory.getLogger(TestSpringEvent.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        if (event instanceof ContextStartedEvent){
            System.out.println("================:{}" + "ContextStartedEvent");
        }
        if (event instanceof ContextRefreshedEvent){
            System.out.println("================:{}"+ "ContextRefreshedEvent");
        }
        if (event instanceof ContextClosedEvent){
            System.out.println("================:{}"+ "ContextClosedEvent");
        }
        if (event instanceof ContextStoppedEvent){
            System.out.println("================:{}"+ "ContextStoppedEvent");
        }
        if (event instanceof EmbeddedServletContainerInitializedEvent){
            System.out.println("================:{}"+ "EmbeddedServletContainerInitializedEvent");
        }
        if (event instanceof ApplicationReadyEvent){
            System.out.println("================:{}"+ "ApplicationReadyEvent");
        }
        System.out.println(">>>>>>>>>>>>>>>>:{}\n"+ event.getClass().getName());
    }
}
