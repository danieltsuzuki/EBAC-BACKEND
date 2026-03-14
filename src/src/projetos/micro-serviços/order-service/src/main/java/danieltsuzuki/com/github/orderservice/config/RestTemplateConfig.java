package danieltsuzuki.com.github.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestClient restClient() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(Duration.ofSeconds(3));
        clientHttpRequestFactory.setReadTimeout(Duration.ofSeconds(3));
        return RestClient.builder().requestFactory(clientHttpRequestFactory).build();
    }
}
