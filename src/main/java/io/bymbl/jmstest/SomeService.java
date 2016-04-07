package io.bymbl.jmstest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeService {

    private final MessageProducer messageProducer;

    @Autowired
    public SomeService(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void sendMessage(@RequestParam("message") String message)   {
        messageProducer.send(message);
    }
}
