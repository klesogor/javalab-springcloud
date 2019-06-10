package it61.springlabs.queueConsumer.Controllers;

import it61.springlabs.data.dto.log.LogDto;
import it61.springlabs.data.generic.Response;
import it61.springlabs.queueConsumer.dal.LogRepository;
import it61.springlabs.queueConsumer.entities.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class LogsController {
    private LogRepository repo;
    @Autowired
    public LogsController(LogRepository repo){
        this.repo = repo;
    }

    @GetMapping("/api/v1/logs")
    public Response<Iterable<LogDto>> getLogs(){
        return Response.Of(StreamSupport.stream(repo.findAll().spliterator(),false)
                .map(Log::toDto).collect(Collectors.toList()));
    }
}
