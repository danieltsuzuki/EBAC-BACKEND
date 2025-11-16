package danieltsuzuki.com.github.springsecurity.controllers;

import danieltsuzuki.com.github.springsecurity.dto.user.UserRequest;
import danieltsuzuki.com.github.springsecurity.dto.user.UserResponse;
import danieltsuzuki.com.github.springsecurity.dto.user.UserUpdateRequest;
import danieltsuzuki.com.github.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "20") int size,
                                                      @RequestParam(defaultValue = "firstName") String sortBy,
                                                      @RequestParam(defaultValue = "asc") String sortDir) {
        PageRequest pageRequest = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("asc") ? org.springframework.data.domain.Sort.by(sortBy).ascending()
                        : org.springframework.data.domain.Sort.by(sortBy).descending());
        return ResponseEntity.ok(userService.listAll(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(java.util.UUID.fromString(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(java.util.UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @Valid @RequestBody UserUpdateRequest request) {
        userService.update(java.util.UUID.fromString(id), request);
        return ResponseEntity.ok().build();
    }

}
