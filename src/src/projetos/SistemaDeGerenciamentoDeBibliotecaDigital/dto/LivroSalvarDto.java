package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.dto;

public class LivroSalvarDto {
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public LivroSalvarDto(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }


    @Override
    public String toString() {
        return "l|" + titulo + "|" + autor + '|' + anoPublicacao;
    }
}
