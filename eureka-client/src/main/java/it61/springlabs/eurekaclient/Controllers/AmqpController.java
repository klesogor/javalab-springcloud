package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.utility.InboundMessageDto;
import it61.springlabs.data.dto.ResultDto;
import it61.springlabs.data.dto.ticket.TicketWriteDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
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

    @PostMapping(value = "/amqp")
    public String CreateTicket(@ModelAttribute TicketWriteDto dto) {
         ResultDto<String> res = (ResultDto<String>)template.convertSendAndReceive("CreateTicket", new InboundMessageDto<>(dto, ""));
         return res.getData();
    }

    @DeleteMapping(value = "/amqp/{id}")
    public String CloseTicket(@PathVariable(name = "id") UUID uuid) {
        template.convertAndSend("CloseTicket", new InboundMessageDto<>(uuid, ""));
        return "Deleted successfully";
    }
}
