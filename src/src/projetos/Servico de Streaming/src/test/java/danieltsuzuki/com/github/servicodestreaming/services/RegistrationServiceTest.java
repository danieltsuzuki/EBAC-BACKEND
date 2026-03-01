package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserRegisterDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserRegisterResponseDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserSubscriberRegisterDto;
import danieltsuzuki.com.github.servicodestreaming.entities.Account;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.enums.UserTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.repositories.UserRepository;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.ResourceAlreadyExistsException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.UnderageUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationService registrationService;

    private UserSubscriberRegisterDto userSubscriberRegisterDto;
    private UserSubscriberRegisterDto underageDto;
    private UserRegisterDto userRegisterDto;
    private Account account;
    private User user;
    private User userLinked;

    @BeforeEach
    void setUp() {
        userSubscriberRegisterDto = new UserSubscriberRegisterDto(
                "John Doe",
                "john.doe@example.com",
                "password123",
                25,
                PlanTypeEnum.STANDARD
        );

        underageDto = new UserSubscriberRegisterDto(
                "Young User",
                "user@mail.com",
                "password123",
                17,
                PlanTypeEnum.BASIC
        );

        userRegisterDto = new UserRegisterDto(
                "Jane Doe",
                "john.doe@example.com",
                "password123",
                20,
                true
        );

        account = new Account(UUID.randomUUID(), userSubscriberRegisterDto.planType(), null, Instant.now(), null);

        user = new User(
                UUID.randomUUID(),
                account,
                UserTypeEnum.SUBSCRIBER,
                userSubscriberRegisterDto.name(),
                userSubscriberRegisterDto.age(),
                userSubscriberRegisterDto.email(),
                userSubscriberRegisterDto.password(),
                Instant.now(),
                null
        );

        userLinked = new User(
                UUID.randomUUID(),
                user.getAccount(),
                UserTypeEnum.LINKED,
                userRegisterDto.name(),
                userRegisterDto.age(),
                userRegisterDto.email(),
                userRegisterDto.password(),
                Instant.now(),
                null
        );

        account = new Account(account.getId(), account.getPlanType(), user, account.getCreatedAt(), account.getUpdatedAt());
    }

    @Test
    void register_ShouldRegisterUserSuccessfully_WhenEmailDoesNotExist() {
        // Arrange
        when(userRepository.existsByEmail(userSubscriberRegisterDto.email())).thenReturn(false);
        when(passwordEncoder.encode(userSubscriberRegisterDto.password())).thenReturn("encodedPassword");
        when(accountService.save(any(Account.class))).thenReturn(account);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserRegisterResponseDto response = registrationService.register(userSubscriberRegisterDto);

        // Assert
        assertNotNull(response);
        assertEquals(user.getId(), response.id());
        assertEquals("john.doe@example.com", response.email());

        verify(userRepository).existsByEmail(userSubscriberRegisterDto.email());
        verify(passwordEncoder).encode(userSubscriberRegisterDto.password());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_ShouldThrowException_WhenEmailAlreadyExists() {
        // Arrange
        when(userRepository.existsByEmail(userSubscriberRegisterDto.email())).thenReturn(true);

        // Act & Assert
        ResourceAlreadyExistsException exception = assertThrows(
                ResourceAlreadyExistsException.class,
                () -> registrationService.register(userSubscriberRegisterDto)
        );

        assertEquals("Email already exists", exception.getMessage());
        verify(userRepository).existsByEmail(userSubscriberRegisterDto.email());
        verify(accountService, never()).save(any(Account.class));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register_ShouldThrowException_WhenIsUnderage() {
        // Arrange

        // Act & Assert
        UnderageUserException exception = assertThrows(
                UnderageUserException.class,
                () -> registrationService.register(underageDto)
        );

        assertEquals("User must be at least 18 years old", exception.getMessage());
        verify(userRepository, never()).existsByEmail(underageDto.email());
        verify(accountService, never()).save(any(Account.class));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register_ShouldEncodePassword() {
        // Arrange
        when(userRepository.existsByEmail(userSubscriberRegisterDto.email())).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(accountService.save(any(Account.class))).thenReturn(account);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        registrationService.register(userSubscriberRegisterDto);

        // Assert
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(argThat(u ->
                u.getPassword().equals("encodedPassword")
        ));
    }

    @Test
    void register_shouldRegisterUserLinkedSuccessfully_WhenEmailDoesNotExist() {
        // Arrange
        when(userRepository.existsByEmail(userRegisterDto.email())).thenReturn(false);
        when(accountService.findById(any(UUID.class))).thenReturn(account);
        when(passwordEncoder.encode(userRegisterDto.password())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(userLinked);

        // Act
        UserRegisterResponseDto response = registrationService.registerLinkedUser(userRegisterDto, user);

        // Assert
        assertNotNull(response);
        assertEquals(userLinked.getId(), response.id());
        assertEquals(userLinked.getEmail(), response.email());
    }

}