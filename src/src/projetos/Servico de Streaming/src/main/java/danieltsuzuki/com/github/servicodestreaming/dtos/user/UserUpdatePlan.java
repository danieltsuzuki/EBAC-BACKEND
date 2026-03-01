package danieltsuzuki.com.github.servicodestreaming.dtos.user;

import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record UserUpdatePlan(
        @Enumerated(EnumType.STRING)
        @NotNull
        PlanTypeEnum planType
) {
}
