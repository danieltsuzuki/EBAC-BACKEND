package danieltsuzuki.com.github.servicodestreaming.dtos.user;

public record UserLoginResponseDto(
        String token,
        String tokenType,
        Long expiresInSeconds
) {}
