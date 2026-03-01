package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.entities.Account;
import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.repositories.AccountRepository;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account findById(UUID id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    public void updatePlanType(Account account, PlanTypeEnum planType) {
        accountRepository.updatePlanTypeById(account.getId(), planType.toString());
    }

    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }
}
