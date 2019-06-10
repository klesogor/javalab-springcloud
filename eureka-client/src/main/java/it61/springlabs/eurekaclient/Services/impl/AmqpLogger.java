package it61.springlabs.eurekaclient.Services.impl;

import it61.springlabs.data.dto.log.LogDto;
import it61.springlabs.eurekaclient.Services.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AmqpLogger implements Logger {
    private AmqpTemplate template;

    @Autowired
    public AmqpLogger(AmqpTemplate template) {
        this.template = template;
    }

    @Override
    public void info(String data) {
        template.convertAndSend("logs",new LogDto(data, new Date()));
    }
}
