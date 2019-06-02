package it61.springlabs.eurekaclient.Controllers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class AmqpController {
    private AmqpTemplate template;
    private Logger logger = Logger.getLogger("Lmao logger factory");

    public AmqpController(@Autowired AmqpTemplate template) {
        this.template = template;
    }

    @GetMapping(value = "/amqp/hello")
    public String HelloWorld(@RequestParam(name = "message", defaultValue = "default hello world") String message){
        template.convertAndSend("HelloWorld", message);
        return "Message sent!";
    }
}
