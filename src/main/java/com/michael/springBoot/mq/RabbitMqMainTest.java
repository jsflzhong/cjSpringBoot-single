package com.michael.springBoot.mq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner; //需要删除pom中该依赖的版本号!否则引入时会报错.

/**
 * Created by jsflz on 2018/8/2.
 * Test class for RabbitMq
 *
 * @author cj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqMainTest {

    @Autowired
    private TestSender testSender; //rabbitmq的测试用的消息生产者.
    @Autowired
    private TestReceiver testReceiver; //rabbitmq的测试用的消息消费者.

    @Test
    public void RabbitMqSender() {
        testSender.send();
    }

}
