package it61.springlabs.eurekaclient.Services;

import feign.Headers;
import it61.springlabs.eurekaclient.DTO.UserDTO;
import it61.springlabs.eurekaclient.DTO.UserResponse;
import it61.springlabs.eurekaclient.DTO.VPSResponse;
import it61.springlabs.eurekaclient.DTO.VpsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("hosting-app")
public interface HostingService {
    @GetMapping(value = "/api/v1/users?page={page}&perPage={perPage}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Iterable<UserResponse> getAllUsers(@PathVariable("page") Integer page, @PathVariable("perPage") Integer perPage);

    @GetMapping(value = "/api/v1/users/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    UserResponse findUserById(@PathVariable UUID id);

    @PostMapping(value = "/api/v1/users",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    UserResponse createUser(UserDTO dto);

    @PutMapping(value = "/api/v1/users/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    UserResponse updateUser(@PathVariable UUID id, UserDTO dto);

    @DeleteMapping(value = "/api/v1/users/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    void deleteUser(@PathVariable UUID id);

    @GetMapping(value="/api/v1/vps?page={page}&perPage={perPage}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Iterable<VPSResponse> getAllVps(@PathVariable("page") Integer page, @PathVariable("perPage") Integer perPage);

    @GetMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    VPSResponse findVpsById(@PathVariable(name = "id") UUID id);

    @PostMapping(value="/api/v1/vps",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    VPSResponse createVps(VpsDTO dto);

    @PutMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    VPSResponse updateVps(@PathVariable(name = "id") UUID id, VpsDTO dto);

    @DeleteMapping(value="/api/v1/vps/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    void deleteVps(@PathVariable(name = "id") UUID id);
}
