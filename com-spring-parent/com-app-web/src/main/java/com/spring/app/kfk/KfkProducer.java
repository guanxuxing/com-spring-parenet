package com.spring.app.kfk;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * Created by Administrator on 2018-11-13.
 */
@Component
public class KfkProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMsg() throws Exception{
        SendResult result = kafkaTemplate.send("zhhtest", "name", "abc123").get();
        System.out.println("send.msg **  -- " + result.getRecordMetadata().topic() + " , " + result.getProducerRecord().value().toString());

    }
}
