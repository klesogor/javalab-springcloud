package it61.springlabs.eurekaclient.Auth;

import it61.springlabs.data.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RefreshScope
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Value("auth.login_url")
    private String AUTH_LOGIN_URL;
    @Value("auth.token_audience")
    private String TOKEN_HEADER;
    @Value("auth.token_audience")
    private String TOKEN_PREFIX;


    private AuthenticationManager authenticationManager;
    private JwtGenerator jwtGenerator;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        setFilterProcessesUrl(AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {
        User user = ((User) authentication.getPrincipal());

        response.addHeader(TOKEN_HEADER, TOKEN_PREFIX + jwtGenerator.generate(user));
    }
}
