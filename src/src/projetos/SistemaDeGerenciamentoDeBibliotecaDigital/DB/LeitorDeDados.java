package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.DB;

import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Emprestimo;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Livro;
import projetos.SistemaDeGerenciamentoDeBibliotecaDigital.model.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LeitorDeDados {
    private BufferedReader reader;
    private final String CAMINHO_ARQUIVO = "C:\\Users\\Dmpt\\Desktop\\Projetos\\EBAC\\BACKEND\\src\\src\\projetos\\SistemaDeGerenciamentoDeBibliotecaDigital\\dados.dat";

    public LeitorDeDados() {
        try {
            this.reader = abrirArquivo();
        } catch (Exception e) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public List<Object> listarDados() {
        List<Object> list = new ArrayList<>();
        try {
            String linha = reader.readLine();
            while (linha != null) {
                String[] dados = linha.split("\\|");
                switch (dados[0]) {
                    case "u" :
                        list.add(new Usuario(dados[1], dados[2]));
                        break;
                    case "l" :
                        list.add(new Livro(
                            dados[1],
                            dados[2],
                            Integer.parseInt(dados[3])
                        ));
                        break;
                    case "e":
                        list.add(new Emprestimo(
                            new Livro(
                                    dados[1],
                                    null,
                                    1
                            ),
                            new Usuario(dados[2], null)
                        ));
                        break;
                }
                linha = reader.readLine();
            }
        } catch (Exception e) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return list;
    }

    public List<Livro> listarLivros() {
        try {
            return listarDados().stream()
                    .filter(Livro.class::isInstance)
                    .map(Livro.class::cast)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<Usuario> listarUsuarios() {
        try {
            return listarDados().stream().filter(Usuario.class::isInstance)
                    .map(Usuario.class::cast).collect(Collectors.toList());
        } catch (Exception e) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            fechar();
        }
        return new ArrayList<>();
    }

    public List<Emprestimo> listarEmprestimos() {
        try {
            return listarDados().stream()
                    .filter(Emprestimo.class::isInstance)
                    .map(Emprestimo.class::cast)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return new ArrayList<>();
    }

    /*
        Retorna uma lista com todos os dados do arquivo, exceto a linha que contém o textoAntigo
    */
    public List<String> removerLinhaSelecionada(String textoAntigo) {
        List<String> linhas = new ArrayList<>();
        try {
            this.reader = abrirArquivo();
            String linha = reader.readLine();
            while (linha != null) {
                if (!linha.equals(textoAntigo)) {
                    linhas.add(linha);
                }
                linha = reader.readLine();
            }
        } catch (Exception e) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            fechar();
        }
        return linhas;
    }

    public void fechar() {
        try {
            reader.close();
        } catch (Exception e) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    private BufferedReader abrirArquivo() {
        try {
            return new BufferedReader(new FileReader(CAMINHO_ARQUIVO));
        } catch (Exception e) {
            Logger.getLogger(LeitorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

}
