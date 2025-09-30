package arquivos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class EscritorXml {
    private final DocumentBuilderFactory dbf;
    private final DocumentBuilder db;
    private final Document doc;
    public EscritorXml(List<Produto> produtos, String diretorio) throws Exception {
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        doc = db.newDocument();
        escreverXml(produtos, diretorio);
    }

    public void escreverXml(List<Produto> produtos, String diretorio) throws Exception {
        Element raiz = doc.createElement("produtos");
        doc.appendChild(raiz);

        for (Produto p : produtos) {
            Element produto = doc.createElement("produto");
            raiz.appendChild(produto);

            Element id = doc.createElement("id");
            produto.appendChild(id);
            id.appendChild(doc.createTextNode(p.getId().toString()));

            Element nome = doc.createElement("nome");
            produto.appendChild(nome);
            nome.appendChild(doc.createTextNode(p.getNome().toString()));


            Element preco = doc.createElement("preco");
            produto.appendChild(preco);
            preco.appendChild(doc.createTextNode(p.getPreco().toString()));

        }


        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformar = tf.newTransformer();

        DOMSource fonte = new DOMSource(doc);
        StreamResult gerar = new StreamResult(new File(diretorio));
        transformar.transform(fonte, gerar);
    }
}
