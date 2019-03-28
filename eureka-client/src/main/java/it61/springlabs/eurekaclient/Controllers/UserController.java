package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.eurekaclient.DTO.UserDTO;
import it61.springlabs.eurekaclient.DTO.UserResponse;
import it61.springlabs.eurekaclient.Services.HostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public final class UserController implements CRUDControllerInterface<UserResponse, UserDTO> {

    private HostingService client;
    @Autowired
    public UserController(HostingService client)
    {
        this.client = client;
    }

    @Override
    @GetMapping("/api/v1/users")
    @ResponseBody
    public Iterable<UserResponse> getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer perPage) {
        return client.getAllUsers(page,perPage);
    }

    @Override
    @GetMapping("/api/v1/users/{id}")
    @ResponseBody
    public UserResponse findById(@PathVariable UUID id){
        return client.findUserById(id);
    }

    @Override
    @PostMapping("/api/v1/users")
    @ResponseBody
    public UserResponse create(@ModelAttribute UserDTO dto) {
        return client.createUser(dto);
    }

    @Override
    @PutMapping("/api/v1/users/{id}")
    @ResponseBody
    public UserResponse update(@PathVariable UUID id, @ModelAttribute UserDTO dto) {
        return client.updateUser(id,dto);

    }

    @DeleteMapping("/api/v1/users/{id}")
    @Override
    public void delete(@PathVariable UUID id) {
        client.deleteUser(id);
    }

}
