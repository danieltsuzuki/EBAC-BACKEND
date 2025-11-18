package danieltsuzuki.com.github.aprimorandoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserResponse", description = "Representa um usuário retornado pela API")
public class UserResponse {

    @Schema(description = "ID do usuário", example = "1")
    private Long id;
    @Schema(description = "Nome do usuário", example = "josé")
    private String name;
    @Schema(description = "Idade do usuário", example = "30")
    private Integer age;

    public UserResponse() {
    }
    public UserResponse(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
