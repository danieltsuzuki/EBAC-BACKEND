package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserRegisterDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserRegisterResponseDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserSubscriberRegisterDto;
import danieltsuzuki.com.github.servicodestreaming.entities.Account;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.enums.PerfilEnum;
import danieltsuzuki.com.github.servicodestreaming.enums.UserTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.repositories.UserRepository;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.ResourceAlreadyExistsException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.SubscriptionException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.UnderageUserException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    public RegistrationService(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               AccountService accountService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @Transactional
    public UserRegisterResponseDto register(UserSubscriberRegisterDto dto) {
        if (dto.age() < 18)
            throw new UnderageUserException("User must be at least 18 years old");

        if (userRepository.existsByEmail(dto.email()))
            throw new ResourceAlreadyExistsException("Email already exists");

        Account account = accountService.save(new Account(
                dto.planType()
        ));

        User user = new User(
                account,
                UserTypeEnum.SUBSCRIBER,
                dto.name(),
                dto.age(),
                dto.email().toLowerCase(),
                passwordEncoder.encode(dto.password())
        );

        account.updateSubscriberUser(user);
        user = userRepository.save(user);

        return new UserRegisterResponseDto(user.getId(), user.getEmail());
    }

    @Transactional
    public UserRegisterResponseDto registerLinkedUser(UserRegisterDto dto, User currentUser) {
        if (userRepository.existsByEmail(dto.email()))
            throw new ResourceAlreadyExistsException("Email already exists");

        Account account = accountService.findById(currentUser.getAccount().getId());

        long linkedUsers = userRepository.countByUsersByAccountSubscriberUser(currentUser.getId());
        if (linkedUsers >= account.getPlanType().getMaxLinkedAccounts())
            throw new SubscriptionException("Maximum number of linked users reached for this account");

        User user = userRepository.save(new User(
                account,
                UserTypeEnum.LINKED,
                dto.name(),
                dto.age(),
                dto.email().toLowerCase(),
                passwordEncoder.encode(dto.password()),
                PerfilEnum.definePerfilByAge(dto.age(), dto.allowContentAdult())
        ));

        return new UserRegisterResponseDto(user.getId(), user.getEmail());
    }

}
