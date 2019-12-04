package com.jt.springbootlearn.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.A", containerFactory = "multiListenerContainer")
public class FanoutConsumerA {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutConsumerA消费者收到消息  : " + testMessage.toString());
    }
}
