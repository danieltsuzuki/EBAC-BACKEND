package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserDetailsDto;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.enums.UserTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.repositories.UserRepository;
import danieltsuzuki.com.github.servicodestreaming.repositories.WatchHistoryRepository;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.ResourceNotFoundException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.SubscriptionException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.UserOrPasswordInvalidException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final WatchHistoryRepository watchHistoryRepository;

    public UsersService(UserRepository usersRepository, AccountService accountService, WatchHistoryRepository watchHistoryRepository) {
        this.userRepository = usersRepository;
        this.accountService = accountService;
        this.watchHistoryRepository = watchHistoryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UserOrPasswordInvalidException("User or password invalid"));
    }

    @Transactional
    public String deleteUser(User user) {
        User managedUser = userRepository.findByEmail(user.getEmail().toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with email: " + user.getEmail().toLowerCase()));

        if (managedUser.getUserType() == UserTypeEnum.SUBSCRIBER) {

            List<User> linkedUsers = userRepository.findAllUsersByAccountSubscriberUser(managedUser.getId());
            linkedUsers.forEach(linked -> {
                watchHistoryRepository.deleteByUserId(linked.getId());
                userRepository.deleteById(linked.getId());
            });

            watchHistoryRepository.deleteByUserId(managedUser.getId());

            userRepository.delete(managedUser);

            accountService.delete(managedUser.getAccount().getId());

        } else {
            watchHistoryRepository.deleteByUserId(managedUser.getId());
            userRepository.delete(managedUser);
        }

        return "User with email " + managedUser.getEmail() + " deleted successfully.";
    }

    public UserDetailsDto getUserDetails(User user) {
        return userRepository.findById(user.getId()).map(UserDetailsDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + user.getEmail().toLowerCase()));
    }

    @Transactional
    public List<UserDetailsDto> getLinkedAccounts(User user) {
        return userRepository.findAllUsersByAccountSubscriberUser(user.getId()).stream().map(UserDetailsDto::new).toList();
    }

    @Transactional
    public String updatePlanType(User currentUser, PlanTypeEnum planType) {
        User managedUser = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        PlanTypeEnum currentPlanType = managedUser.getAccount().getPlanType();

        if (currentPlanType.equals(planType))
            throw new SubscriptionException("It is not possible to upgrade to this plan.");

        if (currentPlanType.getMaxLinkedAccounts() > planType.getMaxLinkedAccounts()) {
            List<User> linkedUsers = userRepository.findAllUsersByAccountSubscriberUser(managedUser.getId());
            userRepository.deleteAll(linkedUsers);
        }

        accountService.updatePlanType(managedUser.getAccount(), planType);

        return "Plan updated successfully to " + planType.name();
    }
}
