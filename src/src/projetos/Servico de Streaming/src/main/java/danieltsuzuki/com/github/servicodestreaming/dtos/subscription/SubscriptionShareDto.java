package danieltsuzuki.com.github.servicodestreaming.dtos.subscription;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubscriptionShareDto(
        @NotNull
        boolean authorizeContentAdult,
        @NotBlank
        String idUserLinked
) {
}
