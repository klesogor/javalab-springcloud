package it61.springlabs.data.dto.user;

import javax.validation.constraints.NotNull;

public class UserLoginDto {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginDto() {
    }
}
