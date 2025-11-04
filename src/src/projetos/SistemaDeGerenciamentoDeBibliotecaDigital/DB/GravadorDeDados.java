package projetos.SistemaDeGerenciamentoDeBibliotecaDigital.DB;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GravadorDeDados {
    private BufferedWriter writer;
    private final String CAMINHO_ARQUIVO = "C:\\Users\\Dmpt\\Desktop\\Projetos\\EBAC\\BACKEND\\src\\src\\projetos\\SistemaDeGerenciamentoDeBibliotecaDigital\\dados.dat";

    public GravadorDeDados()  {
        try {
            this.writer = abrirArquivo(true);
        } catch (Exception e) {
            Logger.getLogger(GravadorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void gravar(Object obj) {
        try {
            if (obj != null)
                writer.write(obj.toString()+"\n");
        } catch (Exception e) {
            Logger.getLogger(GravadorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            fechar();
        }
    }

    public void fechar() {
        try {
            if (writer != null)
                writer.close();
        } catch (Exception e) {
            Logger.getLogger(GravadorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public void editarArquivo(List<String> dados) {
        try {
            this.writer = abrirArquivo(false);
            for (String conteudo : dados)
                writer.write(conteudo+"\n");
        } catch (Exception e) {
            Logger.getLogger(GravadorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
        } finally {
            fechar();
        }
    }

    private BufferedWriter abrirArquivo(boolean editar) {
        try {
            return new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, editar));
        } catch (Exception e) {
            Logger.getLogger(GravadorDeDados.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

}
