package it61.springlabs.eurekaclient.Controllers;

import DTO.InboundMessageDto;
import DTO.ResultDto;
import DTO.TicketReadDto;
import DTO.TicketWriteDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AmqpController {
    private AmqpTemplate template;

    public AmqpController(@Autowired AmqpTemplate template) {
        this.template = template;
    }

    @GetMapping(value = "/amqp/hello")
    public String HelloWorld(@RequestParam(name = "message", defaultValue = "default hello world") String message){
        template.convertAndSend("HelloWorld", message);
        return "Message sent!";
    }

    @PostMapping(value = "/amqp/create")
    public TicketReadDto CreateTicket(@ModelAttribute TicketWriteDto dto) {
         ResultDto<TicketReadDto> result = (ResultDto<TicketReadDto>)template.convertSendAndReceive("CreateTicket", new InboundMessageDto<>(dto, ""));
         return result.getData();
    }

    @DeleteMapping(value = "/amqp/{id}")
    public String CloseTicket(@PathVariable(name = "id") UUID uuid) {
        template.convertSendAndReceive("CloseTicket", new InboundMessageDto<>(uuid, ""));
        return "Deleted successfully";
    }

    @GetMapping(value = "/amqp/byVps/{VpsId}")
    public Iterable<TicketReadDto> GetTicketByVps(@PathVariable(name = "VpsId") UUID id){
        ResultDto<Iterable<TicketReadDto>> result = (ResultDto<Iterable<TicketReadDto>>)template
                .convertSendAndReceive("GetVps", new InboundMessageDto<>(id,""));
        return  result.getData();
    }

}
