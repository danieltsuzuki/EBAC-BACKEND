package danieltsuzuki.com.github.servicodestreaming.services;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    String secret = "test-secret-123";
    String issuer = "myapp";
    long expirationInMinutes = 30L;
    String subject = "user123";
    List<String> roles = List.of("ROLE_USER");

    JwtService jwtService = new JwtService(secret, issuer, expirationInMinutes);

    @Test
    void Jwt_ShouldGenerateToken() {
        Instant before = Instant.now();
        String token = jwtService.generateToken(subject, roles);
        Instant after = Instant.now();

        assertNotNull(token);
        assertFalse(token.isBlank());

        DecodedJWT decoded = jwtService.verify(token);

        assertEquals(issuer, decoded.getIssuer());
        assertEquals(subject, decoded.getSubject());

        List<String> rolesToken = decoded.getClaim("roles").asList(String.class);
        assertEquals(roles, rolesToken);

        assertNotNull(decoded.getIssuedAt());
        assertNotNull(decoded.getExpiresAt());

        Instant iat = decoded.getIssuedAt().toInstant();
        Instant exp = decoded.getExpiresAt().toInstant();

        // iat deve estar entre before e after (com pequena tolerância)
        assertFalse(iat.isBefore(before.minus(2, ChronoUnit.SECONDS)));
        assertFalse(iat.isAfter(after.plus(2, ChronoUnit.SECONDS)));

        // exp deve ser aproximadamente iat + expirationInMinutes
        Instant expectedExpLowerBound = iat.plus(expirationInMinutes, ChronoUnit.MINUTES).minus(2, ChronoUnit.SECONDS);
        Instant expectedExpUpperBound = iat.plus(expirationInMinutes, ChronoUnit.MINUTES).plus(2, ChronoUnit.SECONDS);

        assertFalse(exp.isBefore(expectedExpLowerBound));
        assertFalse(exp.isAfter(expectedExpUpperBound));
    }

    @Test
    void verify_ShouldThrow_WhenSecretIsDifferent() {
        String issuer = "test-issuer";
        long expirationInMinutes = 10L;

        JwtService jwtService1 = new JwtService("secret-A", issuer, expirationInMinutes);
        JwtService jwtService2 = new JwtService("secret-B", issuer, expirationInMinutes);

        String token = jwtService1.generateToken("user@mail.com", List.of("ROLE_USER"));

        assertThrows(JWTVerificationException.class, () -> jwtService2.verify(token));
    }

    @Test
    void verify_ShouldThrow_WhenIssuerIsDifferent() {
        String secret = "test-secret-123";
        long expirationInMinutes = 10L;

        JwtService jwtService1 = new JwtService(secret, "issuer-A", expirationInMinutes);
        JwtService jwtService2 = new JwtService(secret, "issuer-B", expirationInMinutes);

        String token = jwtService1.generateToken("user@mail.com", List.of("ROLE_USER"));

        assertThrows(JWTVerificationException.class, () -> jwtService2.verify(token));
    }

    @Test
    void verify_ShouldThrow_WhenTokenIsExpired() {
        String secret = "test-secret-123";
        String issuer = "test-issuer";

        // expiração negativa: exp fica no passado
        JwtService jwtService = new JwtService(secret, issuer, -1L);

        String token = jwtService.generateToken("user@mail.com", List.of("ROLE_USER"));

        assertThrows(JWTVerificationException.class, () -> jwtService.verify(token));
    }

    @Test
    void verify_ShouldReturnDecodedJWT_WhenTokenIsValid() {
        JwtService jwtService = new JwtService("test-secret-123", "test-issuer", 5L);

        String token = jwtService.generateToken("abc@x.com", List.of("ROLE_USER"));

        DecodedJWT decoded = jwtService.verify(token);

        assertNotNull(decoded);
        assertEquals("test-issuer", decoded.getIssuer());
        assertEquals("abc@x.com", decoded.getSubject());
    }

}