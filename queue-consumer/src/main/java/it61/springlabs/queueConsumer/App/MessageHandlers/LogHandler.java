package it61.springlabs.queueConsumer.App.MessageHandlers;

import it61.springlabs.data.dto.log.LogDto;
import it61.springlabs.queueConsumer.dal.LogRepository;
import it61.springlabs.queueConsumer.entities.Log;
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
    public void Log(LogDto dto){
        logger.info("Adding log record...");
        repository.save(new Log(dto.getData(),dto.getCreated()));
    }
}