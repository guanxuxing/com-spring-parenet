package com.spring.app.kfk;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * Created by Administrator on 2018-11-13.
 */
public class KfkConsumer implements MessageListener<String, String> {

    public void onMessage(ConsumerRecord<String, String> record) {
        String topic = record.topic();
        String key = record.key();
        String value = record.value();
        long offset = record.offset();
        int partition = record.partition();

        StringBuffer buffer = new StringBuffer();
        buffer.append("topic: ").append(topic)
                .append(" ,key: ").append(key)
                .append(" ,value: ").append(value)
                .append(" ,offset: ").append(offset)
                .append(" , partition: ").append(partition);
        System.out.println("kfk.consumer.receive: " + buffer.toString());


    }
}
