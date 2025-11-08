package danieltsuzuki.com.github.apirestfulparte2.dto;

public class UsuarioDto {
    private String nome;
    private String senha;

    public UsuarioDto(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
