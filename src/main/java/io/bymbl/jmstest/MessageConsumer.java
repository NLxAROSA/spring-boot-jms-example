package io.bymbl.jmstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @JmsListener(destination = "test", containerFactory = "deJmsContainerFactory")
    @Transactional(rollbackFor = RuntimeException.class)
    public void receiveMessage(String message)    {

        if ("rollback".equals(message)) {
            LOGGER.info("Rollback received: {}", message);
            // Something happened that prevented this message from being handled, do something useful with it.
            throw new RuntimeException("Rolling back!");
        }else{
            // Message handled succesfully
            LOGGER.info("Message received: {}", message);
        }
    }

}
