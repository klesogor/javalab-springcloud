package it61.springlabs.eurekaclient.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {
    @Value("app.message")
    private String greeting;

    @GetMapping("/hello")
    public String greet(){
        return greeting;
    }
}
