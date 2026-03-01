package danieltsuzuki.com.github.servicodestreaming.controllers;

import danieltsuzuki.com.github.servicodestreaming.dtos.user.*;
import danieltsuzuki.com.github.servicodestreaming.services.LoginService;
import danieltsuzuki.com.github.servicodestreaming.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final LoginService loginService;
    public AuthController(RegistrationService registrationService,
                          LoginService loginService) {
        this.registrationService = registrationService;
        this.loginService = loginService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@Valid @RequestBody
                                                            UserSubscriberRegisterDto dto) {
        return ResponseEntity.created(URI.create("/users")).body(registrationService.register(dto));
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody
                                                      UserLoginDto dto) {
        return ResponseEntity.ok(loginService.login(dto));
    }
}
