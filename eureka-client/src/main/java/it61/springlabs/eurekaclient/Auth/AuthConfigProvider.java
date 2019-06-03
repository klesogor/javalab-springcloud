package it61.springlabs.eurekaclient.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
public class AuthConfigProvider {

    private static Logger logger = LoggerFactory.getLogger("auth_logger");

    @Value("${auth.jwt_secret}")
    private String JWT_SECRET;
    @Value("${auth.token_type}")
    private String TOKEN_TYPE;
    @Value("${auth.token_issuer}")
    private String TOKEN_ISSUER;
    @Value("${auth.token_audience}")
    private String TOKEN_AUDIENCE;
    @Value("${auth.token_header}")
    private String TOKEN_HEADER;
    @Value("${auth.token_prefix}")
    private String TOKEN_PREFIX;

    public String getJWT_SECRET() {
        logger.info(JWT_SECRET);
        return JWT_SECRET;
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
