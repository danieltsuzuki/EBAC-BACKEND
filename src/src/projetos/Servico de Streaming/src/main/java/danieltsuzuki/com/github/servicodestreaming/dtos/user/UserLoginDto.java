package danieltsuzuki.com.github.servicodestreaming.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserLoginDto(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Length(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        String password
) {}
