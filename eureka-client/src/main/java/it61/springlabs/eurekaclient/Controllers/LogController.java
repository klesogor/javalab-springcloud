package it61.springlabs.eurekaclient.Controllers;

import feign.Headers;
import it61.springlabs.data.dto.log.LogDto;
import it61.springlabs.data.generic.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FeignClient("rabbit-mq-consumer")
public interface LogController {
    @GetMapping("/api/v1/logs")
    @Headers("Content-Type: application/json")
    public Response<Iterable<LogDto>> logs();
}
