package it61.springlabs.eurekaclient.Services;

import it61.springlabs.data.dto.user.UserRegistrationDTO;
import it61.springlabs.eurekaclient.entities.User;

import java.util.UUID;

public interface AuthService {
    User TryAuthUser(String login, String password);
    User RegisterUser(UserRegistrationDTO dto);
    void DeleteUser(UUID id);
}
