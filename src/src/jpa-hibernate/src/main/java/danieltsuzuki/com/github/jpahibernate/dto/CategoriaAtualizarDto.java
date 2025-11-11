package danieltsuzuki.com.github.jpahibernate.dto;

import org.hibernate.validator.constraints.Length;

public class CategoriaAtualizarDto {

    private Long id;
    @Length(min = 3, max = 50)
    private String nome;
    @Length(max = 100)
    private String descricao;

    public CategoriaAtualizarDto(String nome, String descricao) {
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
