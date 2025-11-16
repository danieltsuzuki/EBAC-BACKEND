package danieltsuzuki.com.github.springsecurity.utils.mapper;

import danieltsuzuki.com.github.springsecurity.dto.user.UserRequest;
import danieltsuzuki.com.github.springsecurity.dto.user.UserResponse;
import danieltsuzuki.com.github.springsecurity.dto.user.UserUpdateRequest;
import danieltsuzuki.com.github.springsecurity.entities.User;

import java.util.UUID;

public class UserMapper {
    public static User toEntity(UserRequest userRequest) {
        User user = new User(
                null,
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getPassword(),
                userRequest.getEmail(),
                userRequest.getRole());
        return user;
    }

    public static User toUpdateEntity(UserUpdateRequest userRequest, UUID id) {
        User user = new User(
                id,
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getPassword(),
                userRequest.getEmail(),
                userRequest.getRole());
        return user;
    }

    public static UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
}
