/*
package com.spring.bot.activeMQ;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Slf4j
@Service(value = "createDocConsumer")
public class CreateDocConsumer implements SessionAwareMessageListener {

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
        String text = msg.getText();
        log.info("收到信息"+text);
    }
}
*/
