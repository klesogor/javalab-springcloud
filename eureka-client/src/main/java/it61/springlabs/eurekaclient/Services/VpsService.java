package it61.springlabs.eurekaclient.Services;

import feign.Headers;
import it61.springlabs.data.dto.vps.VpsReadDto;
import it61.springlabs.data.dto.vps.VpsWriteDTO;
import it61.springlabs.data.generic.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("hosting-app")
public interface VpsService {
    @GetMapping(value="/api/v1/vps?page={page}&perPage={perPage}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<Iterable<VpsReadDto>> getAllVps(@PathVariable("page") Integer page, @PathVariable("perPage") Integer perPage);

    @GetMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<VpsReadDto> findVpsById(@PathVariable(name = "id") UUID id);

    @PostMapping(value="/api/v1/vps",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<VpsReadDto> createVps(VpsWriteDTO dto);

    @PutMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<VpsReadDto> updateVps(@PathVariable(name = "id") UUID id, VpsWriteDTO dto);

    @DeleteMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    void deleteVps(@PathVariable(name = "id") UUID id);

    @GetMapping(value="/api/v1/vps/byUser/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Response<Iterable<VpsReadDto>> findVpsByUser(@PathVariable(name = "id") UUID userId);
}
