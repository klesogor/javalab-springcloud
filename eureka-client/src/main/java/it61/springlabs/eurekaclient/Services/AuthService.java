package it61.springlabs.eurekaclient.Services;

import it61.springlabs.data.dto.user.UserRegistrationDTO;
import it61.springlabs.data.entities.User;

import java.util.Optional;

public interface AuthService {
    User TryAuthUser(String login, String password);
    User RegisterUser(UserRegistrationDTO dto);
}
