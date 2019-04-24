package it61.springlabs.hostingQueueConsumer.App.MessageHandlers;

import it61.springlabs.data.LogDTO;
import it61.springlabs.hostingQueueConsumer.Domain.Log.Log;
import it61.springlabs.hostingQueueConsumer.Domain.Log.Repositories.LogRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@EnableRabbit
@Component
public class LogHandler {
    private LogRepository repository;
    private Logger logger = Logger.getLogger("LogHandler");
    public LogHandler(@Autowired LogRepository repository){
        this.repository = repository;
    }

    @RabbitListener(queues = "logs")
    public void Log(LogDTO dto){
        logger.info("Adding log record...");
        repository.save(new Log(dto.getEntity(),dto.getCreated()));
    }
}