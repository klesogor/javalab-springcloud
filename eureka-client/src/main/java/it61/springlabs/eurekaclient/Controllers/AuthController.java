package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.user.UserLoginDto;
import it61.springlabs.data.dto.user.UserReadDto;
import it61.springlabs.data.dto.user.UserRegistrationDTO;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Auth.JwtGenerator;
import it61.springlabs.eurekaclient.Auth.JwtTokenDetails;
import it61.springlabs.eurekaclient.Services.AuthService;
import it61.springlabs.eurekaclient.Services.Logger;
import it61.springlabs.eurekaclient.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class AuthController {
    private AuthService auth;
    private JwtGenerator jwtGenerator;
    private Logger logger;

    @Autowired
    public AuthController(AuthService auth, JwtGenerator jwtGenerator, Logger logger) {
        this.auth = auth;
        this.jwtGenerator = jwtGenerator;
        this.logger = logger;
    }

    @PostMapping(value = "/api/v1/auth/login")
    public Response<String> login(@RequestBody UserLoginDto dto) {
        logger.info("Attempting login");
        return Response.Of(jwtGenerator.generate(auth.TryAuthUser(dto.getUsername(), dto.getPassword())));
    }

    @PostMapping(value = "/api/v1/auth/register")
    public Response<String> register(@RequestBody UserRegistrationDTO dto) {
        logger.info("Attempting register");
        return Response.Of(jwtGenerator.generate(auth.RegisterUser(dto)));
    }

    @GetMapping(value = "api/v1/me")
    public Response<UserReadDto> me() {
        JwtTokenDetails jwt = (JwtTokenDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Response.Of(new UserReadDto(
                        jwt.getUserId(),
                        jwt.getUsername(),
                        StreamSupport.stream(jwt.getAuthorities().spliterator(), false)
                                .map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.toList()),
                        null,
                        null
                )
        );
    }
}
