package com.michael.springBoot.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by jsflz on 2018/8/2.
 * RabbitMQ sender
 *
 * @author cj
 */
@Component
public class TestSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "@@@hello " + new Date();
        System.out.println("@@@context : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }



}
