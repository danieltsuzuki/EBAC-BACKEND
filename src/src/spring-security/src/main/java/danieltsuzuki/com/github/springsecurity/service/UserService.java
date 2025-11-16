package danieltsuzuki.com.github.springsecurity.service;

import danieltsuzuki.com.github.springsecurity.dto.user.UserRequest;
import danieltsuzuki.com.github.springsecurity.dto.user.UserResponse;
import danieltsuzuki.com.github.springsecurity.dto.user.UserUpdateRequest;
import danieltsuzuki.com.github.springsecurity.entities.User;
import danieltsuzuki.com.github.springsecurity.exceptions.EmailExistsException;
import danieltsuzuki.com.github.springsecurity.exceptions.ResourceNotFoundException;
import danieltsuzuki.com.github.springsecurity.repositories.UserRepository;
import danieltsuzuki.com.github.springsecurity.utils.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(final UserRepository repository) {
        this.repository = repository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Transactional
    public void save(UserRequest request) {
        if (repository.existsByEmail(request.getEmail()))
            throw new EmailExistsException("Email already in use");
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        repository.save(UserMapper.toEntity(request));
    }

    public UserResponse getById(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
       return UserMapper.toResponse(user);
    }

    public Page<UserResponse> listAll(Pageable pageable) {
        return repository.findAll(pageable).map(UserMapper::toResponse);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("User not found");
        repository.deleteById(id);
    }

    @Transactional
    public void update(UUID id, UserUpdateRequest request) {
        User oldUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (request.getFirstName() == null)
            request.setFirstName(oldUser.getFirstName());
        if (request.getLastName() == null)
            request.setLastName(oldUser.getLastName());
        if (request.getEmail() == null)
            request.setEmail(oldUser.getEmail());
        if (request.getPassword() == null)
            request.setPassword(oldUser.getPassword());
        if (request.getRole() == null)
            request.setRole(oldUser.getRole());
        repository.save(UserMapper.toUpdateEntity(request, id));
    }


}
