package it61.springlabs.eurekaclient.Controllers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmqpController {
    private AmqpTemplate template;

    public AmqpController(@Autowired AmqpTemplate template) {
        this.template = template;
    }

    @GetMapping(value = "/amqp/hello")
    public void HelloWorld(@RequestParam(name = "message") String message){
        template.convertAndSend(message);
    }
}
