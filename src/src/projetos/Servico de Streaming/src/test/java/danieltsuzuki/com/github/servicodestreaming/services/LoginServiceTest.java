package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserLoginDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserLoginResponseDto;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.RoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtService jwtService;
    LoginService loginService;
    long expirationInMinutes;
    UserLoginDto userLoginDto;
    Authentication authentication;
    List<String> roles;

    @BeforeEach
    void setUp() {
        expirationInMinutes = 30L;
        loginService = new LoginService(authenticationManager, jwtService, expirationInMinutes);
        userLoginDto = new UserLoginDto("teste@mail.com", "password123");
        authentication = new UsernamePasswordAuthenticationToken(
                userLoginDto.email().toLowerCase(),
                userLoginDto.password(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
    }


    @Test
    void login_ShouldReturnLoginSuccessful() {
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.email().toLowerCase(),
                userLoginDto.password()
        ))).thenReturn(authentication);
        when(jwtService.generateToken(userLoginDto.email(), roles)).thenReturn("mocked-jwt-token");

        UserLoginResponseDto response = loginService.login(userLoginDto);

        assertNotNull(response);
        assertEquals("mocked-jwt-token", response.token());
        assertEquals("Bearer", response.tokenType());
        assertEquals(60L * expirationInMinutes, response.expiresInSeconds());
    }

    @Test
    void login_ShouldThrowException_RoleNotFoundException() {
        authentication = new UsernamePasswordAuthenticationToken(
                userLoginDto.email().toLowerCase(),
                userLoginDto.password(),
                Collections.emptyList()
        );

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        assertThrows(RoleNotFoundException.class, () -> loginService.login(userLoginDto));
    }

}