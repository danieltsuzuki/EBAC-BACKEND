package danieltsuzuki.com.github.aprimorandoapi.mapper;

import danieltsuzuki.com.github.aprimorandoapi.dto.UserRequest;
import danieltsuzuki.com.github.aprimorandoapi.dto.UserResponse;
import danieltsuzuki.com.github.aprimorandoapi.model.User;

public class UserMapper {
    public static User toEntity(UserRequest request) {
        return new User(null, request.getName(), request.getAge());
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getAge());
    }
}
