package com.michael.springBoot;

import com.michael.springBoot.filters.TestFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ 配置类
 * @author cuiji
 */
//@Configuration
public class RabbitMqConfiguration {

    // 交换空间名称
    public static final String EXCHANGE = "mldn.microboot.exchange";
    // 设置路由keyl
    public static final String ROUTINGKEY = "mldn.microboot.routingkey";
    // 队列名称
    public static final String QUEUE_NAME = "jsflzhong.queue1";

   /* @Bean
    public Binding bindingExchangeQueue(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY) ;
    }

    @Bean
    public DirectExchange getDirectExchange() { // 使用直连的模式
        return new DirectExchange(EXCHANGE, true, true);
    }*/

    //@Bean
    public Queue queue() { // 要传件的队列信息
        return new Queue(QUEUE_NAME);
    }

}
