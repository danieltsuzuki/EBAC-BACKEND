package danieltsuzuki.com.github.servicodestreaming.dtos.user;

import danieltsuzuki.com.github.servicodestreaming.dtos.account.AccountDto;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.enums.PerfilEnum;
import danieltsuzuki.com.github.servicodestreaming.enums.UserTypeEnum;

import java.util.UUID;

public record UserDetailsDto(
        UUID id,
        String email,
        String name,
        int age,
        PerfilEnum perfil,
        UserTypeEnum userTypeEnum,
        AccountDto account
) {
    public UserDetailsDto(User user) {
        this(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.getAge(),
            user.getPerfil(),
            user.getUserType(),
            new AccountDto(
                    user.getAccount().getId(),
                    user.getAccount().getPlanType(),
                    user.getAccount().getSubscriberUser() != null ? user.getAccount().getSubscriberUser().getId() : null
            )
        );
    }
}
