package br.com.eleflow.challenge.service;

import br.com.eleflow.challenge.dto.PlanetDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanetService {

	Flux<PlanetDto> findAll();
	
	Flux<PlanetDto> findByName(String name);
	
	Mono<PlanetDto> findById(Long id);

	Mono<PlanetDto> save(PlanetDto planet);

	Mono<Void> delete(Long id);


}
