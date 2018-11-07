package com.spring.bot.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2018-11-07.
 */
public class KfkProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.1.102:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 1024*1024*1024);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 1024*1024*1024);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < 100; i++) {
                String msg = "Message " + i;
                List<String> list = new ArrayList<String>();
                for (int j =0 ; j<10; j++)  {
                    list.add("1234567890qwe");
                }
                producer.send(new ProducerRecord<String, String>("zhhtest", list.toString()+msg));
                System.out.println("Sent-------- :" + (list.toString()+msg).length());
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }
    }
}


