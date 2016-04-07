package io.bymbl.jmstest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.util.FileSystemUtils;

import javax.jms.ConnectionFactory;
import java.io.File;

@SpringBootApplication
@EnableJms
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Bean
    JmsListenerContainerFactory<?> deJmsContainerFactory(ConnectionFactory connectionFactory, JmsErrorHandler errorHandler) {
        LOGGER.info("Creating factory");
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(errorHandler);
        return factory;
    }

    public static void main(String[] args) {
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        SpringApplication.run(Application.class, args);
        LOGGER.info("Application started");
    }

}
