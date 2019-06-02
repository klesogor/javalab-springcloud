package it61.springlabs.eurekaclient.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import it61.springlabs.eurekaclient.entities.User;

import java.util.Date;

public class JwtGenerator {
    private AuthConfigProvider config;

    public JwtGenerator(AuthConfigProvider config) {
        this.config = config;
    }

    public String generate(User user){
        byte[] signingKey = config.getJWT_SECRET().getBytes();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS256)
                .setHeaderParam("typ", config.getTOKEN_TYPE())
                .setIssuer(config.getTOKEN_ISSUER())
                .setAudience(config.getTOKEN_AUDIENCE())
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000))
                .claim("rol",user.getRolesDecoded())
                .claim("user_id", user.getId().toString())
                .compact();
    }
}
