package it61.springlabs.eurekaclient.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RefreshScope
@Component
public class AuthConfigProvider {

    private static Logger logger = LoggerFactory.getLogger("auth_logger");

    @Value("${jwt.jwt_secret:NO_SECRET}")
    private String secret;
    @Value("$jwt.token_type}")
    private String TOKEN_TYPE;
    @Value("${jwt.token_issuer}")
    private String TOKEN_ISSUER;
    @Value("${jwt.token_audience}")
    private String TOKEN_AUDIENCE;
    @Value("${jwt.token_header}")
    private String TOKEN_HEADER;
    @Value("${jwt.token_prefix}")
    private String TOKEN_PREFIX;

    public String getJWT_SECRET() {
        logger.info(secret);
        return secret;
    }

    public String getTOKEN_TYPE() {
        return TOKEN_TYPE;
    }

    public String getTOKEN_ISSUER() {
        return TOKEN_ISSUER;
    }

    public String getTOKEN_AUDIENCE() {
        return TOKEN_AUDIENCE;
    }

    public String getTOKEN_HEADER() {
        return TOKEN_HEADER;
    }

    public String getTOKEN_PREFIX() {
        return TOKEN_PREFIX;
    }
}
