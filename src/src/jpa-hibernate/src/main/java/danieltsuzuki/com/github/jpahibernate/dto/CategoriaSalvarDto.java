package danieltsuzuki.com.github.jpahibernate.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CategoriaSalvarDto {

    private Long id;
    @NotBlank
    @Length(min = 3, max = 50)
    private String nome;
    @Length(max = 100)
    private String descricao;

    public CategoriaSalvarDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
