package it61.springlabs.eurekaclient.Services;

import it61.springlabs.eurekaclient.DTO.UserDTO;
import it61.springlabs.eurekaclient.DTO.UserResponse;
import it61.springlabs.eurekaclient.DTO.VPSResponse;
import it61.springlabs.eurekaclient.DTO.VpsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@FeignClient("hosting-app")
public interface HostingService {
    @GetMapping("/api/v1/users")
    Iterable<UserResponse> getAllUsers(Integer page, Integer perPage);

    @GetMapping("/api/v1/users/{id}")
    UserResponse findUserById(@PathVariable UUID id);

    @PostMapping("/api/v1/users")
    UserResponse createUser(UserDTO dto);

    @PutMapping("/api/v1/users/{id}")
    UserResponse updateUser(@PathVariable UUID id, UserDTO dto);

    @DeleteMapping("/api/v1/users/{id}")
    void deleteUser(@PathVariable UUID id);

    @GetMapping("/api/v1/vps")
    Iterable<VPSResponse> getAllVps(Integer page,Integer perPage);

    @GetMapping("/api/v1/vps/{id}")
    VPSResponse findVpsById(@PathVariable(name = "id") UUID id);

    @PostMapping("/api/v1/vps")
    VPSResponse createVps(VpsDTO dto);

    @PutMapping("/api/v1/vps/{id}")
    VPSResponse updateVps(@PathVariable(name = "id") UUID id, VpsDTO dto);

    @DeleteMapping("/api/v1/vps/{id}")
    void deleteVps(@PathVariable(name = "id") UUID id);
}
