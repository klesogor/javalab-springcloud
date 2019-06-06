package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.user.UserReadDto;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private UserRepository userRepo;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping(value = "/api/v1/users")
    public Response<Iterable<UserReadDto>> users(){
        return Response.Of(userRepo.findAll().stream().map(u -> new UserReadDto(
                u.getId(),
                u.getUsername(),
                Arrays.asList(u.getRolesDecoded()),
                null,
                null
        )).collect(Collectors.toList()));
    }
}
