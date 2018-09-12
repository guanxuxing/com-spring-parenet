/*
package com.spring.bot.activeMQ;

import com.alibaba.fastjson.JSONObject;
import com.spring.bot.dto.BaseDto;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service(value = "createDocProducer")
public class CreateDocProducer {
    @Resource(name = "producerJmsTemplate")
    private JmsTemplate producerJmsTemplate;

    @Resource(name = "createDocDestination")
    private ActiveMQQueue createDocDestination;

    public void sendMessage(final BaseDto baseDto) {
        MessageCreator messageCreator = new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(baseDto));
            }
        };
        producerJmsTemplate.send(createDocDestination, messageCreator);
    }
}
*/
