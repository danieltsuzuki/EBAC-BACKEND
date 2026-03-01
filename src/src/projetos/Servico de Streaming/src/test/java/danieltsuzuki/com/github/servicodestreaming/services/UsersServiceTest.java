//package danieltsuzuki.com.github.servicodestreaming.services;
//
//import danieltsuzuki.com.github.servicodestreaming.entities.User;
//import danieltsuzuki.com.github.servicodestreaming.repositories.UserRepository;
//import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.ResourceNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class UsersServiceTest {
//
//    @Mock
//    UserRepository userRepository;
//
//    @InjectMocks
//    UsersService usersService;
//
//    String email;
//    User user;
//
//    @BeforeEach
//    void setUp() {
//        email = "teste@mail.com";
//        user = new User(null, null, null, null, 0, email, null, null, null);
//    }
//
//    @Test
//    void User_ShouldLoadByUserName() {
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//        var result = usersService.loadUserByUsername(email);
//        assertNotNull(result);
//        assertEquals(email, result.getUsername());
//    }
//
//    @Test
//    void User_ThrowsException_UsernameNotFoundException()  {
//        UsernameNotFoundException exception = assertThrows(
//                UsernameNotFoundException.class,
//                () -> usersService.loadUserByUsername(email)
//        );
//
//        assertEquals("User or password invalid", exception.getMessage());
//        verify(userRepository, times(1)).findByEmail(email);
//    }
//
//    @Test
//    void User_ShouldDeleteByEmail() {
//        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
//        doNothing().when(userRepository).deleteByEmail(user.getEmail());
//        String message = usersService.deleteUser(user);
//        assertEquals("User with email " + user.getEmail() + " deleted successfully.", message);
//        verify(userRepository, times(1)).existsByEmail(user.getEmail());
//        verify(userRepository, times(1)).deleteByEmail(user.getEmail());
//    }
//
//    @Test
//    void User_ThrowsException_ResourceNotFoundException() {
//        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
//        ResourceNotFoundException exception = assertThrows(
//                ResourceNotFoundException.class,
//                () -> usersService.deleteUser(user)
//        );
//
//        assertEquals("User not found with email: " + user.getEmail(), exception.getMessage());
//        verify(userRepository, times(1)).existsByEmail(user.getEmail());
//        verify(userRepository, times(0)).deleteByEmail(user.getEmail());
//    }
//
//}

package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.user.UserDetailsDto;
import danieltsuzuki.com.github.servicodestreaming.entities.Account;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.enums.UserTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.repositories.UserRepository;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.ResourceNotFoundException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.SubscriptionException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.UserOrPasswordInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    AccountService accountService;

    @InjectMocks
    UsersService usersService;

    UUID userId;
    String email;
    User user;
    Account account;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        email = "teste@mail.com";
        account = new Account(PlanTypeEnum.BASIC); // ajuste conforme seu enum
        user = new User(userId, account, UserTypeEnum.SUBSCRIBER, "Test User", 25, email, "password123", null, null);
    }

    // =========================================================
    // loadUserByUsername
    // =========================================================
    @Nested
    class LoadUserByUsername {

        @Test
        void shouldReturnUserDetails_WhenEmailExists() {
            when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

            UserDetails result = usersService.loadUserByUsername(email);

            assertNotNull(result);
            assertEquals(email, result.getUsername());
            verify(userRepository, times(1)).findByEmail(email);
        }

        @Test
        void shouldThrowUserOrPasswordInvalidException_WhenEmailNotFound() {
            when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

            UserOrPasswordInvalidException exception = assertThrows(
                    UserOrPasswordInvalidException.class,
                    () -> usersService.loadUserByUsername(email)
            );

            assertEquals("User or password invalid", exception.getMessage());
            verify(userRepository, times(1)).findByEmail(email);
        }
    }

    // =========================================================
    // deleteUser
    // =========================================================
    @Nested
    class DeleteUser {

        @Test
        void shouldDeleteUser_WhenEmailExists() {
//            when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//            doNothing().when(userRepository).delete(user);
//
//            String result = usersService.deleteUser(user);
//
//            assertEquals("User with email " + email + " deleted successfully.", result);
//            verify(userRepository, times(1)).findByEmail(email);
//            verify(userRepository, times(1)).delete(user);
        }

        @Test
        void shouldThrowResourceNotFoundException_WhenEmailNotFound() {
            when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(
                    ResourceNotFoundException.class,
                    () -> usersService.deleteUser(user)
            );

            assertEquals("User not found with email: " + email, exception.getMessage());
            verify(userRepository, times(1)).findByEmail(email);
            verify(userRepository, never()).delete(any());
        }
    }

    // =========================================================
    // getUserDetails
    // =========================================================
    @Nested
    class GetUserDetails {

        @Test
        void shouldReturnUserDetailsDto_WhenUserExists() {
            when(userRepository.findById(userId)).thenReturn(Optional.of(user));

            UserDetailsDto result = usersService.getUserDetails(user);

            assertNotNull(result);
            verify(userRepository, times(1)).findById(userId);
        }

        @Test
        void shouldThrowResourceNotFoundException_WhenUserNotFound() {
            when(userRepository.findById(userId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(
                    ResourceNotFoundException.class,
                    () -> usersService.getUserDetails(user)
            );

            assertTrue(exception.getMessage().contains(user.getEmail()));
            verify(userRepository, times(1)).findById(userId);
        }
    }

    // =========================================================
    // getLinkedAccounts
    // =========================================================
    @Nested
    class GetLinkedAccounts {

        @Test
        void shouldReturnLinkedAccountsList_WhenUsersExist() {
            User linkedUser = new User(UUID.randomUUID(), account, UserTypeEnum.LINKED, "Linked User", 20, "linked@mail.com", "pass", null, null);
            when(userRepository.findAllUsersByAccountSubscriberUser(userId)).thenReturn(List.of(linkedUser));

            List<UserDetailsDto> result = usersService.getLinkedAccounts(user);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(userRepository, times(1)).findAllUsersByAccountSubscriberUser(userId);
        }

        @Test
        void shouldReturnEmptyList_WhenNoLinkedUsers() {
            when(userRepository.findAllUsersByAccountSubscriberUser(userId)).thenReturn(List.of());

            List<UserDetailsDto> result = usersService.getLinkedAccounts(user);

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(userRepository, times(1)).findAllUsersByAccountSubscriberUser(userId);
        }
    }

    // =========================================================
    // updatePlanType
    // =========================================================
    @Nested
    class UpdatePlanType {

        @Test
        void shouldThrowResourceNotFoundException_WhenUserNotFound() {
            when(userRepository.findById(userId)).thenReturn(Optional.empty());

            assertThrows(
                    ResourceNotFoundException.class,
                    () -> usersService.updatePlanType(user, PlanTypeEnum.PREMIUM)
            );

            verify(userRepository, times(1)).findById(userId);
            verifyNoInteractions(accountService);
        }

        @Test
        void shouldThrowSubscriptionException_WhenPlanTypeIsTheSame() {
            when(userRepository.findById(userId)).thenReturn(Optional.of(user));

            SubscriptionException exception = assertThrows(
                    SubscriptionException.class,
                    () -> usersService.updatePlanType(user, PlanTypeEnum.BASIC)
            );

            assertEquals("It is not possible to upgrade to this plan.", exception.getMessage());
            verifyNoInteractions(accountService);
        }

        @Test
        void shouldUpdatePlanType_WhenUpgrading() {
            when(userRepository.findById(userId)).thenReturn(Optional.of(user));

            String result = usersService.updatePlanType(user, PlanTypeEnum.PREMIUM);

            assertEquals("Plan updated successfully to PREMIUM", result);
            verify(accountService, times(1)).updatePlanType(account, PlanTypeEnum.PREMIUM);
            verify(userRepository, never()).deleteAll(any());
        }

        @Test
        void shouldDeleteLinkedUsers_WhenDowngrading() {
            // Simula usuário com plano PREMIUM fazendo downgrade para BASIC
            Account premiumAccount = new Account(PlanTypeEnum.PREMIUM);
            User premiumUser = new User(userId, premiumAccount, UserTypeEnum.SUBSCRIBER, "Test User", 25, email, "password123", null, null);

            User linkedUser = new User(UUID.randomUUID(), premiumAccount, UserTypeEnum.LINKED, "Linked", 20, "linked@mail.com", "pass", null, null);

            when(userRepository.findById(userId)).thenReturn(Optional.of(premiumUser));
            when(userRepository.findAllUsersByAccountSubscriberUser(userId)).thenReturn(List.of(linkedUser));

            String result = usersService.updatePlanType(premiumUser, PlanTypeEnum.BASIC);

            assertEquals("Plan updated successfully to BASIC", result);
            verify(userRepository, times(1)).findAllUsersByAccountSubscriberUser(userId);
            verify(userRepository, times(1)).deleteAll(List.of(linkedUser));
            verify(accountService, times(1)).updatePlanType(premiumAccount, PlanTypeEnum.BASIC);
        }
    }
}