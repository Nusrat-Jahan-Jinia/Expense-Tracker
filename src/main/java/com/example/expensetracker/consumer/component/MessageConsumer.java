package com.example.expensetracker.consumer.component;


import com.example.expensetracker.entity.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @JmsListener(destination = "expense-queue")
    public void messageLister(SystemMessage systemMessage){
        LOGGER.info("Message received, {}", systemMessage);
    }

}
