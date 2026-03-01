package danieltsuzuki.com.github.servicodestreaming.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class JwtService {
    private final Algorithm ALGORITHM;
    private final String issuer;
    private final long expirationInMinutes;

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.issuer}") String issuer,
                      @Value("${app.jwt.expiration-minutes}") long expirationInMinutes) {
        this.ALGORITHM = Algorithm.HMAC256(secret);
        this.issuer = issuer;
        this.expirationInMinutes = expirationInMinutes;
    }

    public String generateToken(String subject, List<String> roles) {
        Instant now = Instant.now();
        Instant exp = now.plus(expirationInMinutes, ChronoUnit.MINUTES);
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(ALGORITHM);
    }

    public DecodedJWT verify(String token) throws JWTVerificationException {
        return JWT.require(ALGORITHM)
                .withIssuer(issuer)
                .build()
                .verify(token);
    }

}
