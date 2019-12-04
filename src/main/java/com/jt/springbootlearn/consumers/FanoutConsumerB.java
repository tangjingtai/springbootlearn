package com.jt.springbootlearn.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.B", containerFactory = "multiListenerContainer")
public class FanoutConsumerB {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutConsumerB消费者收到消息  : " + testMessage.toString());
    }
}
