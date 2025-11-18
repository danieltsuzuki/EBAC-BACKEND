package danieltsuzuki.com.github.aprimorandoapi.services;

import danieltsuzuki.com.github.aprimorandoapi.dto.UserRequest;
import danieltsuzuki.com.github.aprimorandoapi.dto.UserResponse;
import danieltsuzuki.com.github.aprimorandoapi.mapper.UserMapper;
import danieltsuzuki.com.github.aprimorandoapi.model.User;
import danieltsuzuki.com.github.aprimorandoapi.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse saveIdempotent(UserRequest request) {
        User user = userRepository.findByNameAndAge(request.getName(), request.getAge())
                .orElseGet(() -> userRepository.save(UserMapper.toEntity(request)));
        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    public Page<UserResponse> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).map(UserMapper::toResponse);
    }
}
