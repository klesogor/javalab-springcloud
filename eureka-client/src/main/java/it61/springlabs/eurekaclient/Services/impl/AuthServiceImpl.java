package it61.springlabs.eurekaclient.Services.impl;

import it61.springlabs.data.dto.user.UserRegistrationDTO;
import it61.springlabs.data.exceptions.NotFoundException;
import it61.springlabs.eurekaclient.dal.AccountDetailsRepository;
import it61.springlabs.eurekaclient.entities.AccountDetails;
import it61.springlabs.eurekaclient.entities.User;
import it61.springlabs.data.exceptions.AuthException;
import it61.springlabs.eurekaclient.Services.AuthService;
import it61.springlabs.eurekaclient.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public final class AuthServiceImpl implements AuthService {

    private PasswordEncoder encoder;
    private UserRepository userRepo;
    private AccountDetailsRepository detailsRepo;

    @Autowired
    public AuthServiceImpl(PasswordEncoder encoder, UserRepository userRepo, AccountDetailsRepository detailsRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
        this.detailsRepo = detailsRepo;
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
        AccountDetails details = new AccountDetails();
        details.setId(UUID.randomUUID());
        details = detailsRepo.save(details);
        user.setAccountDetails(details);
        return userRepo.save(user);
    }

    @Override
    public void DeleteUser(UUID id) {
        User user = userRepo.findById(id).orElseThrow(() -> NotFoundException.of(id,"user"));
        user.getAccountDetails().setDeleted(true);
        user.setDeleted(true);
        userRepo.save(user);
    }
}
