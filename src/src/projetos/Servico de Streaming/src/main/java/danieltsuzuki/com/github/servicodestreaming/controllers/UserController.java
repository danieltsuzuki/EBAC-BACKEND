package danieltsuzuki.com.github.servicodestreaming.controllers;

import danieltsuzuki.com.github.servicodestreaming.configs.OpenApiConfig;
import danieltsuzuki.com.github.servicodestreaming.dtos.account.LinkedAccountsDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserDetailsDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserRegisterDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserRegisterResponseDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserUpdatePlan;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.services.RegistrationService;
import danieltsuzuki.com.github.servicodestreaming.services.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
@RequestMapping("/users")
@RestController
public class UserController {
    private final RegistrationService registrationService;
    private final UsersService usersService;

    public UserController(RegistrationService registrationService, UsersService usersService) {
        this.registrationService = registrationService;
        this.usersService = usersService;
    }

    @GetMapping("/me")
    public ResponseEntity getUserInfo(@AuthenticationPrincipal User currentUser) {
        UserDetailsDto user = usersService.getUserDetails(currentUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me/linked-accounts")
    @PreAuthorize("hasRole('SUBSCRIBER')")
    public ResponseEntity<LinkedAccountsDto> getLinkedAccounts(@AuthenticationPrincipal User currentUser) {
        List<UserDetailsDto> linkedAccounts = usersService.getLinkedAccounts(currentUser);
        return ResponseEntity.ok(new LinkedAccountsDto(linkedAccounts, linkedAccounts.size()));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('SUBSCRIBER') or hasRole('LINKED')")
    public ResponseEntity deleteUser(@AuthenticationPrincipal User currentUser) {
        String message = usersService.deleteUser(currentUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }

    @PostMapping("/register-user-linked")
    @PreAuthorize("hasRole('SUBSCRIBER')")
    public ResponseEntity<UserRegisterResponseDto> registerUserLinked(@AuthenticationPrincipal User currentUser, @RequestBody @Valid UserRegisterDto dto) {
        UserRegisterResponseDto response = registrationService.registerLinkedUser(dto, currentUser);
        return ResponseEntity.created(URI.create("/users")).body(response);
    }

    @PutMapping("/update-plan")
    @PreAuthorize("hasRole('SUBSCRIBER')")
    public ResponseEntity updatePlan(@AuthenticationPrincipal User currentUser, @RequestBody @Valid UserUpdatePlan dto) {
        usersService.updatePlanType(currentUser, dto.planType());
        return ResponseEntity.ok("Plan updated successfully");
        }
}
