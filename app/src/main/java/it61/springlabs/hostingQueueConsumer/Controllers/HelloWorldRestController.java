package it61.springlabs.hostingQueueConsumer.Controllers;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HelloWorldRestController {
    @RequestMapping("/hello")
    public String Hello(){
        return "Hello world from docker!";
    }
}
