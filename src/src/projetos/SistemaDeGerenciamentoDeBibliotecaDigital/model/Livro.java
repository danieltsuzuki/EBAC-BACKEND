package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model;

import java.io.Serializable;
import java.util.Objects;

public class Livro implements Serializable, Comparable<Livro>{
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public Livro(String titulo, String autor, int anoPublicacao) {
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return anoPublicacao == livro.anoPublicacao && Objects.equals(titulo, livro.titulo) && Objects.equals(autor, livro.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, anoPublicacao);
    }

    @Override
    public String toString() {
        return "{ titulo: " + titulo + ", autor: " + autor + ", anoPublicacao: " + anoPublicacao + " }";
    }

    @Override
    public int compareTo(Livro o) {
        return this.titulo.compareTo(o.titulo);
    }
}
