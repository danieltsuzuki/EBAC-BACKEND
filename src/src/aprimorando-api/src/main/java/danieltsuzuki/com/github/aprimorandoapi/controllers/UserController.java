package danieltsuzuki.com.github.aprimorandoapi.controllers;

import danieltsuzuki.com.github.aprimorandoapi.dto.UserRequest;
import danieltsuzuki.com.github.aprimorandoapi.dto.UserResponse;
import danieltsuzuki.com.github.aprimorandoapi.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@Controller
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(
            summary = "Cria um novo usuários se não existir com o mesmo nome e idade",
            description = "Cadastra um novo usuários no sistema"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Cliente criado com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserResponse.class)
            )
    )
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.saveIdempotent(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os usuários",
            description = "Retorna uma lista paginada de usuários"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista retornada com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserResponse.class)
            )
    )
    public ResponseEntity<Page<UserResponse>> findAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "id") String sortBy,
                                                      @RequestParam(defaultValue = "asc") String sortDir) {
        PageRequest pageRequest = PageRequest.of(page, size,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy)
                                                 : Sort.by(sortBy).descending());
        Page<UserResponse> users = userService.findAll(pageRequest);
        return ResponseEntity.ok(users);
    }
}
