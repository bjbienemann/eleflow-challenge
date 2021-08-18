package br.com.eleflow.challenge.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import br.com.eleflow.challenge.dto.PlanetDto;
import br.com.eleflow.challenge.model.PlanetModel;
import br.com.eleflow.challenge.repository.PlanetRepository;
import br.com.eleflow.challenge.service.PlanetService;
import br.com.eleflow.challenge.swapi.SwApiProperties;
import br.com.eleflow.challenge.swapi.dto.SwPagedDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlanetServiceImpl implements PlanetService {

	private final PlanetRepository planetRepository;
	
	private final RestTemplate swapiRestTemplate;

	public PlanetServiceImpl(PlanetRepository planetRepository, 
			SwApiProperties swApiProperties, 
			RestTemplate swapiRestTemplate) {
		
		this.planetRepository = planetRepository;
		this.swapiRestTemplate = swapiRestTemplate;
	}

	@Override
	public Flux<PlanetDto> findAll() {
		return planetRepository.findAll().map(PlanetDto::new);
	}
	
	@Override
	public Flux<PlanetDto> findByName(String name) {
		return planetRepository.findByNameContaining(name)
				.map(PlanetDto::new);
	}

	@Override
	public Mono<PlanetDto> findById(Long id) {
		return planetRepository.findById(id)
				.switchIfEmpty(monoResponseStatusNotFoundException())
				.map(PlanetDto::new);
	}

	@Override
	public Mono<PlanetDto> save(PlanetDto dto) {
		String url = "/planets/?search={name}"; 
		var response = swapiRestTemplate.getForEntity(url, SwPagedDto.class, dto.getName());
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			SwPagedDto swPagedDto = response.getBody();
			swPagedDto.getResults().stream().findFirst()
				.ifPresent(p -> dto.setFilms(p.getFilms().size()));
		}
		
		var model = new PlanetModel(dto);
		
		return planetRepository.save(model).map(PlanetDto::new);
	}
	
	@Override
	public Mono<Void> delete(Long id) {
		return planetRepository.findById(id)
				.switchIfEmpty(monoResponseStatusNotFoundException())
				.flatMap(planetRepository::delete);
	}
	
	private <T> Mono<T> monoResponseStatusNotFoundException() {
		return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Planet not found"));
	}
	
}
