package it61.springlabs.eurekaclient.Auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

public class JwtToken implements Authentication {

    private JwtTokenDetails principle;

    public JwtToken(String username, Collection<? extends GrantedAuthority> authorities, UUID userId) {
        this.principle = new JwtTokenDetails(username,authorities,userId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return principle.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return principle;
    }

    @Override
    public Object getDetails() {
        return principle;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
