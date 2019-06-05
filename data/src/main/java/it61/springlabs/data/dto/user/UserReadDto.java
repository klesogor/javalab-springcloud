package it61.springlabs.data.dto.user;

import it61.springlabs.data.dto.vps.VpsReadDto;

import java.util.List;
import java.util.UUID;

public class UserReadDto {
    private UUID id;
    private String username;
    private List<String> roles;
    private AccountDetailsDto accountDetails;
    private List<VpsReadDto> vps;

    public UserReadDto(UUID id, String username, List<String> roles, AccountDetailsDto accountDetails, List<VpsReadDto> vps) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.accountDetails = accountDetails;
        this.vps = vps;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public AccountDetailsDto getAccountDetails() {
        return accountDetails;
    }

    public List<VpsReadDto> getVps() {
        return vps;
    }
}
