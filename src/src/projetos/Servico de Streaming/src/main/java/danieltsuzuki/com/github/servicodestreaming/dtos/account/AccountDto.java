package danieltsuzuki.com.github.servicodestreaming.dtos.account;

import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;

import java.util.UUID;

public record AccountDto (
         UUID id,

         PlanTypeEnum planType,

         UUID subscriberUserId

) {
    public AccountDto(UUID id, PlanTypeEnum planType, UUID subscriberUserId) {
        this.id = id;
        this.planType = planType;
        this.subscriberUserId = subscriberUserId;
    }
}
