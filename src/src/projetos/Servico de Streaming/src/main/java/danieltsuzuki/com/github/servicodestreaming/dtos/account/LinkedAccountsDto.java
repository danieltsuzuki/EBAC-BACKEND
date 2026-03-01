package danieltsuzuki.com.github.servicodestreaming.dtos.account;

import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserDetailsDto;

import java.util.List;

public record LinkedAccountsDto(
        List<UserDetailsDto> linkedAccounts,
        int total
) {
    public LinkedAccountsDto(List<UserDetailsDto> linkedAccounts, int total) {
        this.linkedAccounts = linkedAccounts;
        this.total = total;
    }
}
