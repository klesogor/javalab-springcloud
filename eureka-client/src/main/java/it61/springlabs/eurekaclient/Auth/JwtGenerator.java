package it61.springlabs.eurekaclient.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import it61.springlabs.data.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Date;


@RefreshScope
@Component
public class JwtGenerator {
    @Value("auth.jwt_secret")
    private String JWT_SECRET;
    @Value("auth.token_type")
    private String TOKEN_TYPE;
    @Value("auth.token_issuer")
    private String TOKEN_ISSUER;
    @Value("auth.token_audience")
    private String TOKEN_AUDIENCE;

    public String generate(User user){
        byte[] signingKey = JWT_SECRET.getBytes();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .compact();
    }
}
