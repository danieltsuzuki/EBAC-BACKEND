package arquivos;

import java.io.FileReader;
import java.util.List;

public class Sistema {
    public static void main(String[] args) {
        try {
            LeitorTxt txt = new LeitorTxt(new FileReader(ArquivoTxt.diretorio));
            List<Produto> produtos = txt.lerArquivo();

            EscritorXml xml = new EscritorXml(produtos, ArquivoXml.diretorio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
