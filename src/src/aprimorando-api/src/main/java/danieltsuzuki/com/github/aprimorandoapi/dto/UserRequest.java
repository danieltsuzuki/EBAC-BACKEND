package danieltsuzuki.com.github.aprimorandoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(name = "UserRequest", description = "Representa um usuário para criação")
public class UserRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    @Schema(description = "nome do usuário", example = "josé")
    private String name;

    @Positive
    @NotNull
    @Schema(description = "idade do usuário", example = "30")
    private Integer age;

    public UserRequest(){}

    public UserRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
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
