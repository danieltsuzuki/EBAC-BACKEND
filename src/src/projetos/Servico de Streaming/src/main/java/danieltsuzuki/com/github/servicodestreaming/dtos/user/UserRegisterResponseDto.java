package danieltsuzuki.com.github.servicodestreaming.dtos.user;

import java.util.UUID;

public record UserRegisterResponseDto(

    UUID id,
    String email

) {}
