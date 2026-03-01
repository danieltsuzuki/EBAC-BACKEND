package danieltsuzuki.com.github.servicodestreaming.dtos.user;

import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record UserSubscriberRegisterDto(
        @NotBlank(message = "Name is mandatory")
        String name,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is mandatory")
        String email,

        @NotBlank(message = "Password is mandatory")
        @Length(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        String password,

        @Positive(message = "Age must be a positive number")
        @Max(value = 120, message = "Age must be less than or equal to 120")
        int age,

        @Enumerated(EnumType.STRING)
        @NotNull
        PlanTypeEnum planType
) {}
