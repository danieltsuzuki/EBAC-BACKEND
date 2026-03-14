package danieltsuzuki.com.github.orquestrador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orquestrador")
public class OrquestradorController {

    private final RestTemplate restTemplate;

    public OrquestradorController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> orquestrar() {
        // Exemplo de chamada para o microserviço de cadastro
        String cadastroUrl = "http://localhost:8081/cadastro";
        ResponseEntity<String> cadastroResponse = restTemplate.getForEntity(cadastroUrl, String.class);

        // Exemplo de chamada para o microserviço de pagamento
        String pagamentoUrl = "http://localhost:8082/pagamento";
        ResponseEntity<String> pagamentoResponse = restTemplate.getForEntity(pagamentoUrl, String.class);

        // Combina as respostas dos microserviços e retorna uma resposta unificada
        String respostaUnificada = "Cadastro: " + cadastroResponse.getBody() + ", Pagamento: " + pagamentoResponse.getBody();
        return ResponseEntity.ok(respostaUnificada);
    }
}
