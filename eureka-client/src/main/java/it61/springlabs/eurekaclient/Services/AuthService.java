package it61.springlabs.eurekaclient.Services;

import it61.springlabs.data.entities.User;

import java.util.Optional;

public interface AuthService {
    public Optional<User> authUser();
}
