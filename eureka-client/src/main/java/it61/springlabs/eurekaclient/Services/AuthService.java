package it61.springlabs.eurekaclient.Services;

import it61.springlabs.data.dto.user.UserRegistrationDTO;
import it61.springlabs.eurekaclient.entities.User;

public interface AuthService {
    User TryAuthUser(String login, String password);
    User RegisterUser(UserRegistrationDTO dto);
}
