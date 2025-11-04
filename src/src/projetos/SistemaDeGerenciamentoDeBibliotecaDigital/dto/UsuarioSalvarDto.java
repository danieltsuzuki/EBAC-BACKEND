package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.dto;

public class UsuarioSalvarDto {

    private String nome;
    private String email;

    public UsuarioSalvarDto(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "u|" + nome + "|" + email;
    }
}
