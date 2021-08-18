package br.com.eleflow.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.eleflow.challenge.swapi.dto.SwPagedDto;
import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/swapi")
public class SwApiController {
	
	@Autowired
	private WebClient swapiWebClient;

	@GetMapping("/planets")
	@Operation(summary = "Listar planetas", tags = {"SWAPI"})
	public Mono<?> findAll() {
		return swapiWebClient.get()
				.uri("/planets")
				.retrieve()
				.bodyToMono(SwPagedDto.class);
	}
	
}
