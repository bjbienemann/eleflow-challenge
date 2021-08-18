package br.com.eleflow.challenge.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.eleflow.challenge.dto.PlanetDto;
import br.com.eleflow.challenge.service.PlanetService;
import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/planets")
public class PlanetController {

	
	private final PlanetService planetService;
	
	public PlanetController(PlanetService planetService) {
		this.planetService = planetService;
	}

	@GetMapping
	@Operation(summary = "Listar planetas", tags = {"Planetas"})
	public Flux<PlanetDto> find(@RequestParam @Nullable String name) { 
		if (Objects.isNull(name)) {
			return planetService.findAll();
		}
		
		return planetService.findByName(name);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Buscar por ID", tags = {"Planetas"})
	public Mono<PlanetDto> findById(@PathVariable Long id) { 
		return planetService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Adicionar um planeta", tags = {"Planetas"})
	public Mono<PlanetDto> add(@Valid @RequestBody PlanetDto planet) {
		return planetService.save(planet);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Remover planeta", tags = {"Planetas"})
	public Mono<Void> delete(@PathVariable Long id) {
		return planetService.delete(id);
	}
}
