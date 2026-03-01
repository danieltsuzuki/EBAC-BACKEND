package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserLoginDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserLoginResponseDto;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final long expirationInMinutes;


    public LoginService(AuthenticationManager authenticationManager,
                        JwtService jwtService,
                        @Value("${app.jwt.expiration-minutes}") long expirationInMinutes) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.expirationInMinutes = expirationInMinutes;
    }

    public UserLoginResponseDto login(UserLoginDto dto) {
        String normalizedEmail = dto.email().toLowerCase();

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(normalizedEmail, dto.password())
        );

        List<String> role = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        if (role.isEmpty())
            throw new RoleNotFoundException("User has no role assigned");


        String token = jwtService.generateToken(normalizedEmail, role);

        return new UserLoginResponseDto(token, "Bearer", 60L * expirationInMinutes);
    }
}
