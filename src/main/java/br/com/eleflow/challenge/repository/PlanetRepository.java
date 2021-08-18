package br.com.eleflow.challenge.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import br.com.eleflow.challenge.model.PlanetModel;
import reactor.core.publisher.Flux;

public interface PlanetRepository extends ReactiveCrudRepository<PlanetModel, Long> {
	
	Flux<PlanetModel> findByNameContaining(String name);
	
}
