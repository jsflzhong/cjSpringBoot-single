package com.michael.springBoot.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by jsflz on 2018/8/2.
 * RabbitMq receiver
 *
 * @author cj
 */
@Component
//This name should be as same as the config in the mq sender!
@RabbitListener(queues = "spirng-boot-rabbitmq")
public class TestReceiver {

    @RabbitHandler
    public void process1(String hello) {
        System.out.println("@@@Receiver1 : " + hello);
    }

    @RabbitHandler
    public void process2(String hello) {
        System.out.println("@@@Receiver2 : " + hello);
    }
}
