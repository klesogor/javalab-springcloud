package it61.springlabs.hostingQueueConsumer.App.MessageHandlers;

import it61.springlabs.data.InboundMessageDto;
import it61.springlabs.data.ResultDto;
import it61.springlabs.data.TicketReadDto;
import it61.springlabs.data.TicketWriteDto;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.Ticket;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.UseCases.CloseTicket;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.UseCases.CreateTicket;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.UseCases.GetTickets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@EnableRabbit
@Component
public class TicketHandlers {

    public TicketHandlers(
            @Autowired CreateTicket createTicket,
            @Autowired CloseTicket closeTicket,
            @Autowired GetTickets getTickets
    ) {
        this.createTicket = createTicket;
        this.closeTicket = closeTicket;
        this.getTickets = getTickets;
    }

    private CreateTicket createTicket;
    private CloseTicket closeTicket;
    private GetTickets getTickets;
    private Logger log = LoggerFactory.getLogger("application");

    @RabbitListener(queues = "CreateTicket")
    public ResultDto<String> handleCreate(InboundMessageDto<TicketWriteDto> dto){
        Ticket ticket = this.createTicket.createTicket(dto.getData());
        return new ResultDto<>(200,null,ticket.getId().toString());
    }

    @RabbitListener(queues = "CloseTicket")
    public void handleClose(InboundMessageDto<UUID> dto){
        this.closeTicket.closeTicket(dto.getData(), new Date());
    }

    @RabbitListener(queues = "GetVps")
    public ResultDto<Iterable<TicketReadDto>> handleGet(InboundMessageDto<UUID> dto){
        Iterable<TicketReadDto> res = this.getTickets.getTicketsForVps(dto.getData());
        return new ResultDto<>(200,null,res);
    }

    @RabbitListener(queues = "HelloWorld")
    public void handleHelloWorld(String message){
        log.info("Received hello world message: " + message);
    }
}
