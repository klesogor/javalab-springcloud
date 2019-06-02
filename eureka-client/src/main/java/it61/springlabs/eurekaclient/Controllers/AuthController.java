package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.user.UserLoginDto;
import it61.springlabs.data.dto.user.UserRegistrationDTO;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Auth.JwtGenerator;
import it61.springlabs.eurekaclient.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService auth;
    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthService auth, JwtGenerator jwtGenerator) {
        this.auth = auth;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping(value = "/api/v1/auth/login")
    public Response<String> login(@RequestBody UserLoginDto dto){
        return Response.Of(jwtGenerator.generate(auth.TryAuthUser(dto.getUsername(), dto.getPassword())));
    }

    @PostMapping(value = "/api/v1/auth/register")
    public Response<String> register(@RequestBody UserRegistrationDTO dto){
        return Response.Of(jwtGenerator.generate(auth.RegisterUser(dto)));
    }
}
