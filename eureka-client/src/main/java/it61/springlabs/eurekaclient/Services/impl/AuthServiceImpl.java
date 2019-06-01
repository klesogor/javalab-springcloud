package it61.springlabs.eurekaclient.Services.impl;

import it61.springlabs.data.dto.user.UserRegistrationDTO;
import it61.springlabs.data.entities.User;
import it61.springlabs.data.exceptions.AuthException;
import it61.springlabs.eurekaclient.DAL.UserRepository;
import it61.springlabs.eurekaclient.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class AuthServiceImpl implements AuthService {

    private PasswordEncoder encoder;
    private UserRepository userRepo;

    @Autowired
    public AuthServiceImpl(PasswordEncoder encoder, UserRepository userRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    @Override
    public User TryAuthUser(String login, String password) {
        User user = userRepo.findUserByUsername(login).orElseThrow(() -> AuthException.of("No user matches credentials"));
        if(!encoder.matches(password,user.getPassword())){
            throw AuthException.of("No user matches credentials");
        }
        return user;
    }

    @Override
    public User RegisterUser(UserRegistrationDTO dto) {
        boolean exists = userRepo.findUserByUsername(dto.getUsername()).isPresent();
        if(exists){
            throw AuthException.of("User with this username already exists!");
        }
        User user = new User
                (
                        UUID.randomUUID(),
                        dto.getUsername(),
                        encoder.encode(dto.getPassword()),
                        dto.getSecret(),
                        dto.isAdmin() ? "admin" : "user"
                );
        return userRepo.save(user);
    }
}
