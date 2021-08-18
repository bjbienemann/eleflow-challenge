package br.com.eleflow.challenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.eleflow.challenge.swapi.SwApiProperties;

@Configuration
public class WebClientConfig {
	
	@Autowired
	private SwApiProperties swApiProperties;
	
	@Bean
	public WebClient swapiWebClient(WebClient.Builder builder) {
		return builder.baseUrl(swApiProperties.getUrl()).build();
	}

	@Bean
	public RestTemplate swapiRestTemplate() {
		return new RestTemplateBuilder().rootUri(swApiProperties.getUrl()).build();
	}
	
}
