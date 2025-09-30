package arquivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorTxt {
    private final BufferedReader reader;

    public LeitorTxt(FileReader fileReader) {
        this.reader = new BufferedReader(fileReader);
    }

    public List<Produto> lerArquivo() throws IOException {
        List<Produto> produtos = new ArrayList<>();
        //pula primeira linha "cabeçalho
        String linha = this.reader.readLine();
        try {
            while (linha != null) {
                linha = this.reader.readLine();
                 if (linha == null)
                     break;
                 String[] mensagem = linha.split("\\|");
                 produtos.add(new Produto(Long.valueOf(mensagem[0]), mensagem[1], new BigDecimal(mensagem[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return produtos;
    }
}
