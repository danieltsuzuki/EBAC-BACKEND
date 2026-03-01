package danieltsuzuki.com.github.servicodestreaming.dtos.subscription;

import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record SubscriptionSubscriberDto(
        @Enumerated(EnumType.ORDINAL)
        @NotNull
        PlanTypeEnum planType
) {
}
