package it61.springlabs.eurekaclient.Services;

import feign.Headers;
import it61.springlabs.data.dto.vps.VpsDTO;
import it61.springlabs.data.entities.Vps;
import it61.springlabs.data.generic.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("hosting-app")
public interface VpsService {
    @GetMapping(value="/api/v1/vps?page={page}&perPage={perPage}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<Iterable<Vps>> getAllVps(@PathVariable("page") Integer page, @PathVariable("perPage") Integer perPage);

    @GetMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<Vps> findVpsById(@PathVariable(name = "id") UUID id);

    @PostMapping(value="/api/v1/vps",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<Vps> createVps(VpsDTO dto);

    @PutMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<Vps> updateVps(@PathVariable(name = "id") UUID id, VpsDTO dto);

    @DeleteMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    void deleteVps(@PathVariable(name = "id") UUID id);
}
