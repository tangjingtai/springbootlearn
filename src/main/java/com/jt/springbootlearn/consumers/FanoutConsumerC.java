package com.jt.springbootlearn.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.C",containerFactory = "multiListenerContainer")
public class FanoutConsumerC {
    @RabbitHandler
    public void process(Map testMessage){
        System.out.println("FanoutConsumerC消费者收到消息  : " + testMessage.toString());
        try {
            Thread.sleep(1 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
